package com.shop.shop.management.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "price")
public class Price {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Product product;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Date ins_ts;

}