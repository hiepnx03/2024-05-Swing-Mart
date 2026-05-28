package org.example.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;

public class ImageRenderer extends DefaultTableCellRenderer {
    private static final String IMAGE_BASE_PATH = "src/main/resources/images/";
    private static final int IMAGE_WIDTH = 100;
    private static final int IMAGE_HEIGHT = 100;
    private static final String MSG_IMAGE_NOT_FOUND = "Image not found";
    private static final String MSG_NO_IMAGE = "No image";

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        if (value != null) {
            // Assuming value is the name of the image file
            String imagePath = IMAGE_BASE_PATH + value.toString();
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(imagePath);
                // Scale the image to the defined constants
                Image image = icon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(image));
                label.setText(null);
            } else {
                label.setText(MSG_IMAGE_NOT_FOUND);
                label.setIcon(null);
            }
        } else {
            label.setText(MSG_NO_IMAGE);
            label.setIcon(null);
        }
        label.setHorizontalAlignment(JLabel.CENTER);  // Center the image in the cell
        label.setVerticalAlignment(JLabel.CENTER);    // Center the image in the cell
        return label;
    }
}