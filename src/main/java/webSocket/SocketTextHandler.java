package webSocket;

import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketTextHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws Exception {
        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        webSocketSession.sendMessage(new TextMessage("Merhaba" + jsonObject.get("user") + "bugün sana nasıl yardımcı olabilirim?"));

    }
}
