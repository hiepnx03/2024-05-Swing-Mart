package org.example.controllers;

import org.example.services.ProductImageService;
import org.example.services.impl.ProductImageServiceImpl;

public class ProductImageController {

    private final ProductImageService productImageService;

    public ProductImageController() {
        this.productImageService = new ProductImageServiceImpl();
    }

    public boolean checkFileNameExists(String fileName) {
        return productImageService.checkFileNameExists(fileName);
    }
}
