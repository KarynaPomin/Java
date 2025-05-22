package Classes;

import javafx.beans.property.*;

public class Person {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final IntegerProperty age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.age = new SimpleIntegerProperty(age);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }

    public int getAge() {
        return age.get();
    }
    public IntegerProperty ageProperty() {
        return age;
    }
}
