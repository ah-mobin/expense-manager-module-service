package com.example.expensemanagermodservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name="categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private UUID uuid;
    @Column(nullable = false, length = 64)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<SubCategoryEntity> subCategories;
}
