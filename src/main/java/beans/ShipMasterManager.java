package beans;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;


@SessionScoped
@Named("shipmstrmngr")
public class ShipMasterManager implements Serializable {

    private static final long serialVersionUID = 1L;

    private ShipMaster notice = new ShipMaster();
    @Resource(mappedName = "jms/myLog") private Queue logMessages;

    @Resource(mappedName = "jms/myMessageFactory1") private ConnectionFactory logFactory;

    public void setNotice( ShipMaster notice ) {
        this.notice = notice;
    }

    public ShipMaster getNotice() { return notice ; }

    public void mesg() {
        System.out.println("Message Sending");
        String j = notice.toJsonString();
        sendMesg(j);
        System.out.println("Message Sent Successfully!");
    }

    public void sendMesg(String message) {
        MessageProducer messageProducer;
        TextMessage textMessage;

        try{
            Connection conn = logFactory.createConnection();
            Session session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(logMessages);
            textMessage=session.createTextMessage();
            textMessage.setText(message);
            messageProducer.send(textMessage);
            messageProducer.close();
            session.close();
            conn.close();
        }catch(JMSException e){
            System.out.println("error: " + e);
        }
    }
}
