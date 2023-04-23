package com.paperized.productstore.serviceImpl;

import com.paperized.productstore.dto.ProductDTO;
import com.paperized.productstore.entity.Product;
import com.paperized.productstore.exception.EntityNotFoundException;
import com.paperized.productstore.repository.ProductRepository;
import com.paperized.productstore.service.ProductService;
import com.paperized.productstore.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long createProduct(ProductDTO productDTO) {
        return productRepository.save(ProductDTO.toProduct(productDTO)).getId();
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(EntityNotFoundException::new);
        return MapperUtil.mapTo(ProductDTO::fromProduct, product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(x -> MapperUtil.mapTo(ProductDTO::fromProduct, x)).toList();
    }

    @Transactional
    @Override
    public Boolean deleteProduct(Long productId) {
        if(productRepository.existsById(productId))
            throw new EntityNotFoundException();
        productRepository.deleteById(productId);
        return true;
    }
}
