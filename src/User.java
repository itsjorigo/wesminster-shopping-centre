import java.util.ArrayList;

public class User {

    private static ArrayList<User> users;
    private ArrayList<Product> productHistory= new ArrayList<>();
    private String username;
    private String password;

    public User(ArrayList<Product> productHistory, String username, String password) {
        this.productHistory = productHistory;
        this.username = username;
        this.password = password;
    }

    public static ArrayList<User> getUsers() {
        if (users == null) { users = new ArrayList<>(); }
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    public ArrayList<Product> getProductHistory() {
        return productHistory;
    }

    public void setProductHistory(ArrayList<Product> productHistory) {
        this.productHistory = productHistory;
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
}
