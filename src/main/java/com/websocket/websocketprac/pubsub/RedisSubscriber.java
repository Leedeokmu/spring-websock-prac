package com.websocket.websocketprac.pubsub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.websocket.websocketprac.chat.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber {
    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    public void sendMessage(String message) {
        try {
            // ChatMessage 객채로 맵핑
            ChatMessage roomMessage = objectMapper.readValue(message, ChatMessage.class);
            // Websocket 구독자에게 채팅 메시지 Send
            messagingTemplate.convertAndSend("/sub/chat/room/" + roomMessage.getRoomId(), roomMessage);
        } catch (Exception e) {
            log.error("[sendMessage] Exception", e);
        }
    }
}
