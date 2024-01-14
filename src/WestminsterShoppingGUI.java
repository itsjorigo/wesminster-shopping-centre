import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WestminsterShoppingGUI {
    private JFrame login;
    private JFrame signIn;
    private JFrame productSelectInterface;
    private JFrame shoppingCart;


    public WestminsterShoppingGUI() {
        login = userLogin();
        signIn = userSignIn();
        productSelectInterface = productSelectInterface();
        shoppingCart = shoppingCart();
    }

    public JFrame userLogin() {
        JFrame login = new JFrame("User Login Page");

        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel userNamePanel = new JLabel("Username: ");
        JTextField userNameField = new JTextField(20);

        JLabel passwordPanel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            // Add login functionality here
            // For now, let's just display a message
//            JOptionPane.showMessageDialog(login, "Login button clicked!");
            productSelectInterface.setVisible(true);
            login.setVisible(false);
        });

        JButton signupButton = new JButton("Signup");
        signupButton.addActionListener(e -> {
            // Add signup functionality here
            // For now, let's just display a message
            signIn.setVisible(true); // Show the signIn frame
            login.setVisible(false); // Hide the login frame
        });

        loginPanel.add(userNamePanel);
        loginPanel.add(userNameField);
        loginPanel.add(passwordPanel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(signupButton);

        login.add(loginPanel);
        return login;
    }

    public JFrame userSignIn() {
        JFrame signIn = new JFrame("User SignIn Page");

        JPanel signInPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel userNamePanel = new JLabel("Enter a username: ");
        JTextField userNameField = new JTextField(20);

        JLabel passwordPanel1 = new JLabel("Enter a password: ");
        JPasswordField passwordField1 = new JPasswordField(20);

        JLabel passwordPanel2 = new JLabel("Enter password again: ");
        JPasswordField passwordField2 = new JPasswordField(20);

        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(e -> {
            // Add signIn functionality here
            // For now, let's just display a message
            JOptionPane.showMessageDialog(signIn, "SignIn button clicked!");
        });

        signInPanel.add(userNamePanel);
        signInPanel.add(userNameField);
        signInPanel.add(passwordPanel1);
        signInPanel.add(passwordField1);
        signInPanel.add(passwordPanel2);
        signInPanel.add(passwordField2);
        signInPanel.add(signInButton);

        signIn.add(signInPanel);
        signIn.setSize(300, 250); // Adjusted the height
        signIn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the signIn frame, not the entire application

        return signIn;
    }

    public JFrame productSelectInterface(){
        JFrame productSelect = new JFrame("Westminster Shopping Centre");

        JPanel productSelectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel categoryPanel = new JLabel("Select Product Category: ");

        String[] options = {"All", "Electronics", "Clothing"};
        JComboBox<String> categoryComboBox = new JComboBox<>(options);

        JButton shoppingCartButton = new JButton("Sign In");
        shoppingCartButton.addActionListener(e -> {
            // Add signIn functionality here
            // For now, let's just display a message
//            JOptionPane.showMessageDialog(productSelect, "Shopping Cart button clicked!");
            shoppingCart.setVisible(true);
            productSelectInterface.setVisible(false);

        });

        productSelectPanel.add(categoryPanel);
        productSelectPanel.add(categoryComboBox);
        productSelectPanel.add(shoppingCartButton);

        productSelect.add(productSelectPanel);
        productSelect.setSize(800,800);
        productSelect.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        return productSelect;

    }

    public JFrame shoppingCart(){
        JFrame cart = new JFrame("Shoppning Cart");

        JPanel cartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel cartLabel = new JLabel("Shopping Cart");

        cartPanel.add(cartLabel);

        cart.add(cartPanel);
        cart.setSize(400, 400);
        cart.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        return cart;
    }
    public static void main(String[] args) {
        WestminsterShoppingGUI GUI = new WestminsterShoppingGUI();

        JFrame login = GUI.login;

        login.setSize(300, 150); // Adjusted the height
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setVisible(true);
    }
}
