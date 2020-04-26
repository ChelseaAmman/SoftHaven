package beans;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "jms/myLog")
public class PortAuthority implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage tm = (TextMessage) message;
            try {
                String text = tm.getText();
                System.out.println("Message Received: " + text);
            }catch(Exception e){
                System.out.println("Message bean error: " + e);
            }
        }
    }
}
