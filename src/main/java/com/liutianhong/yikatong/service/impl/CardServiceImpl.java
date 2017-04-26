package com.liutianhong.yikatong.service.impl;

import com.liutianhong.yikatong.service.CardService;
import com.liutianhong.yikatong.domain.Card;
import com.liutianhong.yikatong.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Card.
 */
@Service
@Transactional
public class CardServiceImpl implements CardService{

    private final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);
    
    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Save a card.
     *
     * @param card the entity to save
     * @return the persisted entity
     */
    @Override
    public Card save(Card card) {
        log.debug("Request to save Card : {}", card);
        Card result = cardRepository.save(card);
        return result;
    }

    /**
     *  Get all the cards.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Card> findAll() {
        log.debug("Request to get all Cards");
        List<Card> result = cardRepository.findAll();

        return result;
    }

    /**
     *  Get one card by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Card findOne(Long id) {
        log.debug("Request to get Card : {}", id);
        Card card = cardRepository.findOne(id);
        return card;
    }

    /**
     *  Delete the  card by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Card : {}", id);
        cardRepository.delete(id);
    }
}
