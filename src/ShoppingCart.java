import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static ArrayList<Product> items;

//    public ShoppingCart(ArrayList<Product> products) {
//        items = new ArrayList<>(products);
//    }

    public ShoppingCart(ArrayList<Product> products) {
        if (products != null) {
            items = new ArrayList<>(products);
        } else {
            items = new ArrayList<>();
        }
    }

    public static void addItem(Product product) {
        items.add(product);
    }

    public void deleteItem(Product product) {
        items.remove(product);
    }

    public static ArrayList<Product> getItems() {
//        return items = new ArrayList<>();
        return items;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : items) {
            totalPrice += product.getProductPrice();
        }
        return totalPrice;
    }
}