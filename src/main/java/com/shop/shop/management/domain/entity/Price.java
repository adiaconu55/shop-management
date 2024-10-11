package com.shop.shop.management.domain.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "price")
public class Price {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int price;

    @Column(name = "ins_ts", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date insTs;

    @PrePersist
    protected void onCreate() {
        this.insTs = new Date();
    }

    public Price() {
    }

    public Price(int price) {
        this.price = price;
    }

    public Price(Product product, int price) {
        this.product = product;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getInsTs() {
        return insTs;
    }

    public void setInsTs(Date insTs) {
        this.insTs = insTs;
    }
}