package com.ismailourakh.virtual_card_service.service;

import com.ismailourakh.virtual_card_service.model.VirtualCard;
import com.ismailourakh.virtual_card_service.repository.VirtualCardRepository;
import com.ismailourakh.virtual_card_service.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class VirtualCardService {

    @Autowired
    private VirtualCardRepository virtualCardRepository;

    public VirtualCard createCard(UUID userId, BigDecimal limitAmount) {
        try {
            VirtualCard card = new VirtualCard();
            card.setUserId(userId);
            card.setCardNumber(EncryptionUtil.encrypt(generateCardNumber()));
            card.setCvv(EncryptionUtil.encrypt(generateCVV()));
            card.setExpiryDate("12/28");
            card.setStatus(VirtualCard.CardStatus.ACTIVE);
            card.setLimitAmount(limitAmount);
            card.setCreatedAt(LocalDateTime.now());
            return virtualCardRepository.save(card);
        } catch (Exception e) {
            throw new RuntimeException("Error while generating card", e);
        }
    }

    public List<VirtualCard> getUserCards(UUID userId) {
        return virtualCardRepository.findByUserId(userId);
    }

    public void deactivateCard(UUID cardId) {
        VirtualCard card = virtualCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        card.setStatus(VirtualCard.CardStatus.INACTIVE);
        virtualCardRepository.save(card);
    }

    public boolean validateTransaction(UUID cardId, BigDecimal amount) {
        VirtualCard card = virtualCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        if (card.getStatus() == VirtualCard.CardStatus.INACTIVE) {
            throw new RuntimeException("Card is inactive");
        }
        if (amount.compareTo(card.getLimitAmount()) > 0) {
            throw new RuntimeException("Transaction amount exceeds the card limit");
        }
        return true;
    }

    public void updateLimit(UUID cardId, BigDecimal newLimit) {
        VirtualCard card = virtualCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        card.setLimitAmount(newLimit);
        virtualCardRepository.save(card);
    }

    private String generateCardNumber() {
        return "4000" + (long) (Math.random() * 1_000_000_000L);
    }

    private String generateCVV() {
        return String.valueOf((int) (Math.random() * 900 + 100));
    }
}
