package org.vaadin.training.views.department;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class EmployeeEditor extends FormLayout implements ClickListener {

	private static final long serialVersionUID = 1L;

	private Button commit;
	private Button cancel;

	@PropertyId("firstName")
	private TextField firstName;

	@PropertyId("lastName")
	private TextField lastName;

	@PropertyId("email")
	private TextField email;

	@PropertyId("salary")
	private TextField salary;

	private EmployeeEditorHandler editorHandler;
	private BeanFieldGroup<EmployeeProxy> binder;

	public EmployeeEditor(EmployeeEditorHandler editorHandler) {
		this.editorHandler = editorHandler;
		binder = new BeanFieldGroup<EmployeeProxy>(EmployeeProxy.class);

		setEnabled(false);
		setMargin(true);

		firstName = new TextField("First name");
		addComponent(firstName);

		lastName = new TextField("Last name");
		addComponent(lastName);

		email = new TextField("E-mail");
		addComponent(email);

		salary = new TextField("Salary");
		addComponent(salary);

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);

		commit = new Button("Commit", this);
		buttonLayout.addComponent(commit);

		cancel = new Button("Cancel", this);
		buttonLayout.addComponent(cancel);
		addComponent(buttonLayout);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (commit.equals(event.getButton())) {
			editorHandler.onSaveClick();
		} else {
			editorHandler.onCancelClick();
		}
	}

	public EmployeeProxy commit() {
		try {
			binder.commit();
			return binder.getItemDataSource().getBean();
		} catch (CommitException e) {
			return null;
		}
	}

	public void discard() {
		binder.discard();
	}

	public void setEmployee(BeanItem<EmployeeProxy> employee) {
		binder.setItemDataSource(employee);
		binder.bindMemberFields(this);
	}

}
