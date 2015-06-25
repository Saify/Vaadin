package org.vaadin.training.views.department;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.vaadin.training.authentication.AuthenticationService;
import org.vaadin.training.backend.PersonService;
import org.vaadin.training.backend.ProgressingFuture;
import org.vaadin.training.data.Person;

public class DepartmentPresenter {

	private DepartmentView view;
	private PersonService service = new PersonService();
	private DepartmentModel model;
	private final ExecutorService pool = Executors.newFixedThreadPool(10);
	private String parameters;

	public DepartmentPresenter(DepartmentView view) {
		this.view = view;
		model = new DepartmentModel();
	}

	public void onViewEnter(String parameters) {
		this.parameters = parameters;
		model.getEmployees().clear();
		model.setDepartmentName(AuthenticationService.getLoggedInUser()
				.getDepartment().getName());
		model.setDataLoadingState(0);
		view.setModel(model);
		pool.submit(new UIUpdateRunnable());
	}

	private void setSelectedUser(List<EmployeeProxy> persons) {
		if (parameters != null && !parameters.isEmpty()) {
			try {
				Integer id = Integer.parseInt(parameters);
				EmployeeProxy person = getPersonWithId(id, persons);
				view.selectEmployee(person);
				view.scrollToSelection();
			} catch (NumberFormatException e) {
				Logger.getLogger("DepartmentView").log(Level.WARNING,
						"Unable to parse ID from '" + parameters + "'");
			}
		}
	}

	private EmployeeProxy getPersonWithId(Integer id, List<EmployeeProxy> persons) {
		for (EmployeeProxy person : persons) {
			if (person.getId() == id) {
				return person;
			}
		}
		return null;
	}

	public void saveClicked() {
		EmployeeProxy person = view.commitChanges();
		if (person != null) {
			service.save(person.getPerson());
			view.selectEmployee(null);
		}
	}

	public void cancelClicked() {
		view.discardChanges();
		view.selectEmployee(null);
	}

	public void employeeSelected(EmployeeProxy employee) {
		view.showEmployeeInForm(employee);

		// This will trigger the uri fragment change
		view.selectEmployee(employee);
	}

	class UIUpdateRunnable implements Runnable {
		ProgressingFuture<List<Person>> employeesAsync;

		@Override
		public void run() {
			employeesAsync = service.getEmployeesAsync();

			while (!employeesAsync.isDone()) {
				if (employeesAsync.isCancelled()) {
					break;
				}

				int progress = employeesAsync.getProgress();
				final float percentageProgress = progress / 100f;
				model.setDataLoadingState(percentageProgress);
				if (progress >= 100) {
					break;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				List<EmployeeProxy> employees = new ArrayList<EmployeeProxy>();
				for (Person person : employeesAsync.get()) {
					employees.add(new EmployeeProxy(person));
				}
				model.setEmployees(employees);
				model.setDataLoadingState(1);
				setSelectedUser(employees);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
