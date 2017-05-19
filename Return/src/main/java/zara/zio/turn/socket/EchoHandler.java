package zara.zio.turn.socket;

import java.util.ArrayList;
import java.util.List;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
 
 
public class EchoHandler extends TextWebSocketHandler {
    
    private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
    
    //��� ������ �����Ѵ�
    //���1 Map
    //private Map <String,WebSocketSession> sessions = new HashMap<String, WebSocketSession>();
    
    //���2 List //��üä��//����������
    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
    
    
    /**
     * Ŭ���̾�Ʈ ���� ���Ŀ� ����Ǵ� �޼ҵ�
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //1 MAP :
        //session.put(session.getId(), session);
        //2 List :
        sessionList.add(session);
        logger.info(" {} conntected ", session.getId());
    }
    /**
     * Ŭ���̾�Ʈ�� ������ ������ �޽����� �������� �� ����Ǵ� �޼ҵ�
     */
    @Override
    protected void handleTextMessage(WebSocketSession session,
            TextMessage message) throws Exception {
        System.out.println("dasdas");
        //�Ʒ��� ���̴� �ִ� 2��
        logger.info("From {}, recieved Message : {} ", session.getId(), message.getPayload() );
                
        //����Ǿ��ִ� ��� Ŭ���̾�Ʈ�鿡�� �޽����� �����Ѵ�
        //2 List
        for(WebSocketSession sess : sessionList){
            sess.sendMessage(new TextMessage(session.getId() +": " + message.getPayload() ));
        }
        
        //1 Map
//        Iterator <String> sessionIds = sessions.keySet().iterator();
//        String sessionId = "";
//        while(sessionIds.hasNext()){
//            sessionId = sessionIds.next();
//            sessions.get(sessionId).sendMessage(new TextMessage("echo: " + message.getPayload() ));
//        }
//        
        //session.sendMessage(new TextMessage("echo: " + message.getPayload() ));
    }
    
    /**
     * Ŭ���̾�Ʈ�� ������ ������ �� ����Ǵ� �޼ҵ�
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session,
            CloseStatus status) throws Exception {
        
        //2 List
        sessionList.remove(session);
        
        //1 Map
        //sessions.remove(session.getId());
        
        logger.info(" {} Connection Closed ", session.getId());
    }
 
}
 