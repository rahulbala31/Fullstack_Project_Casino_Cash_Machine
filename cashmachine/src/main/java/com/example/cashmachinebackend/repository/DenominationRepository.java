package com.example.cashmachinebackend.repository;
import java.util.Optional;

import com.example.cashmachinebackend.model.Denomination;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DenominationRepository extends JpaRepository<Denomination, Long> {

    /**
     * Returns all denominations with quantity > 0, sorted by value descending.
     * Used to get only available denominations for dispensing cash.
     */
    List<Denomination> findByQuantityGreaterThanOrderByValueDesc(int minQuantity);

    /**
     * Returns all denominations regardless of quantity, sorted by value descending.
     * Useful for admin or overview purposes.
     */
    List<Denomination> findAllByOrderByValueDesc();

    Optional<Denomination> findByValue(int value);
}
