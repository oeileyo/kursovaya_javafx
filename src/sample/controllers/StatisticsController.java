package sample.controllers;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import sample.models.Appointment;
import sample.models.Category;
import sample.models.Employee;
import sample.utils.ApiSession;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class StatisticsController {
    @FXML
    private PieChart pieChart;

    @FXML
    private PieChart pieChart2;

    private ApiSession apiSession = new ApiSession();
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        employeeStatistics();
        categoryStatistics();
    }

    public void employeeStatistics() {
        List<Appointment> appointmentList = apiSession.getAllAppointments();
        List<Long> listOfAppointmentsByEmployeeId = new ArrayList<>();

        for (Appointment appointment : appointmentList){
            listOfAppointmentsByEmployeeId.add(appointment.getEmployee_id());
        }

        List<Employee> employeeList = apiSession.getAllEmployees();
        for (Employee employee : employeeList){
            Integer count = 0;
            for (Long i : listOfAppointmentsByEmployeeId){
                if (i == employee.getId()){
                    count += 1;
                }
            }
            pieChartData.add(new PieChart.Data(employee.getFirst_name(), count));
        }
        pieChart.setData(pieChartData);
        pieChart.setTitle("(Отражает количество записей к каждому изсотрудников)");
    }

    public void categoryStatistics() {
        List<Appointment> appointmentList = apiSession.getAllAppointments();
        List<Long> listOfAppointmentsByCategoryId = new ArrayList<>();

        for (Appointment appointment : appointmentList){
            listOfAppointmentsByCategoryId.add(appointment.getCategory_id());
        }

        List<Category> categoryList = apiSession.getAllCategories();
        for (Category category : categoryList){
            Integer count = 0;
            for (Long i : listOfAppointmentsByCategoryId){
                if (i == category.getId()){
                    count += 1;
                }
            }
            pieChartData2.add(new PieChart.Data(category.getName(), count));
        }
        pieChart2.setData(pieChartData2);
        pieChart2.setTitle("(Отражает количество записей к каждому изсотрудников)");
    }
}
