package com.geekbrains.java.lesson18;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "consumer_id")
    private Long consumerId;

    @Column(name = "good_id")
    private Long goodId;

    public Purchase() {}

    public Purchase(Long cost, Long consumerId, Long goodId) {
        this.cost = cost;
        this.consumerId = consumerId;
        this.goodId = goodId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }
}
