package com.example.expensemanagermodservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity(name="sub_categories")
public class SubCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;

}
