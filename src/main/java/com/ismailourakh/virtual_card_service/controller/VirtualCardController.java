package com.ismailourakh.virtual_card_service.controller;

import com.ismailourakh.virtual_card_service.model.VirtualCard;
import com.ismailourakh.virtual_card_service.service.VirtualCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/virtual-cards")
public class VirtualCardController {

    @Autowired
    private VirtualCardService virtualCardService;

    @PostMapping("/create")
    public ResponseEntity<VirtualCard> createCard(
            @RequestParam UUID userId,
            @RequestParam BigDecimal limitAmount) {
        return ResponseEntity.ok(virtualCardService.createCard(userId, limitAmount));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<VirtualCard>> getUserCards(@PathVariable UUID userId) {
        return ResponseEntity.ok(virtualCardService.getUserCards(userId));
    }

    @PutMapping("/deactivate/{cardId}")
    public ResponseEntity<Void> deactivateCard(@PathVariable UUID cardId) {
        virtualCardService.deactivateCard(cardId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validate/{cardId}")
    public ResponseEntity<Boolean> validateTransaction(
            @PathVariable UUID cardId,
            @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(virtualCardService.validateTransaction(cardId, amount));
    }

    @PutMapping("/update-limit/{cardId}")
    public ResponseEntity<Void> updateLimit(
            @PathVariable UUID cardId,
            @RequestParam BigDecimal newLimit) {
        virtualCardService.updateLimit(cardId, newLimit);
        return ResponseEntity.noContent().build();
    }
}
