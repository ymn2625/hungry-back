package org.example.hungryback.common;

public interface ResponseCode {
    String SUCCESS = "SU";

    String EXPIRED_TOKEN = "ET";

    String VALIDATION_FAIL = "VF";
    String DUPLICATE_ID = "DI";
    String DUPLICATE_TEL = "DT";
    String NOT_EIXST_USER = "NU";

    String SIGN_IN_FAIL = "SF";
    String CERTIFICATION_FAIL = "CF";

    String MESSAGE_FAIL = "MF";
    String DATABASE_ERROR = "DBE";
}
