package com.example.expensemanagermodservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity(name="sub_categories")
public class SubCategoryEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String uuid;

    @Column(nullable = false, length = 64)
    private String name;
    @ManyToOne
    @JoinColumn(name="category_id",nullable = false)
    @JsonIgnore
    private CategoryEntity category;

}
