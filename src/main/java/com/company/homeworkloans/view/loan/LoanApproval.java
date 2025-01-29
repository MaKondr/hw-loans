package com.company.homeworkloans.view.loan;

import com.company.homeworkloans.entity.Loan;
import com.company.homeworkloans.entity.LoanStatus;
import com.company.homeworkloans.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "LoanApproval", layout = MainView.class)
@ViewController(id = "LoanApproval.list")
@ViewDescriptor(path = "LoanApproval.xml")
@LookupComponent("loansDataGrid")
@DialogMode(width = "64em")
public class LoanApproval extends StandardListView<Loan> {
    @ViewComponent
    private DataGrid<Loan> loansDataGrid;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;
    @ViewComponent
    private CollectionLoader<Loan> loansDl;
    @ViewComponent
    private CollectionLoader<Loan> previousLoansDl;
    @ViewComponent
    private CollectionContainer<Loan> previousLoansDc;

    @Subscribe(id = "approveBtn", subject = "clickListener")
    public void onApproveBtnClick(final ClickEvent<JmixButton> event) {
        Loan singleSelectedItem = loansDataGrid.getSingleSelectedItem();

        if(singleSelectedItem == null) {
            notifications.create("Выберете запись").show();
            return;
        }

        singleSelectedItem.setStatus(LoanStatus.APPROVED);
        dataManager.save(singleSelectedItem);
        notifications.create("Success", "Approved")
                .withType(Notifications.Type.SUCCESS)
                .withPosition(Notification.Position.TOP_END)
                .withDuration(3000)
                .show();
        loansDl.load();

    }

    @Subscribe(id = "rejectBtn", subject = "clickListener")
    public void onRejectBtnClick(final ClickEvent<JmixButton> event) {
        Loan singleSelectedItem = loansDataGrid.getSingleSelectedItem();

        if(singleSelectedItem == null) {
            notifications.create("Выберете запись").show();
            return;
        }

        singleSelectedItem.setStatus(LoanStatus.REJECTED);
        dataManager.save(singleSelectedItem);
        notifications.create("Success", "Rejected")
                .withType(Notifications.Type.SUCCESS)
                .withPosition(Notification.Position.TOP_END)
                .withDuration(3000)
                .show();
        loansDl.load();

    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        loansDl.load();
    }

    @Subscribe(id = "loansDc", target = Target.DATA_CONTAINER)
    public void onLoansDcItemChange(final InstanceContainer.ItemChangeEvent<Loan> event) {
        if (event.getItem() == null) {
            previousLoansDc.getMutableItems().clear();
            return;
        }
        previousLoansDl.setParameter("client", event.getItem().getClient());
        previousLoansDl.setParameter("loan", event.getItem());
        previousLoansDl.load();
    }

    


}