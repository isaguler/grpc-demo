package com.isaguler.warehouseapp.repository;

import com.isaguler.warehouseapp.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findByBookId(Long bookId);
}
