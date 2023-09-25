package com.siliconvalley.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
    UNAUTHORIZATION(401, "C007", "Unauthorization Error"),
    INVALID_TOKEN(401, "C008", "Invalid Token Error"),
    EXPIRED_TOKEN(401, "C009", "Expired Token Error"),
    UNCONNECTED_REDIS(500, "C010", "Unconnected Redis Error"),
    EMPTY_REQUEST_BODY(400, "C011", "Empty Request Body"),


    // 도메인별 에러코드 추가
    MEMBER_NOT_FOUND(400, "M001", "Member Not Found"),
    FILE_UPLOAD_ERROR(500, "M002", "File Upload Error"),

    // Profile
    PROFILE_NAME_DUPLICATE(400, "P001", "Profile Name Duplicate"),
    PROFILE_NAME_INCLUDE_BAD_WORD(400, "P002", "Profile Name Has Bad Word"),

    // Item
    INVALID_CATEGORY(400,"I001", "Invalid category"),

    // RabbitMQ
    RABBITMQ_MESSAGE_REJECTED(400, "R001", "Rejected messaging"),
    RABBITMQ_ERROR(400, "R002", "Message Error");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}