package org.example.hungryback.provider;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CertificationProvider {
    private DefaultMessageService messageService;

    @Value("${coolsms.apikey}")
    private String apiKey;
    @Value("${coolsms.apisecret}")
    private String apiSecret;
    @Value("${coolsms.fromnumber}")
    private String fromNumber;

    @PostConstruct
    public void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(this.apiKey, this.apiSecret, "https://api.coolsms.co.kr");
    }

    public String createRandomNumber() {
        String randomNumber = "";
        for(int i = 0; i < 6; i++) {
            int num = (int)(Math.random() * 10);
            randomNumber += Integer.toString(num);
        }
        return randomNumber;
    }

    public boolean sendCertification(String userTel, String certificationNumber) {
        try {

            Message message = new Message();
            message.setFrom(fromNumber);
            message.setTo(userTel);
            message.setText("[배고팟] 인증번호가 도착했습니다. \n [" + certificationNumber + "]");

            this.messageService.sendOne(new SingleMessageSendingRequest(message));

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
