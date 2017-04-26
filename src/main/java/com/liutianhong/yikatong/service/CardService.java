package com.liutianhong.yikatong.service;

import com.liutianhong.yikatong.domain.Card;
import java.util.List;

/**
 * Service Interface for managing Card.
 */
public interface CardService {

    /**
     * Save a card.
     *
     * @param card the entity to save
     * @return the persisted entity
     */
    Card save(Card card);

    /**
     *  Get all the cards.
     *  
     *  @return the list of entities
     */
    List<Card> findAll();

    /**
     *  Get the "id" card.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Card findOne(Long id);

    /**
     *  Delete the "id" card.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
