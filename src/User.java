import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static ArrayList<User> users;
    private ArrayList<Product> productHistory= new ArrayList<>();
    private String username;
    private String password;
    private static final String userFile = "users.txt";


    public User(ArrayList<Product> productHistory, String username, String password) {
        this.productHistory = productHistory;
        this.username = username;
        this.password = password;
    }

    public static ArrayList<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public static void setUsers(User user) {
        users.add(user);
    }

    public ArrayList<Product> getProductHistory() {
        return productHistory;
    }

    public void setProductHistory(Product product) {
        if (productHistory == null) {
            productHistory = new ArrayList<>();
        }
        productHistory.add(product);    }

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

    static public void saveUsers(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(userFile))) {
            out.writeObject(users);
            System.out.println("Users saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

    static public void loadUsers(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(userFile))) {
            users = (ArrayList<User>) in.readObject();
            System.out.println("Users loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
            e.printStackTrace(); // Add this line for debugging
        }
    }


}
