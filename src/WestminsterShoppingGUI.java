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
    private JFrame productSelectInterface;
    private JFrame shoppingCart;
    private JPanel selectedProductsDetailsPanel;
    private ArrayList<Product> items = new ArrayList<>();
    private JTable cartTable = new JTable();
    private DefaultTableModel cartTableModel = new DefaultTableModel();


    public WestminsterShoppingGUI() {
        selectedProductsDetailsPanel = new JPanel(); // Initialize the panel here
        shoppingCart = shoppingCart();
    }

    public JFrame productSelectInterface(User user) {
        JFrame productSelect = new JFrame("Westminster Shopping Centre");

        JPanel productSelectPanel = new JPanel(new BorderLayout());

        JLabel categoryLabel = new JLabel("Select Product Category: ");

        String[] options = {"All", "Electronics", "Clothing"};
        JComboBox<String> categoryComboBox = new JComboBox<>(options);

        JButton shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.addActionListener(e -> {

            shoppingCart = shoppingCart();
            shoppingCart.setVisible(true);

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
                        items.add(product);
                        System.out.println("Items in the shopping cart:");
                        for (Product item : items) {
                            System.out.println(item);
                        }
                        user.setProductHistory(product);
                        JOptionPane.showMessageDialog(null, "Product added successfully !");
                    }
                });

                selectedProductsDetailsPanel.revalidate();
                selectedProductsDetailsPanel.repaint();
            }
        }
    }

    public JFrame shoppingCart() {

        JFrame cart = new JFrame("Shopping Cart");
        JPanel cartPanel = new JPanel(new BorderLayout());

        DefaultTableModel cartTableModel = new DefaultTableModel();
        JTable cartTable = new JTable(cartTableModel);

        String[] columnNames = {"Product", "Quantity", "Price"};

        cartTable.setDefaultEditor(Object.class, null);

        for (String columnName : columnNames) {
            cartTableModel.addColumn(columnName);
        }

        System.out.println(items);
        for (Product item : items){
            String[] rowData = { item.getProductName() , String.valueOf(item.getProductNOU()) , String.valueOf(item.getProductPrice()) };
            cartTableModel.addRow(rowData);
        }

        JScrollPane scrollPane = new JScrollPane(cartTable);
        cartPanel.add(scrollPane, BorderLayout.CENTER);

        double totalPrice = TotalPrice();
//        double discount10 = firstPurchase(,totalPrice);

        JPanel finalbill = new JPanel(new GridLayout(4, 1));
        JLabel totalLabel = new JLabel("Total : " + totalPrice);
        JLabel firstPurchaseLabel = new JLabel("First Purchase Discount (10 %) : ");
        JLabel threeProductLabel = new JLabel("Three items in the same Category Discount (20 %) : ");
        JLabel finalTotalLabel = new JLabel("Final Total : " + totalPrice);

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

//    public void updateCartTable(JTable table) {
//        List<Object[]> tableDataList = new ArrayList<>();
//
//        for (Product product : items) {
//            String productString;
//            if (product instanceof Electronics) {
//                productString = product.getProductID() + ", " + product.getProductName() + ", " + ((Electronics) product).getProductBrand() + ", " + ((Electronics) product).getProductWarranty();
//            } else if (product instanceof Clothing) {
//                productString = product.getProductID() + ", " + product.getProductName() + ", " + ((Clothing) product).getProductSize() + ", " + ((Clothing) product).getProductColor();
//            } else {
//                productString = "Unknown Product Type";
//            }
//
//            Object[] tableRowProduct = new Object[]{
//                    productString,
//                    product.getProductNOU(),
//                    product.getProductPrice()
//            };
//            tableDataList.add(tableRowProduct);
//        }
//
//        for (Object[] rowData : tableDataList) {
//            cartTableModel.addRow(rowData);
//        }
//    }

    public double TotalPrice() {
        double totalPrice = 0;
        for (Product product : items) {
            totalPrice += product.getProductPrice();
        }
        return totalPrice;
    }

//    public double firstPurchase(Product product, double totalPrice){
//        for (Product historyItem : items){
//            if (!historyItem.getProductID().equals(product.getProductID())){
//                double discount = (totalPrice * (0.1));
//                return discount;
//            }
//        }
//        return 0;
//    }

    public static void main(String[] args) {
        WestminsterShoppingGUI shoppingGUI = new WestminsterShoppingGUI();

        JFrame productSelectFrame = shoppingGUI.productSelectInterface(new User("admin", "admin", new ArrayList<>()));

        productSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        productSelectFrame.setVisible(true);
    }
}
