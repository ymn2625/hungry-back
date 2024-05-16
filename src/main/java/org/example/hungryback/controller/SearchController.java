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
        System.out.println(response.size()+"왔냐구");
        return ResponseEntity.ok(response);
    }


}


