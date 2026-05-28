package org.example.services.impl;

import org.example.repositories.ProductImageRepository;
import org.example.repositories.impl.ProductImageRepositoryImpl;
import org.example.services.ProductImageService;

public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository = new ProductImageRepositoryImpl();

    @Override
    public boolean checkFileNameExists(String fileName) {
        return productImageRepository.checkFileNameExists(fileName);
    }
}
