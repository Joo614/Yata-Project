package com.yata.backend.global.exception;

import lombok.Getter;
@Getter
public enum ExceptionCode {
   PAYMENT_NOT_FOUND(404, "PAYMENT_NOT_FOUND"),
   PAYMENT_AMOUNT_EXP(400 , "PAYMENT_AMOUNT_NOT_EQUAL"),

   TITLE_NONE(400, "TITLE_NONE"),
   MEMBER_NONE(404, "MEMBER_NONE"),

   MEMBER_DUPLICATE(409, "MEMBER_DUPLICATE"),

   TOKEN_INVALID(401 , "TOKEN_INVALID"),
   EMAIL_NONE(400, "EMAIL_NEEDED"),
   TOKEN_NOT_EXPIRED(400, "TOKEN_NOT_EXPIRED"),
   REFRESH_TOKEN_NOT_FOUND(400, "REFRESH_TOKEN_NOT_FOUND"),
   REFRESH_TOKEN_INVALID(400, "REFRESH_TOKEN_INVALID"),
   REFRESH_TOKEN_NOT_MATCH(400, "REFRESH_TOKEN_NOT_MATCH"),
   ALREADY_APPLIED(409,"ALREADY_APPLIED"),
   ALREADY_INVITED(409,"ALREADY_INVITED"),
//   ALREADY_APPROVED(409,"ALREADY_APPROVED"),
   CANNOT_DELETE(403,"CANNOT_DELETE"),
   POST_NONE(404,"POST_NONE");

   @Getter
   private final int code;

   @Getter
   private final String message;

   ExceptionCode(int code, String message) {
      this.code = code;
      this.message = message;
   }
}
