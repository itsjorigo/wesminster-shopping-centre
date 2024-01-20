import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WestminsterShoppingGUI {

    public Collection<Product> productValues = WestminsterShoppingManager.stocks.values();
    private JFrame login;
    private JFrame signIn;
    private JFrame productSelectInterface;
    private JFrame shoppingCart;
    private JPanel selectedProductsDetailsPanel;

    public WestminsterShoppingGUI() {
        login = userLogin();
        signIn = userSignIn();
        selectedProductsDetailsPanel = new JPanel(); // Initialize the panel here
        shoppingCart = shoppingCart();
    }

    public JFrame userLogin() {
        User.loadUsers();
        JFrame login = new JFrame("User Login Page");

        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel userNameLabel = new JLabel("Username: ");
        JTextField userNameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = userNameField.getText();
            String password = new String(passwordField.getPassword());

            User validatedUser = userValidation(username, password);

            if (validatedUser != null) {
                productSelectInterface(validatedUser).setVisible(true);
                login.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                userNameField.setText("");
                passwordField.setText("");
            }
        });

        JButton signupButton = new JButton("Signup");
        signupButton.addActionListener(e -> {
            signIn.setVisible(true);
            login.setVisible(false);
        });

        loginPanel.add(userNameLabel);
        loginPanel.add(userNameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(signupButton);

        login.add(loginPanel);
        return login;
    }

    public JFrame userSignIn() {
        JFrame signIn = new JFrame("User SignIn Page");

        JPanel signInPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel userNameLabel = new JLabel("Enter a username: ");
        JTextField userNameField = new JTextField(20);

        JLabel passwordLabel1 = new JLabel("Enter a password: ");
        JPasswordField passwordField1 = new JPasswordField(20);

        JLabel passwordLabel2 = new JLabel("Enter password again: ");
        JPasswordField passwordField2 = new JPasswordField(20);

        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(e -> {
            // Get the text content from the fields
            String inputUsername = userNameField.getText();
            String inputPassword1 = new String(passwordField1.getPassword());
            String inputPassword2 = new String(passwordField2.getPassword());

            // Check if fields are not empty
            if (!inputUsername.equals("") && !inputPassword1.equals("") && !inputPassword2.equals("")) {
                boolean fieldEmpty = true;
                if (inputPassword1.equals(inputPassword2)) {
                    if (User.getUsers() != null) {
                        userRegistration(inputUsername, inputPassword1, inputPassword2, fieldEmpty);
                    } else {
                        User user = new User(null, "admin", "admin");
                        userRegistration(inputUsername, inputPassword1, inputPassword2, fieldEmpty);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Passwords doesn't match. Please try again.");
                    passwordField1.setText("");
                    passwordField2.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(signIn, "Fields can't be empty !");
            }
        });

        signInPanel.add(userNameLabel);
        signInPanel.add(userNameField);
        signInPanel.add(passwordLabel1);
        signInPanel.add(passwordField1);
        signInPanel.add(passwordLabel2);
        signInPanel.add(passwordField2);
        signInPanel.add(signInButton);

        signIn.add(signInPanel);
        signIn.setSize(300, 250);
        signIn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        return signIn;
    }

    public void userRegistration(String inputUsername, String inputPassword1, String inputPassword2, boolean fieldEmpty){
        for (User user : User.getUsers()) {
            // Correct comparison with inputUsername
            if (inputUsername.equals(user.getUsername())) {
                JOptionPane.showMessageDialog(signIn, "Username already exists !");
                fieldEmpty = false;
                break;
            }
        }

        if (fieldEmpty) {
            User user = new User(null, inputUsername, inputPassword1);
            User.setUsers(user);
            User.saveUsers();
            JOptionPane.showMessageDialog(signIn, "You're registered. Welcome !");
            productSelectInterface(user).setVisible(true);
            signIn.setVisible(false);
        }
    }

    public User userValidation(String Username, String Password){
        boolean validated = false;
        for (User user : User.getUsers()){
            if (Username.equals(user.getUsername()) && Password.equals(user.getPassword())){
                validated = true;
                return user;
            }
        }
        return null;
    }

    public JFrame productSelectInterface(User user) {
        JFrame productSelect = new JFrame("Westminster Shopping Centre");

        JPanel productSelectPanel = new JPanel(new BorderLayout());

        JLabel categoryLabel = new JLabel("Select Product Category: ");

        String[] options = {"All", "Electronics", "Clothing"};
        JComboBox<String> categoryComboBox = new JComboBox<>(options);

        JButton shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.addActionListener(e -> {
            shoppingCart.setVisible(true);
            productSelectInterface(user).setVisible(false);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(categoryLabel);
        buttonPanel.add(categoryComboBox);
        buttonPanel.add(shoppingCartButton);

        JScrollPane productTableScrollPane = productTable(categoryComboBox, user);
        productTableScrollPane.setPreferredSize(new Dimension(800, 400));

        selectedProductsDetailsPanel = new JPanel (new GridLayout(0,1));
        selectedProductsDetailsPanel.setPreferredSize(new Dimension(800, 300));
        selectedProductsDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        productSelectPanel.add(buttonPanel, BorderLayout.NORTH);
        productSelectPanel.add(productTableScrollPane, BorderLayout.CENTER);
        productSelectPanel.add(selectedProductsDetailsPanel, BorderLayout.SOUTH);

        productSelect.add(productSelectPanel);
        productSelect.setSize(800, 800);
        productSelect.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return productSelect;
    }

    public JScrollPane productTable(JComboBox<String> categoryComboBox,User user) {
        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Information"};

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        table.setDefaultEditor(Object.class, null);

        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                updateTable(model, selectedCategory, table);
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Object productId = table.getValueAt(selectedRow, 0);
                        displaySelectedProductsDetails(productId, model, user);
                    }
                }
            }
        });

        return scrollPane;
    }

    public void updateTable(DefaultTableModel model, String selectedCategory, JTable table) {
        List<Object[]> tableDataList = new ArrayList<>();

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
                default -> {continue;}
            }
            rowData = new Object[]{
                    product.getProductID(),
                    product.getProductName(),
                    category,
                    product.getProductPrice(),
                    information
            };
            tableDataList.add(rowData);
        }
        model.setRowCount(0);
        for (Object[] rowData : tableDataList) {
            model.addRow(rowData);
        }
    }

    public String getCategoryString(Product product) {
        if (product instanceof Electronics) {
            return "Electronics";
        } else if (product instanceof Clothing) {
            return "Clothes";
        }
        return "";
    }

    public String getCategoryInformation(Product product) {
        if (product instanceof Electronics) {
            return ((Electronics) product).getProductBrand() + ", " + ((Electronics) product).getProductWarranty();
        } else if (product instanceof Clothing) {
            return ((Clothing) product).getProductSize() + ", " + ((Clothing) product).getProductColor();
        }
        return "";
    }

    public void displaySelectedProductsDetails(Object productId, DefaultTableModel model, User user) {
        selectedProductsDetailsPanel.removeAll();
        for (Product product : productValues) {
            if (productId.equals(product.getProductID())) {
                JLabel productIDLabel = new JLabel("Product ID: " + product.getProductID());
                JLabel categoryLabel = new JLabel("Category: " + getCategoryString(product));
                JLabel nameLabel = new JLabel("Name: " + product.getProductName());
                JLabel itemsAvailableLabel = new JLabel("Items Available: " + product.getProductNOU());

                selectedProductsDetailsPanel.add(productIDLabel);
                selectedProductsDetailsPanel.add(categoryLabel);
                selectedProductsDetailsPanel.add(nameLabel);

                if (product instanceof Electronics) {
                    JLabel brandLabel = new JLabel("Brand: " + ((Electronics) product).getProductBrand());
                    JLabel warrantyLabel = new JLabel("Warranty: " + ((Electronics) product).getProductWarranty());
                    selectedProductsDetailsPanel.add(brandLabel);
                    selectedProductsDetailsPanel.add(warrantyLabel);

                } else if (product instanceof Clothing) {
                    JLabel sizeLabel = new JLabel("Size: " + ((Clothing) product).getProductSize());
                    JLabel colorLabel = new JLabel("Colour: " + ((Clothing) product).getProductColor());
                    selectedProductsDetailsPanel.add(sizeLabel);
                    selectedProductsDetailsPanel.add(colorLabel);

                }
                selectedProductsDetailsPanel.add(itemsAvailableLabel);

                JButton addToCart = new JButton("Add To Shopping Cart");
                selectedProductsDetailsPanel.add(addToCart);

                addToCart.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        user.setProductHistory(product);
                        JOptionPane.showMessageDialog(null, "Product added successfully !");
                    }
                });

                selectedProductsDetailsPanel.revalidate();
                selectedProductsDetailsPanel.repaint();
                break;
            }
        }
    }

    public JFrame shoppingCart() {
        JFrame cart = new JFrame("Shopping Cart");

        JPanel cartPanel = new JPanel(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        String[] columnNames = {"Product", "Quantity", "Price"};

        table.setDefaultEditor(Object.class, null);

        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        Object[] tabledata = {"B201, Leggings, M, Black", "2", "45.80"};
        Object[] tabledata1 = {"B201, Leggings, M, Black", "2", "45.80"};

        model.addRow(tabledata);
        model.addRow(tabledata1);

        cartPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel finalbill = new JPanel(new GridLayout(4, 1)); // 4 rows, 1 column

        JLabel totalLabel = new JLabel("Total : ");
        JLabel firstPurchaseLabel = new JLabel("First Purchase Discount (10 %) : ");
        JLabel threeProductLabel = new JLabel("Three items in same Category Discount (20 %) : ");
        JLabel finalTotalLabel = new JLabel("Final Total : ");

        finalbill.add(totalLabel);
        finalbill.add(firstPurchaseLabel);
        finalbill.add(threeProductLabel);
        finalbill.add(finalTotalLabel);

        finalbill.setBorder(new EmptyBorder(10, 10, 10, 10));


        cart.add(cartPanel, BorderLayout.CENTER);
        cart.add(finalbill, BorderLayout.SOUTH);

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
