package com.sparta.springjpa.entity;

import com.sparta.springjpa.Timestamped;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Orders extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @Getter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Orders(Food food, Member member) {
        this.food = food;
        this.member = member;
    }

}

