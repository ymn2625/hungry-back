package org.example.hungryback.common;

public interface ResponseCode {
    String SUCCESS = "SU";

    String VALIDATION_FAIL = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_TEL = "DT";
    String DUPLICATE_PARTY_MEMBER = "DPM";
    String NOT_EXIST_USER = "NU";
    String NOT_EXIST_STORE = "NS";
    String NOT_EXIST_PARTY = "NP";
    String NOT_EXIST_PARTY_MEMBER = "NPM";

    String PARTY_LEADER = "PL";

    String SIGN_IN_FAIL = "SF";
    String CERTIFICATION_FAIL = "CF";
    String REISSUE_FAIL = "RF";

    String MESSAGE_FAIL = "MF";
    String DATABASE_ERROR = "DBE";

    String INVALID_TOKEN = "IT";
    String MESSAGE_DELIVERY_ERROR = "MDE";
    String UNEXPECTED_MESSAGE_ERROR = "UME";

    String NO_PERMISSION = "NP";

}
