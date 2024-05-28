package org.example.hungryback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.request.search.SearchRequestDto;
import org.example.hungryback.dto.response.search.SearchResponseDto;
import org.example.hungryback.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;


    @PostMapping("/searchList")
    public ResponseEntity<List<SearchResponseDto>> searchList(@RequestBody @Valid SearchRequestDto requestBody) {
        List<SearchResponseDto> response = searchService.searchList(requestBody);
        // response가 비어있지 않은지 확인
        if (!response.isEmpty()) {
            // 첫 번째 요소의 storeName 출력
            System.out.println("storeName of the first element: " + response.get(0).getStoreName() + response.get(0).getStoreAddress());
        } else {
            // response가 비어있는 경우 콘솔에 메시지 출력
            System.out.println("Response is empty");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/storeResult")
    public ResponseEntity<SearchResponseDto> storeResult(@RequestBody @Valid SearchRequestDto requestBody) {
        SearchResponseDto response = searchService.storeResult(requestBody);
        return ResponseEntity.ok(response);
    }

}


