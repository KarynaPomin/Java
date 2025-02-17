package Classes;

public class User {
    public String name;
    public String login;
    public String password;

    public User(){
        name = null;
        login = null;
        password = null;
    }

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public void build(){
        Memory.INSTANCE.build(this);
    }
}
