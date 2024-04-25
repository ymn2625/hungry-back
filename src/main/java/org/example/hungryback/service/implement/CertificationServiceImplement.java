package org.example.hungryback.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.request.certification.CheckCertificationRequestDto;
import org.example.hungryback.dto.request.certification.SendCertificationRequestDto;
import org.example.hungryback.dto.response.certification.CheckCertificationResponseDto;
import org.example.hungryback.dto.response.certification.SendCertificationResponseDto;
import org.example.hungryback.entity.CertificationEntity;
import org.example.hungryback.entity.UserEntity;
import org.example.hungryback.provider.CertificationProvider;
import org.example.hungryback.repository.CertificationRepository;
import org.example.hungryback.repository.UserRepository;
import org.example.hungryback.service.CertificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificationServiceImplement implements CertificationService {

    private final CertificationRepository certificationRepository;
    private final UserRepository userRepository;

    private final CertificationProvider certificationProvider;


    // 인증번호 전송
    @Override
    public ResponseEntity<? super SendCertificationResponseDto> sendCertification(SendCertificationRequestDto dto) {
        try {

            String userTel = dto.getUserTel();
            String certificationNumber = certificationProvider.createRandomNumber();

            boolean isSuccessed = certificationProvider.sendCertification(userTel, certificationNumber);
            if(!isSuccessed) return SendCertificationResponseDto.messageSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(userTel, certificationNumber);
            certificationRepository.save(certificationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SendCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {

        String userEmail = null;

        try {

            String userTel = dto.getUserTel();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUserTel(userTel);
            if(certificationEntity == null || !certificationEntity.getCertificationNumber().equals(certificationNumber))
                return CheckCertificationResponseDto.certificationFail();

            certificationRepository.delete(certificationEntity);

            UserEntity userEntity = userRepository.findByUserTel(userTel);
            if(userEntity != null ) userEmail = userEntity.getUserEmail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return CheckCertificationResponseDto.success(userEmail);
    }
}
