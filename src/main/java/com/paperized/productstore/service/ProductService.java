package com.paperized.productstore.service;

import com.paperized.productstore.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    Long createProduct(ProductDTO productDTO);
    ProductDTO getProductById(Long productId);
    List<ProductDTO> getAllProducts();
    Boolean deleteProduct(Long productId);
}
