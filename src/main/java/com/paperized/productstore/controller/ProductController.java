package com.paperized.productstore.controller;

import com.paperized.productstore.dto.ProductDTO;
import com.paperized.productstore.security.util.IsAdmin;
import com.paperized.productstore.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @IsAdmin
    @PostMapping("/")
    public Long createProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @GetMapping("/{productId}")
    public ProductDTO getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @IsAdmin
    @DeleteMapping("/{productId}")
    public Boolean deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }
}
