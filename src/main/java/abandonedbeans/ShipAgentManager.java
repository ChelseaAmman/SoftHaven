package abandonedbeans;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.*;


@SessionScoped
@Named("shipagntmngr")
public class ShipAgentManager implements Serializable {

    private static final long serialVersionUID = 1L;

    private ShipAgent record = new ShipAgent();

    @Resource(mappedName = "jms/myRecords")
    private Queue logMessages;

    @Resource(mappedName = "jms/myMessageFactory1")
    private ConnectionFactory logFactory;

    public void setRecord(ShipAgent record) {
        this.record = record;
    }

    public ShipAgent getRecord() {
        return record;
    }

    public void mesg() {
        System.out.println("Message Sending");
        String j = record.toJsonString();
        sendMesg(j);
        System.out.println("Message Sent Successfully!");
    }

    public void sendMesg(String message) {
        MessageProducer messageProducer;
        TextMessage textMessage;
        try
        {
            Connection connection = logFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(logMessages);
            textMessage = session.createTextMessage();

            textMessage.setText(message);
            System.out.println("Sending the following message: "
                    + textMessage.getText());
            messageProducer.send(textMessage);

            messageProducer.close();
            session.close();
            connection.close();
        }
        catch (JMSException e)
        {
            e.printStackTrace();
        }
    }
}
