package com.company.homeworkloans.view.requestloan;


import com.company.homeworkloans.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route(value = "RequestLoan", layout = MainView.class)
@ViewController(id = "Requestloan")
@ViewDescriptor(path = "RequestLoan.xml")
public class Requestloan extends StandardView {
    private static final Logger log = LoggerFactory.getLogger(Requestloan.class);

    @Subscribe(id = "requestButton", subject = "clickListener")
    public void onRequestButtonClick(final ClickEvent<JmixButton> event) {
        log.info("INFO");
    }
}