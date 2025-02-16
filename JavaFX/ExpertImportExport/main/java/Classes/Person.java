package Classes;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Person {
    private BooleanProperty active;
    public Integer id;
    public String pesel;
    public String name;
    public String secondname;

    public boolean isInList;

    public Person() {
        this.active = new SimpleBooleanProperty(false);
    }

    public Person(Integer id, String pesel, String name, String secondname) {
        this.active = new SimpleBooleanProperty(false);
        this.id = id;
        this.pesel = pesel;
        this.name = name;
        this.secondname = secondname;
        isInList = false;

        getFullName();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public Boolean getIsInList() {
        return isInList;
    }

    public void setIsInList(boolean bool) {
        isInList = bool;
    }

    public String getFullName() {
        return name + " " + secondname;
    }
}
