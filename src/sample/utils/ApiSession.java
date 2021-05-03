package sample.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import sample.models.Appointment;
import sample.models.Category;
import sample.models.Client;
import sample.models.Employee;

import java.time.LocalDate;
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
//        System.out.println(answer);

        if (answer != null) {
            JsonArray jsonAnswer = JsonParser.parseString(answer).getAsJsonArray();
//            System.out.println(jsonAnswer);

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
        String jsonString = employee.toJsonWithId();
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
//        System.out.println(answer);

        if (answer != null) {
            JsonArray jsonAnswer = JsonParser.parseString(answer).getAsJsonArray();
//            System.out.println(jsonAnswer);

            for (int i = 0; i < jsonAnswer.size(); i++) {
                JsonObject currentCategory = jsonAnswer.get(i).getAsJsonObject();
                result.add(categoryFromJson(currentCategory));
            }
        }
        return result;
    }

    public Category getCategoryById(Long id) {
        String answer = HttpClass.GetRequest(url + "/category/"+id);
        JsonObject currentCategory = JsonParser.parseString(answer).getAsJsonObject();
        if (currentCategory != null){
            return categoryFromJson(currentCategory);
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
        String jsonString = category.toJsonWithId();
//        System.out.println("!!!!  " + jsonString);
        HttpClass.PutRequest(url + "/category/" + id, jsonString);
    }

    public boolean deleteCategory(Category category) {
        Long id = category.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(url + "/category/" + id);
    }


    public void createAppointment(Appointment appointment) {
        System.out.println(appointment.toJson());
        HttpClass.PostRequest(url + "/appointment", appointment.toJson());
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> result = new ArrayList<>();
        String answer = HttpClass.GetRequest(url + "/appointment");
        System.out.println(answer);

        if (answer != null) {
            JsonArray jsonAnswer = JsonParser.parseString(answer).getAsJsonArray();
            System.out.println(jsonAnswer);

            for (int i = 0; i < jsonAnswer.size(); i++) {
                JsonObject currentAppointment = jsonAnswer.get(i).getAsJsonObject();
                result.add(appointmentFromJson(currentAppointment));
            }
        }
        return result;
    }

    public Appointment getAppointmentById(Long id) {
        String answer = HttpClass.GetRequest(url + "/appointment/"+id);
        JsonObject currentAppointment = JsonParser.parseString(answer).getAsJsonObject();
        if (currentAppointment != null){
            return appointmentFromJson(currentAppointment);
        }
        return null;
    }


    public Appointment appointmentFromJson(JsonObject currentAppointment){
        if (currentAppointment.get("id") != null) {
            if (!currentAppointment.get("status").getAsBoolean()){ // запись занята
                Long id = currentAppointment.get("id").getAsLong();
                Long employee_id = currentAppointment.get("employee_id").getAsLong();
                String employee_name = getEmployeeById(employee_id).getFirst_name()+" "+getEmployeeById(employee_id).getLast_name();
                String date = currentAppointment.get("date").getAsString();
                String time = currentAppointment.get("time").getAsString();
                String status = (currentAppointment.get("status").getAsBoolean() ? "Свободно" : "Занято");
                Long client_id = currentAppointment.get("client_id").getAsLong();
                String client_name = getClientById(client_id).getFirst_name()+" "+getClientById(client_id).getLast_name();
                Long category_id = currentAppointment.get("category_id").getAsLong();
                String category_name = getCategoryById(category_id).getName();
                Integer price = getCategoryById(category_id).getPrice();
                Appointment appointment = new Appointment(id, employee_id, employee_name, date, time,
                        status, client_id, client_name, category_id, category_name, price);
                return appointment;
            } else { // запись свободна
                Long id = currentAppointment.get("id").getAsLong();
                Long employee_id = currentAppointment.get("employee_id").getAsLong();
                String employee_name = getEmployeeById(employee_id).getFirst_name()+" "+getEmployeeById(employee_id).getLast_name();
                String date = currentAppointment.get("date").getAsString();
                String time = currentAppointment.get("time").getAsString();
                String status = (currentAppointment.get("status").getAsBoolean() ? "Свободно" : "Занято");
                Appointment appointment = new Appointment(id, employee_id, employee_name, date, time, status);
                return appointment;
            }
        } else {
            if (!currentAppointment.get("status").getAsBoolean()) {
                Long employee_id = currentAppointment.get("employee_id").getAsLong();
                String employee_name = getEmployeeById(employee_id).getFirst_name()+" "+getEmployeeById(employee_id).getLast_name();
                String date = currentAppointment.get("date").getAsString();
                String time = currentAppointment.get("time").getAsString();
                String status = (currentAppointment.get("status").getAsBoolean() ? "Свободно" : "Занято");
                Long client_id = currentAppointment.get("client_id").getAsLong();
                String client_name = getClientById(client_id).getFirst_name()+" "+getClientById(client_id).getLast_name();
                Long category_id = currentAppointment.get("category_id").getAsLong();
                String category_name = getCategoryById(category_id).getName();
                Integer price = getCategoryById(category_id).getPrice();
                Appointment appointment = new Appointment(employee_id, employee_name, date, time,
                        status, client_id, client_name, category_id, category_name, price);
                return appointment;
            } else {
                Long employee_id = currentAppointment.get("employee_id").getAsLong();
                String employee_name = getEmployeeById(employee_id).getFirst_name()+" "+getEmployeeById(employee_id).getLast_name();
                String date = currentAppointment.get("date").getAsString();
                String time = currentAppointment.get("time").getAsString();
                String status = (currentAppointment.get("status").getAsBoolean() ? "Свободно" : "Занято");
                Appointment appointment = new Appointment(employee_id, employee_name, date, time, status);
                return appointment;
            }
        }
    }

    public void updateAppointment(Appointment appointment) {
        Long id = appointment.getId();
        String jsonString = appointment.toJsonWithId();
        System.out.println("!!!!  " + jsonString);
        HttpClass.PutRequest(url + "/appointment/" + id, jsonString);
    }

    public boolean deleteAppointment(Appointment appointment) {
        Long id = appointment.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(url + "/appointment/" + id);
    }


    public void createClient(Client client) {
        System.out.println(client.toJson());
        HttpClass.PostRequest(url + "/client", client.toJson());
    }

    public List<Client> getAllClients() {
        List<Client> result = new ArrayList<>();
        String answer = HttpClass.GetRequest(url + "/client");
//        System.out.println(answer);

        if (answer != null) {
            JsonArray jsonAnswer = JsonParser.parseString(answer).getAsJsonArray();
//            System.out.println(jsonAnswer);

            for (int i = 0; i < jsonAnswer.size(); i++) {
                JsonObject currentClient = jsonAnswer.get(i).getAsJsonObject();
                result.add(clientFromJson(currentClient));
            }
        }
        return result;
    }

    public Client getClientById(Long id) {
        String answer = HttpClass.GetRequest(url + "/client/"+id);
        JsonObject currentClient = JsonParser.parseString(answer).getAsJsonObject();
        if (currentClient != null){
            return clientFromJson(currentClient);
        }
        return null;
    }


    public Client clientFromJson(JsonObject currentClient){
        if (currentClient.get("id") != null){
            Long id = currentClient.get("id").getAsLong();
            String first_name = currentClient.get("first_name").getAsString();
            String last_name = currentClient.get("last_name").getAsString();
            String phone = currentClient.get("phone").getAsString();
            //        List<Appointment> appointmentList = currentEmployee.get("appointmentList").getAs
            Client client = new Client(id, first_name, last_name, phone);
            return client;
        } else {
            String first_name = currentClient.get("first_name").getAsString();
            String last_name = currentClient.get("last_name").getAsString();
            String phone = currentClient.get("phone").getAsString();
            //        List<Appointment> appointmentList = currentEmployee.get("appointmentList").getAs
            Client client = new Client(first_name, last_name, phone);
            return client;
        }
    }

    public boolean deleteClient(Client client) {
        Long id = client.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(url + "/client/" + id);
    }
}
