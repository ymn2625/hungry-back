package org.example.hungryback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import jakarta.persistence.*;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "store")
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeId;
    private String storeName;
    private String storeAddress;
    private String storeLatitude;
    private String storeLongitude;
    private String storeTel;
    private String storeDescription;

    @Builder
    public StoreEntity(String storeName, String storeAddress, String storeLatitude, String storeLongitude, String storeTel, String storeDescription) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeLatitude = storeLatitude;
        this.storeLongitude = storeLongitude;
        this.storeTel = storeTel;
        this.storeDescription = storeDescription;
    }
}
