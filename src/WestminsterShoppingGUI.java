import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class WestminsterShoppingGUI {

//ArrayList<java.lang.Object[]> data = new ArrayList<java.lang.Object[]>();

    private JFrame frame;
    private JComboBox<String> categoryComboBox;
    private JButton shoppingCartButton, addToCartButton;
    private JTable productsTable;
    private JTextArea itemDetailsTextArea;
    private ShoppingCart shoppingCart;
    ArrayList<Product> productList = new ArrayList<>(WestminsterShoppingManager.stocks.values());


    public WestminsterShoppingGUI() {
        // Initialize the frame
        frame = new JFrame("Westminster Online Shopping System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create components
        categoryComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothes"});
        shoppingCartButton = new JButton("Shopping Cart");
        addToCartButton = new JButton("Add to Shopping Cart");
        DefaultTableModel tableModel = null;
        productsTable = new JTable(tableModel);

        // Change table column size
        Object[][] tableData = new Object[WestminsterShoppingManager.stocks.size()][5];

        // Column names
        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Information"};

        // Create a table model
        tableModel = new DefaultTableModel(tableData, columnNames);

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

        itemDetailsTextArea = new JTextArea();

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
        JScrollPane tableScrollPane = new JScrollPane(productsTable);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 50, 20));

        JPanel centerPanel = new JPanel(new BorderLayout());
//        centerPanel.add(new JScrollPane(productsTable), BorderLayout.CENTER);
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Bottom panel with item details and add to cart button
        JPanel bottomPanel = new JPanel(new BorderLayout());
//        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        itemDetailsTextArea.setPreferredSize(new Dimension(400, 200)); // Set your preferred size

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
                shoppingCart.hashCode();
                JOptionPane.showMessageDialog(frame, "Shopping Cart button clicked");
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                // Add your logic for the add to cart button
                JOptionPane.showMessageDialog(frame, "Add to Cart button clicked");
                addToShoppingCart();
            }

            private void addToShoppingCart() {
                String itemDetails = itemDetailsTextArea.getText();
                if (!itemDetails.isEmpty()) {
                    // Split the itemDetails and add to the shopping cart
                    String[] detailsArray = itemDetails.split("\\s+");
                    shoppingCart.addItem(detailsArray);
                    JOptionPane.showMessageDialog(frame, "Item added to Shopping Cart");
                }
            }
        });

        // Set up ActionListener for the combo box
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
                model.setRowCount(0); // Clear existing rows

                String Category = null;
                String Information = null;
                String selectedCategory = (String) categoryComboBox.getSelectedItem();

                for (int i = 0; i < productList.size(); i++) {
                    Product product = productList.get(i);

                    if (product instanceof Electronics) {
                        Category = "Electronics";
                        Information = ((Electronics) product).getProductBrand() + "," + ((Electronics) product).getProductWarranty();
                    } else if ((product instanceof Clothing)) {
                        Category = "Clothing";
                        Information = ((Clothing) product).getProductColor() + "," + ((Clothing) product).getProductSize();
                    }

                    tableData[i][0] = product.getProductID();
                    tableData[i][1] = product.getProductName();
                    tableData[i][2] = Category;
                    tableData[i][3] = product.getProductPrice();
                    tableData[i][4] = Information;
                }


//                int dataSize = 0;
//
//                if ("All".equals(selectedCategory)) {
//                    dataSize = productList.size();
//                } else if ("Electronics".equals(selectedCategory)) {
//                    for (Product product : productList) {
//                        if (product instanceof Electronics) {
//                            dataSize++;
//                        }
//                    }
//                } else if ("Clothes".equals(selectedCategory)) {
//                    for (Product product : productList) {
//                        if (product instanceof Clothing) {
//                            dataSize++;
//                        }
//                    }
//                }
//
//                Object[][] tableData = new Object[dataSize][5];
//
//                // Print corresponding values based on the selected item
//                int tableIndex = 0; // Index to fill the tableData
//
//                // Print corresponding values based on the selected item
//                if ("All".equals(selectedCategory)) {
//
//                    for (int i = 0; i < productList.size(); i++) {
//                        Product product = productList.get(i);
//
//                        if (product instanceof Electronics) {
//                            Category = "Electronics";
//                            Information = ((Electronics) product).getProductBrand() + "," + ((Electronics) product).getProductWarranty();
//                        } else if ((product instanceof Clothing)) {
//                            Category = "Clothing";
//                            Information = ((Clothing) product).getProductColor() + "," + ((Clothing) product).getProductSize();
//                        }
//
//                        tableData[tableIndex][0] = product.getProductID();
//                        tableData[tableIndex][1] = product.getProductName();
//                        tableData[tableIndex][2] = Category;
//                        tableData[tableIndex][3] = product.getProductPrice();
//                        tableData[tableIndex][4] = Information;
//
//                        tableIndex++; // Increment tableIndex
//                    }
//
//                } else if ("Electronics".equals(selectedCategory)) {
//
//                    for (int i = 0; i < productList.size(); i++) {
//                        Product product = productList.get(i);
//
//                        if (product instanceof Electronics) {
//                            Category = "Electronics";
//                            Information = ((Electronics) product).getProductBrand() + "," + ((Electronics) product).getProductWarranty();
//
//                            tableData[tableIndex][0] = product.getProductID();
//                            tableData[tableIndex][1] = product.getProductName();
//                            tableData[tableIndex][2] = Category;
//                            tableData[tableIndex][3] = product.getProductPrice();
//                            tableData[tableIndex][4] = Information;
//
//                            tableIndex++; // Increment tableIndex
//                        }
//                    }
//
//                } else if ("Clothes".equals(selectedCategory)) {
//
//                    for (int i = 0; i < productList.size(); i++) {
//                        Product product = productList.get(i);
//
//                        if ((product instanceof Clothing)) {
//                            Category = "Clothing";
//                            Information = ((Clothing) product).getProductColor() + "," + ((Clothing) product).getProductSize();
//
//                            tableData[tableIndex][0] = product.getProductID();
//                            tableData[tableIndex][1] = product.getProductName();
//                            tableData[tableIndex][2] = Category;
//                            tableData[tableIndex][3] = product.getProductPrice();
//                            tableData[tableIndex][4] = Information;
//
//                            tableIndex++; // Increment tableIndex
//                        }
//                    }
//                }

                // Set the new data to the table model
//                for (int i = 0; i < tableData.length; i++) {
//                    model.addRow(new Object[]{tableData[i][0], tableData[i][1], tableData[i][2], tableData[i][3], tableData[i][4]});
//                }
            }
        });

        productsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = productsTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Retrieve information from the selected row and display it in the itemDetailsTextArea
                    StringBuilder productInfo = new StringBuilder();
                    for (int i = 0; i < productsTable.getColumnCount(); i++) {
                        productInfo.append(productsTable.getValueAt(selectedRow, i)).append(" ");
                    }
                    itemDetailsTextArea.setText(productInfo.toString());
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
                WestminsterShoppingGUI gui = new WestminsterShoppingGUI();
                gui.shoppingCart = new ShoppingCart();
            }
        });
    }
}