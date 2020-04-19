package beans;

import java.io.Serializable;
import java.util.Queue;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.*;


@SessionScoped
@Named("shipmstrmngr")
public class ShipMasterManager implements Serializable {



    private static final long serialVersionUID = 1L;

    private ShipMaster notice = new ShipMaster();
    @Resource(mappedName = "jms/myLog")
    private Queue logMessages;
    @Resource(mappedName = "jms/myMessageFactory")
    private ConnectionFactory logFactory;

    public void setNotice( ShipMaster notice ) {
        this.notice = notice;
    }

    public ShipMaster getNotice() { return notice ; }

    public void mesg() {
        System.out.println("Message Sending");
        String j = notice.toJsonString();
        //sendMesg(j);
        System.out.println("Message Sent Successfully!");
    }

//    public void sendMesg(String message){
//        JMSContext context = logFactory.createContext();
//        JMSProducer mp = context.createProducer();
//        Message tm = context.createTextMessage(message);
//        mp.send((Destination) logMessages, tm);
//    }
}
