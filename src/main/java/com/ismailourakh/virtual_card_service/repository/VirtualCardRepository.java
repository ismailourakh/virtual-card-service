package com.ismailourakh.virtual_card_service.repository;

import com.ismailourakh.virtual_card_service.model.VirtualCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VirtualCardRepository extends JpaRepository<VirtualCard, UUID> {
    List<VirtualCard> findByUserId(UUID userId);
}
