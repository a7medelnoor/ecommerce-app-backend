package com.a7medelnoor.ecommerce_app.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;


@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "category_Name")
    private String categoryName;
    @Column(name = "description")

    private String description;
    @Column(name = "image_url")

    private String imageUrl;
}
