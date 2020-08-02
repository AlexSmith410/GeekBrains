package com.geekbrains.java.lesson19;

import javax.persistence.*;

@Entity
@Table(name = "lots")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "current_bet")
    private Long currentBet;

    @ManyToOne
    @JoinColumn(name = "last_owner")
    private User lastOwner;

    @Version
    Long version;

    public Lot() {

    }

    public Lot(String name, Long currentBet) {
        this.name = name;
        this.currentBet = currentBet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Long getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(Long currentBet) {
        this.currentBet = currentBet;
    }

    public User getlastOwner() {
        return lastOwner;
    }

    public void setlastOwner(User lastOwner) {
        this.lastOwner = lastOwner;
    }

    public Long getVersion() {
        return version;
    }
}
