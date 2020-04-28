package com.websocket.websocketprac.chat;

import com.websocket.websocketprac.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate redisTemplate;
    private final ChannelTopic channelTopic;

    @MessageMapping("/chat/message")
    public void message(
            ChatMessage message,
            @Header(name = "token") String token
    ) {
        // 토큰에서 사용자명 불러옴
        String nickname = jwtTokenProvider.getUserNameFromJwt(token);

        message.setSender(nickname);
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setSender("[알림]");
            message.setMessage(nickname + "님이 입장하셨습니다.");
        }
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }
}
