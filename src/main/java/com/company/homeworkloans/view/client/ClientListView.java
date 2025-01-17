package com.company.homeworkloans.view.client;

import com.company.homeworkloans.entity.Client;
import com.company.homeworkloans.view.main.MainView;
import com.company.homeworkloans.view.requestloan.Requestloan;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.event.view.ViewOpenedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

@Route(value = "clients", layout = MainView.class)
@ViewController("Client.list")
@ViewDescriptor("client-list-view.xml")
@LookupComponent("clientsDataGrid")
@DialogMode(width = "64em")
public class ClientListView extends StandardListView<Client> {
    private static final Logger log = LoggerFactory.getLogger(ClientListView.class);
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private DialogWindows dialogWindows;

    @Subscribe("requestLoan")
    public void onRequestLoanClick(ClickEvent<JmixButton> event) {
        dialogWindows.view(this, Requestloan.class)
                .withAfterCloseListener(afterCloseEvent -> {
                    // Действие после закрытия диалога
                    System.out.println("Dialog closed");
                })
                .open();
    }

}