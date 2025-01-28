package com.company.homeworkloans.view.loan;

import com.company.homeworkloans.entity.Loan;
import com.company.homeworkloans.view.main.MainView;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Route(value = "LoanApproval", layout = MainView.class)
@ViewController(id = "LoanApproval.list")
@ViewDescriptor(path = "LoanApproval.xml")
@LookupComponent("loansDataGrid")
@DialogMode(width = "64em")
public class LoanApproval extends StandardListView<Loan> {

}