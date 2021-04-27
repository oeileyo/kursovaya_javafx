package sample.models;

import com.google.gson.Gson;
import javafx.beans.property.*;
import javafx.util.converter.IntegerStringConverter;

import java.util.HashMap;
import java.util.Map;

public class Category implements ApiModel {

    private LongProperty id;
    private StringProperty name;
    private IntegerProperty price;

//    private ObjectProperty<Company> company;

    public Category(String name, Integer price) {
        this.id = null;
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
        //        this.company = new SimpleObjectProperty<Company>(company);
    }

    public Category(Long id, String name, Integer price) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
//        this.company = new SimpleObjectProperty<Company>(company);
    }

    public Category() {
        this.id = null;
        this.name = null;
        this.price = null;
    }

    public Long getId() { return id.get(); }

    public LongProperty idProperty() { return id; }

    public void setId(Long id) { this.id = new SimpleLongProperty(id); }


    public String getName() { return name.get(); }

    public StringProperty nameProperty() { return name; }

    public void setName(String name) { this.name = new SimpleStringProperty(name); }


    public Integer getPrice() { return price.get(); }

    public IntegerProperty priceProperty() { return price; }

    public void setPrice(Integer price) { this.price = new SimpleIntegerProperty(price); }


    @Override
    public String toJson() {
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();
        map.put("name", getName());
        map.put("price", String.valueOf(getPrice()));
        return gson.toJson(map);
    }

    @Override
    public String toJsonPut() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.get());
        map.put("name", name.get());
        map.put("price", String.valueOf(getPrice()));
        return gson.toJson(map);
    }
}
