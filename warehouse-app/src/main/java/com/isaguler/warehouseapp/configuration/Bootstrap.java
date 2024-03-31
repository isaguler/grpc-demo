package com.isaguler.warehouseapp.configuration;

import com.isaguler.warehouseapp.model.Discount;
import com.isaguler.warehouseapp.model.Storage;
import com.isaguler.warehouseapp.repository.DiscountRepository;
import com.isaguler.warehouseapp.repository.StorageRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Bootstrap {

    private final StorageRepository storageRepository;
    private final DiscountRepository discountRepository;

    public Bootstrap(StorageRepository storageRepository, DiscountRepository discountRepository) {
        this.storageRepository = storageRepository;
        this.discountRepository = discountRepository;
    }

    @PostConstruct
    void init() {
        Storage storage1 = new Storage();
        storage1.setCount(20L);
        storage1.setBookId(1L);

        Storage storage2 = new Storage();
        storage2.setCount(15L);
        storage2.setBookId(2L);

        Storage storage3 = new Storage();
        storage3.setCount(10L);
        storage3.setBookId(3L);

        storageRepository.saveAll(List.of(storage1, storage2, storage3));

        Discount discount1 = new Discount();
        discount1.setBookId(1L);
        discount1.setDiscountAmount(new BigDecimal(10));

        Discount discount2 = new Discount();
        discount2.setBookId(2L);
        discount2.setDiscountAmount(new BigDecimal(5));

        Discount discount3 = new Discount();
        discount3.setBookId(3L);
        discount3.setDiscountAmount(new BigDecimal(5));

        discountRepository.saveAll(List.of(discount1, discount2, discount3));

    }
}
