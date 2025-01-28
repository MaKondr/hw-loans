package com.company.homeworkloans.view.requestloan;


import com.company.homeworkloans.entity.Client;
import com.company.homeworkloans.entity.Loan;
import com.company.homeworkloans.entity.LoanStatus;
import com.company.homeworkloans.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.textfield.JmixBigDecimalField;
import io.jmix.flowui.component.validation.ValidationErrors;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Route(value = "RequestLoan", layout = MainView.class)
@ViewController(id = "Requestloan")
@ViewDescriptor(path = "RequestLoan.xml")
@DialogMode()
public class Requestloan extends StandardView {
    @ViewComponent
    private EntityComboBox<Client> clientsComboBox;
    @Autowired
    private ViewValidation viewValidation;
    @ViewComponent
    private VerticalLayout mainBox;
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private JmixBigDecimalField Amount;
    @ViewComponent
    private JmixBigDecimalField amount;

    @Subscribe(id = "cancelButton", subject = "clickListener")
    public void onCancelButtonClick(final ClickEvent<JmixButton> event) {
        closeWithDefaultAction();
    }

    public void setClient(Client client){
        clientsComboBox.setValue(client);
    }

    @Subscribe(id = "requestButton", subject = "clickListener")
    public void onRequestButtonClick(final ClickEvent<JmixButton> event) {
        ValidationErrors errors = viewValidation.validateUiComponents(mainBox);
        if(!errors.isEmpty()){
            viewValidation.showValidationErrors(errors);
        }
        Loan loan = dataManager.create(Loan.class);
        loan.setClient(clientsComboBox.getValue());
        loan.setAmount(amount.getValue());
        loan.setRequestDate(LocalDate.now());
        loan.setStatus(LoanStatus.REQUESTED);
        dataManager.save(loan);
        closeWithDefaultAction();
    }
}