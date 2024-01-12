import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WestminsterShoppingGUI {

//ArrayList<java.lang.Object[]> data = new ArrayList<java.lang.Object[]>();

    private JFrame frame;
    private JComboBox<String> categoryComboBox;
    private JButton shoppingCartButton, addToCartButton;
    private JTable productsTable;
    private JTextArea itemDetailsTextArea;

    public WestminsterShoppingGUI() {
        // Initialize the frame
        frame = new JFrame("Westminster Online Shopping System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create components
        categoryComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothes"});
        shoppingCartButton = new JButton("Shopping Cart");
        addToCartButton = new JButton("Add to Shopping Cart");

        // Change table column size
        Object[][] tableData = new Object[WestminsterShoppingManager.stocks.size()][5];

//        String Category = null;
//        String Information = null;
//        for (int i = 0; i < WestminsterShoppingManager.list_of_products.size(); i++) {
//            Product product = WestminsterShoppingManager.list_of_products.get(i);
//
//            if (product instanceof Electronics){
//                Category = "Electronics";
//                Information = ((Electronics) product).getProduct_brand() + "," + ((Electronics) product).getProduct_warranty();
//            }else if((product instanceof Clothing)){
//                Category = "Clothing";
//                Information = ((Clothing) product).getProduct_color() + "," + ((Clothing) product).getProduct_size();
//            }
//
//            tableData[i][0] = product.getProduct_ID();
//            tableData[i][1] = product.getProduct_name();
//            tableData[i][2] = Category;
//            tableData[i][3] = product.getPrice();
//            tableData[i][4] = Information;
//        }


        // Column names
        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Information"};

        // Create a table model
        DefaultTableModel tableModel = new DefaultTableModel(tableData, columnNames);

        // Create the JTable with the table model
        productsTable = new JTable(tableModel);

        // Set column widths
        int[] columnWidths = {100, 150, 100, 120, 150}; // Adjust these values as needed

        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = productsTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

//        product details to the center of cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < productsTable.getColumnCount(); i++) {
            productsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set layout manager
        frame.setLayout(new BorderLayout());

        // Top panel with category label, dropdown, and shopping cart button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel categoryLabel = new JLabel("Select Product Category");
        topPanel.add(categoryLabel);
        topPanel.add(categoryComboBox);

        JPanel shoppingCartPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        shoppingCartPanel.add(shoppingCartButton);
        topPanel.add(shoppingCartPanel);

        // Center panel with product table
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(new JScrollPane(productsTable), BorderLayout.CENTER);

        // Bottom panel with item details and add to cart button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(new JScrollPane(itemDetailsTextArea), BorderLayout.CENTER);
        bottomPanel.add(addToCartButton, BorderLayout.SOUTH);

        // Add panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Set up ActionListener for the buttons
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your logic for the shopping cart button
                JOptionPane.showMessageDialog(frame, "Shopping Cart button clicked");
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                // Add your logic for the add to cart button
                JOptionPane.showMessageDialog(frame, "Add to Cart button clicked");
            }
        });

        // Set up ActionListener for the combo box
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Category = null;
                String Information = null;
                String selectedCategory = (String) categoryComboBox.getSelectedItem();

                // Print corresponding values based on the selected item
                if ("All".equals(selectedCategory)) {
                    System.out.println("1");

                    for (int i = 0; i < WestminsterShoppingManager.stocks.size(); i++) {
                        Product product = WestminsterShoppingManager.stocks.get(i);

                        if (product instanceof Electronics){
                            Category = "Electronics";
                            Information = ((Electronics) product).getProductBrand() + "," + ((Electronics) product).getProductWarranty();
                        }else if((product instanceof Clothing)){
                            Category = "Clothing";
                            Information = ((Clothing) product).getProductColor() + "," + ((Clothing) product).getProductSize();
                        }

                        tableData[i][0] = product.getProductID();
                        tableData[i][1] = product.getProductName();
                        tableData[i][2] = Category;
                        tableData[i][3] = product.getProductPrice();
                        tableData[i][4] = Information;
                    }

                } else if ("Electronics".equals(selectedCategory)) {
                    System.out.println("2");

                    for (int i = 0; i < WestminsterShoppingManager.stocks.size(); i++) {
                        Product product = WestminsterShoppingManager.stocks.get(i);

                        if (product instanceof Electronics){
                            Category = "Electronics";
                            Information = ((Electronics) product).getProductBrand() + "," + ((Electronics) product).getProductWarranty();

                            tableData[i][0] = product.getProductID();
                            tableData[i][1] = product.getProductName();
                            tableData[i][2] = Category;
                            tableData[i][3] = product.getProductPrice();
                            tableData[i][4] = Information;
                        }
                    }

                } else if ("Clothes".equals(selectedCategory)) {
                    System.out.println("3");

                    for (int i = 0; i < WestminsterShoppingManager.stocks.size(); i++) {
                        Product product = WestminsterShoppingManager.stocks.get(i);

                        if((product instanceof Clothing)){
                            Category = "Clothing";
                            Information = ((Clothing) product).getProductColor() + "," + ((Clothing) product).getProductSize();

                            tableData[i][0] = product.getProductID();
                            tableData[i][1] = product.getProductName();
                            tableData[i][2] = Category;
                            tableData[i][3] = product.getProductPrice();
                            tableData[i][4] = Information;
                        }
                    }
                }
            }
        });
        // Set frame visibility
        frame.setVisible(true);
    }


    public void runGUI(){
        WestminsterShoppingGUI.main(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WestminsterShoppingGUI();
            }
        });
    }
}