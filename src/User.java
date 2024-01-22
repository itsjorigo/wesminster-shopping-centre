import java.io.*;
import java.util.ArrayList;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static ArrayList<User> users;
    private ArrayList<Product> productHistory= new ArrayList<>();
    private String username;
    private String password;
    private static final String userFile = "users.txt";


    public User(String username, String password, ArrayList<Product> productHistory ) {
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

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
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

    public static void saveUsers( ArrayList<User> abc){
        ArrayList<User> existingUsers = loadUsers();

        // Add new users to the loaded list
        if (existingUsers == null) {
            existingUsers = new ArrayList<>();
        }
        existingUsers.addAll(abc);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(userFile))) {
            out.writeObject(existingUsers);
            System.out.println(existingUsers);
            System.out.println("Users saved to file.");

        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

    static public ArrayList<User> loadUsers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(userFile))) {
            users = (ArrayList<User>) in.readObject();
            System.out.println("Users loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
            e.printStackTrace(); // Add this line for debugging
        }
        return users;
    }
}
