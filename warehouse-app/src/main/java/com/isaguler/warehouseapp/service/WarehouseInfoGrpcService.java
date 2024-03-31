package com.isaguler.warehouseapp.service;

import com.isaguler.grpc.WarehouseInfoServiceGrpc;
import com.isaguler.grpc.WarehouseResponse;
import com.isaguler.warehouseapp.model.Discount;
import com.isaguler.warehouseapp.model.Storage;
import com.isaguler.warehouseapp.repository.DiscountRepository;
import com.isaguler.warehouseapp.repository.StorageRepository;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@Slf4j
@GrpcService
public class WarehouseInfoGrpcService extends WarehouseInfoServiceGrpc.WarehouseInfoServiceImplBase {

    private final DiscountRepository discountRepository;
    private final StorageRepository storageRepository;

    public WarehouseInfoGrpcService(DiscountRepository discountRepository, StorageRepository storageRepository) {
        this.discountRepository = discountRepository;
        this.storageRepository = storageRepository;
    }

    @Override
    public void getByBookId(com.isaguler.grpc.WarehouseRequest request,
                            io.grpc.stub.StreamObserver<com.isaguler.grpc.WarehouseResponse> responseObserver) {
        Optional<Discount> optionalDiscount = discountRepository.findByBookId(request.getId());
        Optional<Storage> optionalStorage = storageRepository.findByBookId(request.getId());

        if (optionalDiscount.isPresent() && optionalStorage.isPresent()) {

            responseObserver.onNext(WarehouseResponse.newBuilder()
                    .setDiscountAmount(optionalDiscount.get().getDiscountAmount().longValue())
                    .setCount(optionalStorage.get().getCount())
                    .build());

            responseObserver.onCompleted();
        } else {
            log.error("no storage or discount check db for book id : " + request.getId());

            responseObserver.onNext(WarehouseResponse.newBuilder().build());
            responseObserver.onCompleted();
        }
    }

}
