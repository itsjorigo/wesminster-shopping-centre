import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WestminsterShoppingGUI {
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
        productsTable = new JTable(6, 5); // You might need to customize this based on your data model
        itemDetailsTextArea = new JTextArea();

        // Set layout manager
        frame.setLayout(new BorderLayout());

        // Top panel with category label, dropdown, and shopping cart button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel categoryLabel = new JLabel("Select Product Category");
        topPanel.add(categoryLabel);
        topPanel.add(categoryComboBox);
        topPanel.add(shoppingCartButton);

        // Center panel with product table
        JPanel centerPanel = new JPanel(new BorderLayout());
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

        // Set frame visibility
        frame.setVisible(true);
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
