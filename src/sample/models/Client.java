package sample.models;

import com.google.gson.Gson;
import javafx.beans.property.*;

import java.util.HashMap;
import java.util.Map;

public class Client implements ApiModel{

    private LongProperty id;
    private StringProperty first_name;
    private StringProperty last_name;
    private StringProperty phone;
//    private ObjectProperty<Company> company;

    public Client(String first_name, String last_name, String phone) {
        this.id = null;
        this.first_name = new SimpleStringProperty(first_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.phone = new SimpleStringProperty(phone);
        //        this.company = new SimpleObjectProperty<Company>(company);
    }

    public Client(Long id, String first_name, String last_name, String phone) {
        this.id = new SimpleLongProperty(id);
        this.first_name = new SimpleStringProperty(first_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.phone = new SimpleStringProperty(phone);
//        this.company = new SimpleObjectProperty<Company>(company);
    }

    public Client() {
        this.id = null;
        this.first_name = null;
        this.last_name = null;
        this.phone = null;
    }


    public long getId() { return id.get(); }

    public String getFirst_name() { return first_name.get(); }

    public String getLast_name() { return last_name.get(); }

    public String getPhone() { return phone.get(); }


    public void setId(long id) { this.id.set(id); }

    public void setFirst_name(String first_name) { this.first_name.set(first_name); }

    public void setLast_name(String last_name) { this.last_name.set(last_name); }

    public void setPhone(String phone) { this.phone.set(phone); }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();
        map.put("first_name", getFirst_name());
        map.put("last_name", getLast_name());
        map.put("phone", getPhone());
        return gson.toJson(map);
    }

    @Override
    public String toJsonWithId() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.get());
        map.put("first_name", getFirst_name());
        map.put("last_name", getLast_name());
        map.put("phone", getPhone());
        return gson.toJson(map);
    }
}
