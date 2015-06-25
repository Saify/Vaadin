package org.vaadin.training.views.department;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.vaadin.training.authentication.AuthenticationService;
import org.vaadin.training.backend.PersonService;
import org.vaadin.training.data.Person;

import com.vaadin.cdi.UIScoped;

@UIScoped
public class DepartmentPresenter {

	private DepartmentView view;
	
	@Inject
	private PersonService service; 

	public void setView(DepartmentView view) {
		this.view = view;
	}
	
	public void onViewEnter(String parameters) {
		List<Person> persons = service.getEmployees();
		view.setEmployees(service.getEmployees());
		view.setDepartment(AuthenticationService.getLoggedInUser().getDepartment()); 
		
		if (parameters != null && !parameters.isEmpty()) {
			try {
				Integer id = Integer.parseInt(parameters);
				Person person = getPersonWithId(id, persons);
				view.selectEmployee(person);
				view.scrollToSelection();
			} catch (NumberFormatException e) {
				Logger.getLogger("DepartmentView").log(
						Level.WARNING,
						"Unable to parse ID from '" + parameters
								+ "'");
			}
		}
	}

	private Person getPersonWithId(Integer id, List<Person> persons) {
		for(Person person : persons) {
			if(person.getId() == id) {
				return person;
			}
		}
		return null;
	}

	public void saveClicked() {
		Person person = view.commitChanges();
		if (person != null) {
			service.save(person);	
			view.selectEmployee(null);
		}
	}

	public void cancelClicked() {
		view.discardChanges();
		view.selectEmployee(null);
	}

	public void employeeSelected(Person employee) {
		view.showEmployeeInForm(employee);
		
		// This will trigger the uri fragment change
		view.selectEmployee(employee);
	}


}
