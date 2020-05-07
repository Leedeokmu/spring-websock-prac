package com.websocket.websocketprac.chat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter @Getter @NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ChatRoom implements Serializable {
    private static final long serialVersionUID = 649467835489606639L;
    private String roomId;
    private String name;
    private long userCount;

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomId(UUID.randomUUID().toString());
        chatRoom.setName(name);
        return chatRoom;
    }
}
