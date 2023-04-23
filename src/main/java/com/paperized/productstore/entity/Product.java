package com.paperized.productstore.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 1000)
    private String description;
    private String amount;
    private String thumbnailUrl;

    private Long creatorId;
    @Column(nullable = false)
    private Timestamp creationTime;
    @Column(nullable = false)
    private Timestamp lastUpdatedTime;
}
