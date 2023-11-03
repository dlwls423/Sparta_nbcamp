package com.sparta.springjpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int price;

    @OneToMany(mappedBy = "food", fetch = FetchType.EAGER)
    private List<Orders> orders = new ArrayList<>();

    public Food(String foodName, int price) {
        this.foodName = foodName;
        this.price = price;
    }
}
