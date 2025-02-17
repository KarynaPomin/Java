package Classes;

import javafx.collections.ObservableList;

import java.io.File;

public enum Memory {

    INSTANCE;

    private String username;
    private String password;
    private String zusDraPersonsFile;
    private String infdpPersonsFile;
    private ObservableList<Person> selectedPersonsList;

    void build(User user) {
        this.username = user.name;
        this.password = user.password;
    }

    public void print() {
        System.out.println("Zalogowano: " + username + " " + password);
    }

    public void logOut(){
        this.username = null;
        this.password = null;
        this.zusDraPersonsFile = null;
        this.infdpPersonsFile = null;
        this.selectedPersonsList = null;

        System.out.println("Wylogowano");
    }

    public static Memory getInstance() {
        return INSTANCE;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZusDraPersonsFile() {
        return zusDraPersonsFile;
    }

    public void setZusDraPersonsFile(String zusDraPersonsFile) {
        this.zusDraPersonsFile = zusDraPersonsFile;
        System.out.println("Dodano nowy: " + zusDraPersonsFile);
    }

    public String getInfdpPersonsFile() {
        return infdpPersonsFile;
    }

    public void setInfdpPersonsFile(String infdpPersonsFile) {
        this.infdpPersonsFile = infdpPersonsFile;
        System.out.println("Dodano wysłany: " + infdpPersonsFile);
    }

    public ObservableList<Person> getSelectedPersonsList() {
        for(Person person : selectedPersonsList) {
            System.out.println(person.getPesel());
        }
        System.out.println("\n");

        return selectedPersonsList;
    }

    public void setSelectedPersonsList(ObservableList<Person> selectedPersonsList) {
        this.selectedPersonsList = selectedPersonsList;
    }
}
