package com.fastporte.PaymentService.service.impl;

import com.fastporte.PaymentService.entities.Card;
import com.fastporte.PaymentService.repository.ICardRepository;
import com.fastporte.PaymentService.service.ICardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CardServiceImpl implements ICardService {

    private ICardRepository cardRepository;

    public CardServiceImpl(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    @Transactional
    public Card save(Card card) throws Exception {
        return cardRepository.save(card);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        cardRepository.deleteById(id);
    }

    @Override
    public Optional<Card> getById(Long id) throws Exception {
        return cardRepository.findById(id);
    }

    @Override
    public List<Card> getAll() throws Exception {
        return cardRepository.findAll();
    }

}
