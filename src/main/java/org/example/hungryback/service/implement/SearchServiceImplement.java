package org.example.hungryback.service.implement;

import org.example.hungryback.dto.request.search.SearchRequestDto;
import org.example.hungryback.dto.response.search.SearchResponseDto;
import org.example.hungryback.entity.StoreEntity;
import org.example.hungryback.repository.StoreRepository;
import org.example.hungryback.service.SearchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SearchServiceImplement implements SearchService {

    private final StoreRepository storeRepository;

    public SearchServiceImplement(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<SearchResponseDto> searchList(SearchRequestDto dto) {
        String keyword = dto.getKeyword();

        // storeName 또는 storeAddress에 keyword가 포함되는 상점을 찾습니다.
        List<StoreEntity> stores = storeRepository.findByStoreSearchList(keyword, keyword);
        System.out.println(stores.size());
        // StoreEntity를 SearchResponseDto로 변환하여 반환합니다.
        return stores.stream()
                .map(this::mapToSearchResponseDto)
                .collect(Collectors.toList());
    }

    private SearchResponseDto mapToSearchResponseDto(StoreEntity storeEntity) {
        return new SearchResponseDto(
                storeEntity.getStoreId(),
                storeEntity.getStoreName(),
                storeEntity.getStoreAddress(),
                storeEntity.getStoreLatitude(),
                storeEntity.getStoreLongitude(),
                storeEntity.getStoreTel(),
                storeEntity.getStoreDescription()
        );
    }
}
