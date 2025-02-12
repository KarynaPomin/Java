package Classes;

public class FilesItem {
    public String pesel;
    public String name;
    public String secondname;
    public String fullName;

    public FilesItem(){

    }

    public FilesItem(String pesel, String name, String secondname) {
        this.pesel = pesel;
        this.name = name;
        this.secondname = secondname;

        getFullName();
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

    public String getFullName() {
        return name + " " + secondname;
    }

    public void setFullName(String name, String secondname) {
        fullName = name + " " + secondname;
    }
}
