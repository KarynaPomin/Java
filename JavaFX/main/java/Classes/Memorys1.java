package Classes;

import javafx.collections.ObservableList;

import java.io.*;

public enum Memory {

    INSTANCE;

    private String username;
    private String password;
    private String zusDraPersonsFile;
    private String infdpPersonsFile;
    private ObservableList<Person> selectedPersonsList;

    private static final String FILE_PATH = "C:\\JavaFX\\ExpertImportExport\\src\\main\\memory_state.txt";

    private void saveState(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(text);
            writer.newLine();
            System.out.println("Saved to file: " + text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadState() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("📄 Loaded from file: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void build(User user) {
        this.username = user.name;
        this.password = user.password;

        saveState("Login user: " + getUsername());
        System.out.println("Zalogowano: " + username + " " + password);
    }

    public void logOut(){
        this.username = null;
        this.password = null;
        this.zusDraPersonsFile = null;
        this.infdpPersonsFile = null;
        this.selectedPersonsList = null;

        saveState("Wylogowano user: " + getUsername());
        System.out.println("User logged out.");
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

        saveState("Dodano nowy plik: " + zusDraPersonsFile);
        System.out.println("Dodano nowy plik: " + zusDraPersonsFile);
    }

    public String getInfdpPersonsFile() {
        return infdpPersonsFile;
    }

    public void setInfdpPersonsFile(String infdpPersonsFile) {
        this.infdpPersonsFile = infdpPersonsFile;

        saveState("Dodano wysłany plik: " + infdpPersonsFile);
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
