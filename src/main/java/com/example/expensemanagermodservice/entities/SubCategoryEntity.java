package com.example.expensemanagermodservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(nullable = false, length = 64)
    private String name;
    @Column(nullable = false, unique = true, length = 64)
    private String slug;
    @ManyToOne
    @JoinColumn(name="category_id",nullable = false)
    @JsonIgnore
    private CategoryEntity category;

}
