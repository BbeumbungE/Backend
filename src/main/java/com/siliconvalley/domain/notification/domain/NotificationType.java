package com.siliconvalley.domain.notification.domain;

public enum NotificationType {

    NEW_AVATAR(" 아바타가 출시되었습니다."), // 새로운 아바타 알림
    NEW_SUBJECT(" 그림주제가 출시되었습니다."), // 새로운 그림 주제 알림
    RANKING("랭킹안에 들었습니다.") // 랭킹(8위) 안에 든거 확인
    ;

    private String message;

    NotificationType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
