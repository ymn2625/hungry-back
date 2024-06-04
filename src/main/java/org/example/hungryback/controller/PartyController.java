package org.example.hungryback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.request.party.PartyRequestDto;
import org.example.hungryback.dto.response.party.PartyResponseDto;
import org.example.hungryback.service.PartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/party")
@RequiredArgsConstructor
public class PartyController {

    private final PartyService partyService;


    @PostMapping("/partyList")
    public ResponseEntity<List<PartyResponseDto>> partyList(@RequestBody @Valid PartyRequestDto requestBody) {

        List<PartyResponseDto> response = partyService.partyList(requestBody);
        // response가 비어있지 않은지 확인
        if (!response.isEmpty()) {
            // 첫 번째 요소의 PartyName 출력
            System.out.println("partyName of the first element: " + response.get(0).getPartyName());
        } else {
            // response가 비어있는 경우 콘솔에 메시지 출력
            System.out.println("Response is empty?");
        }
        System.out.println("파티리스트" + response.size());
        return ResponseEntity.ok(response);

    }

    @PostMapping("/partyInfo")
    public ResponseEntity<PartyResponseDto> partyInfo(@RequestBody @Valid PartyRequestDto requestBody) {
        PartyResponseDto response = partyService.partyInfo(requestBody);
        return ResponseEntity.ok(response);
    }

}


