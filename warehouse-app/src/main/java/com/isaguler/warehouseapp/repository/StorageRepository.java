package com.isaguler.warehouseapp.repository;

import com.isaguler.warehouseapp.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

    Optional<Storage> findByBookId(Long bookId);
}
