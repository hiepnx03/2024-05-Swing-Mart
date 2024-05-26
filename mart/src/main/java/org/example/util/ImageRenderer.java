package org.example.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;

public class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        if (value != null) {
            // Assuming value is the name of the image file
            String imagePath = "src/main/resources/images/" + value.toString();
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(imagePath);
                // Scale the image to 100x100
                Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(image));
                label.setText(null);
            } else {
                label.setText("Image not found");
                label.setIcon(null);
            }
        } else {
            label.setText("No image");
            label.setIcon(null);
        }
        label.setHorizontalAlignment(JLabel.CENTER);  // Center the image in the cell
        label.setVerticalAlignment(JLabel.CENTER);    // Center the image in the cell
        return label;
    }
}