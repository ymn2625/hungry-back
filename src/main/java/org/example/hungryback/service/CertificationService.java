package org.example.hungryback.service;

import org.example.hungryback.dto.request.certification.CheckCertificationRequestDto;
import org.example.hungryback.dto.request.certification.SendCertificationRequestDto;
import org.example.hungryback.dto.response.certification.CheckCertificationResponseDto;
import org.example.hungryback.dto.response.certification.SendCertificationResponseDto;
import org.springframework.http.ResponseEntity;

public interface CertificationService {
    ResponseEntity<? super SendCertificationResponseDto> sendCertification(SendCertificationRequestDto dto);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
}
