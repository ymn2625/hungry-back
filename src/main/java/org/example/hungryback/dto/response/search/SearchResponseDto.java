package org.example.hungryback.dto.response.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.entity.StoreEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponseDto extends ResponseDto {

    private int storeId;
    private String storeName;
    private String storeAddress;
    private String storeLatitude;
    private String storeLongitude;
    private String storeTel;
    private String storeDescription;

    public static ResponseEntity<SearchResponseDto> success(StoreEntity storeEntity) {
        SearchResponseDto responseBody = new SearchResponseDto(
                storeEntity.getStoreId(),
                storeEntity.getStoreName(),
                storeEntity.getStoreAddress(),
                storeEntity.getStoreLatitude(),
                storeEntity.getStoreLongitude(),
                storeEntity.getStoreTel(),
                storeEntity.getStoreDescription()
        );
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> storeNotExist(StoreEntity storeEntity) {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EIXST_STORE, ResponseMessage.NOT_EXIST_STORE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
