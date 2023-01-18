package com.restaurantapp.models;

import java.time.LocalDateTime;

public class Order {
    private Long id;

    private User user;

    private Offer offer;

    private LocalDateTime localDateTime;

    public Order() {
    }

    public Order(Long id, User user, Offer offer, LocalDateTime localDateTime) {
        this.id = id;
        this.user = user;
        this.offer = offer;
        this.localDateTime = localDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order id(Long id){
        setId(id);
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order user(User user){
        setUser(user);
        return this;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Order offer(Offer offer){
        setOffer(offer);
        return this;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Order localDateTime(LocalDateTime localDateTime){
        setLocalDateTime(localDateTime);
        return this;
    }
}
