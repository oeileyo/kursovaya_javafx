package sample.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import sample.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class ApiSession {
    private static final String url = "http://localhost:8080";

    public void createEmployee(Employee employee) {
        System.out.println(employee.toJson());
        HttpClass.PostRequest(url + "/employee", employee.toJson());
    }

    public List<Employee> getAllEmployees() {
        List<Employee> result = new ArrayList<>();
        String answer = HttpClass.GetRequest(url + "/employees");
        System.out.println(answer);

        if (answer != null) {
            JsonArray jsonAnswer = JsonParser.parseString(answer).getAsJsonArray();
            System.out.println(jsonAnswer);

            for (int i = 0; i < jsonAnswer.size(); i++) {
                JsonObject currentEmployee = jsonAnswer.get(i).getAsJsonObject();
                result.add(employeeFromJson(currentEmployee));
            }
        }
        return result;
    }

    public Employee getEmployeeById(Long id) {
        String answer = HttpClass.GetRequest(url + "/employees/"+id);
        JsonObject currentEmployee = JsonParser.parseString(answer).getAsJsonObject();
        if (currentEmployee != null){
            return employeeFromJson(currentEmployee);
        }
        return null;
    }


    public Employee employeeFromJson(JsonObject currentEmployee){
        if (currentEmployee.get("id") != null){
            Long id = currentEmployee.get("id").getAsLong();
            String first_name = currentEmployee.get("first_name").getAsString();
            String last_name = currentEmployee.get("last_name").getAsString();
    //        List<Appointment> appointmentList = currentEmployee.get("appointmentList").getAs
            Employee employee = new Employee(id, first_name, last_name);
            return employee;
        } else {
            String first_name = currentEmployee.get("first_name").getAsString();
            String last_name = currentEmployee.get("last_name").getAsString();
            //        List<Appointment> appointmentList = currentEmployee.get("appointmentList").getAs
            Employee employee = new Employee(first_name, last_name);
            return employee;
        }
    }

    public void updateEmployee(Employee employee) {
        Long id = employee.getId();
        String jsonString = employee.toJson();
        HttpClass.PutRequest(url + "/employees/" + id, jsonString);
    }

    public boolean deleteEmployee(Employee employee) {
        Long id = employee.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(url + "/employees/" + id);
    }

}
