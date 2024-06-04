package org.example.hungryback.common;

public interface ResponseMessage {
    String SUCCESS = "Success.";

    String EXPIRED_TOKEN = "Expired token.";

    String VALIDATION_FAIL = "Validation failed.";
    String DUPLICATE_EMAIL = "Duplicate email.";
    String DUPLICATE_TEL = "Duplicate tel.";
    String DUPLICATE_PARTY_MEMBER = "Duplicate party member";
    String NOT_EXIST_USER = "Not exist user";
    String NOT_EXIST_STORE = "Not exist store";
    String NOT_EXIST_PARTY = "Not exist party";
    String NOT_EXIST_PARTY_MEMBER = "Not exist party member";

    String SIGN_IN_FAIL = "Login information mismatch.";
    String CERTIFICATION_FAIL = "Certification failed.";
    String REISSUE_FAIL = "JWT reissue failed";

    String MESSAGE_FAIL = "Message send failed.";
    String DATABASE_ERROR = "Database error.";

    String NO_PERMISSION = "No permission.";
}
