package pl.barwinski.restaurantbackend.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@RequiredArgsConstructor
@Component
public class AuthSocketHandler extends TextWebSocketHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("Connected"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if(message.getPayload().startsWith("Bearer ")) {
            String jwt = message.getPayload().substring(7);
            if(jwtTokenProvider.validateToken(jwt)) {
                String username = jwtTokenProvider.extractEmailFromToken(jwt);
                session.getAttributes().put("username", username);
            }
            else
                session.close(CloseStatus.BAD_DATA);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if(status.equals(CloseStatus.BAD_DATA))
            session.sendMessage(new TextMessage("Invalid token"));
        else
            session.sendMessage(new TextMessage("Disconnected"));
    }
}

