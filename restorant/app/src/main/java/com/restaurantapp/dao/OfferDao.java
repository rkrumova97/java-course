package com.restaurantapp.dao;

import com.restaurantapp.models.*;

import java.util.List;

public interface OfferDao {
    public Offer createOffer(Offer offer);

    public Offer readOffer(Long id);

    public List<Offer> readAllOffer();

    public Offer updateOffer(Offer offer);

    public void deleteOffer(Long id);
}
