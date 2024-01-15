import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            productSelectInterface.setVisible(true);
            login.setVisible(false);
        });

        JButton signupButton = new JButton("Signup");
        signupButton.addActionListener(e -> {
            signIn.setVisible(true);
            login.setVisible(false);
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
        signIn.setSize(300, 250);
        signIn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        return signIn;
    }

    public JFrame productSelectInterface() {
        JFrame productSelect = new JFrame("Westminster Shopping Centre");

        JPanel productSelectPanel = new JPanel(new BorderLayout());

        JLabel categoryLabel = new JLabel("Select Product Category: ");

        String[] options = {"All", "Electronics", "Clothing"};
        JComboBox<String> categoryComboBox = new JComboBox<>(options);

        JButton shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.addActionListener(e -> {
            shoppingCart.setVisible(true);
            productSelectInterface.setVisible(false);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(categoryLabel);
        buttonPanel.add(categoryComboBox);
        buttonPanel.add(shoppingCartButton);

        productSelectPanel.add(buttonPanel, BorderLayout.NORTH);
        productSelectPanel.add(productTable(categoryComboBox), BorderLayout.CENTER);

        productSelect.add(productSelectPanel);
        productSelect.setSize(800, 800);
        productSelect.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return productSelect;
    }

    public JScrollPane productTable(JComboBox<String> categoryComboBox) {
        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Information"};

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                updateTable(categoryComboBox, table, model, selectedCategory);
            }
        });
        return scrollPane;
    }

//    private void updateTable(JComboBox<String> categoryComboBox, JTable table, DefaultTableModel model, String selectedCategory) {
//        List<Object[]> rowDataList = new ArrayList<>();
//
//        for (Map.Entry<String, Product> entry : WestminsterShoppingManager.stocks.entrySet()) {
//            String productID = entry.getKey();
//            Product product = entry.getValue();
//
//            String category;
//            String information;
//            Object[] rowData;
//
//            switch (selectedCategory) {
//                case "All":
//                    category = getCategoryString(product);
//                    information = getCategoryInformation(product);
//                    rowData = new Object[]{
//                            product.getProductID(),
//                            product.getProductName(),
//                            category,
//                            product.getProductPrice(),
//                            information
//                    };
//                    rowDataList.add(rowData);
//                    break;
//
//                case "Electronics":
//                    if (product instanceof Electronics) {
//                        category = "Electronics";
//                        information = ((Electronics) product).getProductBrand() + ", " + ((Electronics) product).getProductWarranty();
//                        rowData = new Object[]{
//                                product.getProductID(),
//                                product.getProductName(),
//                                category,
//                                product.getProductPrice(),
//                                information
//                        };
//                        rowDataList.add(rowData);
//                    }
//                    break;
//
//                case "Clothing":
//                    if (product instanceof Clothing) {
//                        category = "Clothes";
//                        information = ((Clothing) product).getProductSize() + ", " + ((Clothing) product).getProductColor();
//                        rowData = new Object[]{
//                                product.getProductID(),
//                                product.getProductName(),
//                                category,
//                                product.getProductPrice(),
//                                information
//                        };
//                        rowDataList.add(rowData);
//                    }
//                    break;
//
//                default:
//                    // Handle other cases as needed
//                    break;
//            }
//        }
//        // Clear existing rows in the table
//        model.setRowCount(0);
//        // Add all rows to the model
//        for (Object[] rowData : rowDataList) {
//            model.addRow(rowData);
//        }
//    }

    private void updateTable(JComboBox<String> categoryComboBox, JTable table, DefaultTableModel model, String selectedCategory) {
        List<Object[]> rowDataList = new ArrayList<>();

        for (Map.Entry<String, Product> entry : WestminsterShoppingManager.stocks.entrySet()) {
            Product product = entry.getValue();

            String category;
            String information;
            Object[] rowData;

            switch (selectedCategory) {
                case "All" -> {
                    category = getCategoryString(product);
                    information = getCategoryInformation(product);
                }
                case "Electronics" -> {
                    if (!(product instanceof Electronics)) {
                        continue;
                    }
                    category = "Electronics";
                    information = ((Electronics) product).getProductBrand() + ", " + ((Electronics) product).getProductWarranty();
                }
                case "Clothing" -> {
                    if (!(product instanceof Clothing)) {
                        continue;
                    }
                    category = "Clothes";
                    information = ((Clothing) product).getProductSize() + ", " + ((Clothing) product).getProductColor();
                }
                default -> {
                    // Handle other cases as needed
                    continue;
                }
            }

            rowData = new Object[]{
                    product.getProductID(),
                    product.getProductName(),
                    category,
                    product.getProductPrice(),
                    information
            };
            rowDataList.add(rowData);
        }

        // Clear existing rows in the table
        model.setRowCount(0);

        // Add all rows to the model
        for (Object[] rowData : rowDataList) {
            model.addRow(rowData);
        }
    }


    private String getCategoryString(Product product) {
        if (product instanceof Electronics) {
            return "Electronics";
        } else if (product instanceof Clothing) {
            return "Clothes";
        }
        return "";
    }

    private String getCategoryInformation(Product product) {
        if (product instanceof Electronics) {
            return ((Electronics) product).getProductBrand() + ", " + ((Electronics) product).getProductWarranty();
        } else if (product instanceof Clothing) {
            return ((Clothing) product).getProductSize() + ", " + ((Clothing) product).getProductColor();
        }
        return "";
    }

    public JFrame shoppingCart() {
        JFrame cart = new JFrame("Shopping Cart");

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

        login.setSize(300, 150);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setVisible(true);
    }
}

