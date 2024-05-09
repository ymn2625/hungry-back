package org.example.hungryback.common;

public interface ResponseMessage {
    String SUCCESS = "Success.";

    String EXPIRED_TOKEN = "Expired token.";

    String VALIDATION_FAIL = "Validation failed.";
    String DUPLICATE_ID = "Duplicate id.";
    String DUPLICATE_TEL = "Duplicate tel.";
    String NOT_EXIST_USER = "Not exist user";

    String SIGN_IN_FAIL = "Login information mismatch.";
    String CERTIFICATION_FAIL = "Certification failed.";
    String REISSUE_FAIL = "JWT reissue failed";

    String MESSAGE_FAIL = "Message send failed.";
    String DATABASE_ERROR = "Database error.";
}
