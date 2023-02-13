package model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "category_Name")
     private  String categoryName;
    @Column(name = "description")

    private String description;
    @Column(name = "image_url")

    private String imageUrl;
}
