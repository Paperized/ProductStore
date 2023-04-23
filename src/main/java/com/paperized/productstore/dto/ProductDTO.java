package com.paperized.productstore.dto;

import com.paperized.productstore.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@AllArgsConstructor
@Data
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private String amount;
    private String thumbnailUrl;
    private Long creatorId;
    private Timestamp creationTime;
    private Timestamp lastUpdatedTime;

    public ProductDTO() { }

    public static ProductDTO fromProduct(Product product) {
        return new ProductDTO(product.getId(), product.getTitle(), product.getDescription(), product.getAmount(), product.getThumbnailUrl(),
                product.getCreatorId(), product.getCreationTime(), product.getLastUpdatedTime());
    }

    public static Product toProduct(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getTitle(), productDTO.getDescription(), productDTO.getAmount(), productDTO.getThumbnailUrl(),
                productDTO.getCreatorId(), productDTO.getCreationTime(), productDTO.getLastUpdatedTime());
    }
}
