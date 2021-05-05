package sample.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.beans.property.*;
import sample.utils.DateUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Appointment implements ApiModel {

    private LongProperty id;
    private LongProperty employee_id;
    private StringProperty employee_name;
    private StringProperty date;
    private StringProperty time;
    private LongProperty client_id;
    private StringProperty client_name;
    private LongProperty category_id;
    private StringProperty category_name;
    private IntegerProperty price;

    // занятая запись
    public Appointment(Long id, Long employee_id, String employee_name, String date,
                       String time, Long client_id, String client_name, Long category_id,
                       String category_name, Integer price) {
        this.id = new SimpleLongProperty(id);
        this.employee_id = new SimpleLongProperty(employee_id);
        this.employee_name = new SimpleStringProperty(employee_name);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.client_id = new SimpleLongProperty(client_id);
        this.client_name = new SimpleStringProperty(client_name);
        this.category_id = new SimpleLongProperty(category_id);
        this.category_name = new SimpleStringProperty(category_name);
        this.price = new SimpleIntegerProperty(price);
    }

    // новая занятая запись
    public Appointment(Long employee_id, String employee_name, String date,
                       String time, Long client_id, String client_name, Long category_id,
                       String category_name, Integer price) {
        this.id = null;
        this.employee_id = new SimpleLongProperty(employee_id);
        this.employee_name = new SimpleStringProperty(employee_name);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.client_id = new SimpleLongProperty(client_id);
        this.client_name = new SimpleStringProperty(client_name);
        this.category_id = new SimpleLongProperty(category_id);
        this.category_name = new SimpleStringProperty(category_name);
        this.price = new SimpleIntegerProperty(price);
    }

    public Appointment(Long employee_id, String date, String time, Long client_id, Long category_id) {
        this.id = null;
        this.employee_id = new SimpleLongProperty(employee_id);
        this.employee_name = null;
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.client_id = new SimpleLongProperty(client_id);
        this.client_name = null;
        this.category_id = new SimpleLongProperty(category_id);
        this.category_name = null;
        this.price = null;
    }

    // пустая запись
    public Appointment() {
        this.id = null;
        this.employee_id = null;
        this.employee_name = null;
        this.date = null;
        this.time = null;
        this.client_id = null;
        this.client_name = null;
        this.category_id = null;
        this.category_name = null;
        this.price = null;
    }


    public long getId() { return id.get(); }

    public long getEmployee_id() { return employee_id.get(); }

    public String getEmployee_name() { return employee_name.get(); }

    public String getDate() { return date.get(); }

    public String getTime() { return time.get(); }

    public long getClient_id() { return client_id.get(); }

    public String getClient_name() { return client_name.get(); }

    public long getCategory_id() { return category_id.get(); }

    public String getCategory_name() { return category_name.get(); }

    public int getPrice() { return price.get(); }


    public void setId(long id) { this.id.set(id); }

    public void setEmployee_id(long employee_id) { this.employee_id.set(employee_id); }

    public void setEmployee_name(String employee_name) { this.employee_name.set(employee_name); }

    public void setDate(String date) { this.date.set(date); }

    public void setTime(String time) { this.time.set(time); }

    public void setClient_id(Long client_id) { this.client_id.set(client_id); }

    public void setClient_name(String client_name) { this.client_name.set(client_name); }

    public void setCategory_id(Long category_id) { this.category_id.set(category_id); }

    public void setCategory_name(String category_name) { this.category_name.set(category_name); }

    public void setPrice(int price) { this.price.set(price); }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("date", date.get());
        map.put("time", time.get());
        map.put("employee_id", employee_id.get());
        map.put("client_id", client_id.get());
        map.put("category_id", category_id.get());
        return gson.toJson(map);
    }

    @Override
    public String toJsonWithId() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.get());
        map.put("date", date.get());
        map.put("time", time.get());
        map.put("employee_id", employee_id.get());
        map.put("client_id", client_id.get());
        map.put("category_id", category_id.get());
        return gson.toJson(map);
    }
}
