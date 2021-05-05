package sample.models;

import com.google.gson.Gson;
import javafx.beans.property.*;

import java.util.HashMap;
import java.util.Map;

public class Client implements ApiModel{

    private LongProperty id;
    private StringProperty name;
    private IntegerProperty phone;

    public Client(String name,Integer phone) {
        this.id = null;
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleIntegerProperty(phone);
    }

    public Client(Long id, String name, Integer phone) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleIntegerProperty(phone);
//        this.company = new SimpleObjectProperty<Company>(company);
    }

    public Client() {
        this.id = null;
        this.name = null;
        this.phone = null;
    }


    public long getId() { return id.get(); }

    public String getName() { return name.get(); }

    public Integer getPhone() { return phone.get(); }


    public void setId(long id) { this.id.set(id); }

    public void setName(String name) { this.name.set(name); }

    public void setPhone(Integer phone) { this.phone.set(phone); }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("name", getName());
        map.put("phone", getPhone());
        return gson.toJson(map);
    }

    @Override
    public String toJsonWithId() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.get());
        map.put("name", getName());
        map.put("phone", getPhone());
        return gson.toJson(map);
    }
}
