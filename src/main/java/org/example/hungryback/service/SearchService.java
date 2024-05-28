package org.example.hungryback.service;

import org.example.hungryback.dto.request.search.SearchRequestDto;
import org.example.hungryback.dto.response.search.SearchResponseDto;

import java.util.List;

public interface SearchService {
    List<SearchResponseDto> searchList(SearchRequestDto dto);
    SearchResponseDto storeResult(SearchRequestDto dto);
}
