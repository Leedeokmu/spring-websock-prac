package com.websocket.websocketprac.chat;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 649467835489606639L;

    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK, JOIN
    }
    private MessageType type; // 메시지 타입
    private String roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}