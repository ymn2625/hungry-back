package org.example.hungryback.controller;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.service.PartyService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PartyController {
    private final PartyService partyService;
}
