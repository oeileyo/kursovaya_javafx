package sample.models;

import com.google.gson.Gson;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class Employee implements ApiModel{

    private LongProperty id;
    private StringProperty first_name;
    private StringProperty last_name;

//    private ObjectProperty<Company> company;

    public Employee(Long id, String first_name, String last_name){
        this.id = new SimpleLongProperty(id);
        this.first_name = new SimpleStringProperty(first_name);
        this.last_name = new SimpleStringProperty(last_name);
//        this.company = new SimpleObjectProperty<Company>(company);
    }

    public Employee(String first_name, String last_name){
        this.id = null;
        this.first_name = new SimpleStringProperty(first_name);
        this.last_name = new SimpleStringProperty(last_name);
        //        this.company = new SimpleObjectProperty<Company>(company);
    }

    public Employee(){
        this(null, null, null);
    }


    public Long getId() {return id.get();};

    public String getFirst_name() {
        return first_name.get();
    }

    public String getLast_name() { return last_name.get(); }

    public LongProperty get_id_property() { return id; }

    public StringProperty get_first_name_property() { return first_name; }

    public StringProperty get_last_name_property() {
        return last_name;
    }

    public void setId(Long id) { this.id = new SimpleLongProperty((id)); }

    public void setFirst_name(String first_name) {
        this.first_name = new SimpleStringProperty(first_name);
    }

    public void setLast_name(String last_name) {
        this.last_name =  new SimpleStringProperty(last_name);
    }


    public boolean alreadyExists(Long id_){ if (this.getId() == id_) {return true;} else { return false; } }


    @Override
    public String toJson() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("first_name", getFirst_name());
        map.put("last_name", getLast_name());
//        map.put("company", new Gson().fromJson(getCompany().toJSON(), JsonObject.class));
        return gson.toJson(map);
    }
}
