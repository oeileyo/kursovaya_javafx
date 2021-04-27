package sample.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import sample.models.Category;
import sample.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class ApiSession {
    private static final String url = "http://localhost:8080";

    public void createEmployee(Employee employee) {
        System.out.println(employee.toJson());
        HttpClass.PostRequest(url + "/employees", employee.toJson());
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
        String jsonString = employee.toJsonPut();
        HttpClass.PutRequest(url + "/employees/" + id, jsonString);
    }

    public boolean deleteEmployee(Employee employee) {
        Long id = employee.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(url + "/employees/" + id);
    }



    public void createCategory(Category category) {
        System.out.println(category.toJson());
        HttpClass.PostRequest(url + "/category", category.toJson());
    }

    public List<Category> getAllCategories() {
        List<Category> result = new ArrayList<>();
        String answer = HttpClass.GetRequest(url + "/category");
        System.out.println(answer);

        if (answer != null) {
            JsonArray jsonAnswer = JsonParser.parseString(answer).getAsJsonArray();
            System.out.println(jsonAnswer);

            for (int i = 0; i < jsonAnswer.size(); i++) {
                JsonObject currentCategory = jsonAnswer.get(i).getAsJsonObject();
                result.add(categoryFromJson(currentCategory));
            }
        }
        return result;
    }

    public Employee getCategoryById(Long id) {
        String answer = HttpClass.GetRequest(url + "/category/"+id);
        JsonObject currentCategory = JsonParser.parseString(answer).getAsJsonObject();
        if (currentCategory != null){
            return employeeFromJson(currentCategory);
        }
        return null;
    }


    public Category categoryFromJson(JsonObject currentCategory){
        if (currentCategory.get("id") != null) {
            Long id = currentCategory.get("id").getAsLong();
            String name = currentCategory.get("name").getAsString();
            Integer price = currentCategory.get("price").getAsInt();
            Category category = new Category(id, name, price);
            return category;
        } else {
            String name = currentCategory.get("name").getAsString();
            Integer price = currentCategory.get("price").getAsInt();
            Category category = new Category(name, price);
            return category;
        }
    }

    public void updateCategories(Category category) {
        Long id = category.getId();
        String jsonString = category.toJsonPut();
//        System.out.println("!!!!  " + jsonString);
        HttpClass.PutRequest(url + "/category/" + id, jsonString);
    }

    public boolean deleteCategory(Category category) {
        Long id = category.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(url + "/category/" + id);
    }
}
