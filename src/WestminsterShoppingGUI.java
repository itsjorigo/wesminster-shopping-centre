import javax.swing.*;
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

        JScrollPane productTableScrollPane = productTable(categoryComboBox);
        productTableScrollPane.setPreferredSize(new Dimension(800, 400));

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

    public JScrollPane productTable(JComboBox<String> categoryComboBox) {
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
                updateTable(model, selectedCategory);
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Object productId = table.getValueAt(selectedRow, 0);
                        displaySelectedProductsDetails(productId);
                    }
                }
            }
        });

        return scrollPane;
    }

    public void updateTable(DefaultTableModel model, String selectedCategory) {
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

    public void displaySelectedProductsDetails(Object productId) {
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
                selectedProductsDetailsPanel.revalidate();
                selectedProductsDetailsPanel.repaint();
                break;
            }
        }
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
