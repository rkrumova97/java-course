package com.restaurantapp.dao;

import com.restaurantapp.models.*;

import java.util.List;

public interface OfferDao {
    public Offer createOffer(Offer offer) throws Exception;

    public Offer readOffer(Long id) throws Exception;

    public List<Offer> readAllOffer() throws Exception;

    void updateOffer(Offer offer);

    public void deleteOffer(Long id);
}
