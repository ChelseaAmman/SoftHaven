package abandonedbeans;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import java.io.StringReader;

@MessageDriven(mappedName = "jms/myRecords")
public class PortAuthorityBerthRecord implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage tm = (TextMessage) message;
            try {
                String text = tm.getText();
                System.out.println("Message Received: " + text);
                ShipAgent bRecord = deserialize(text);
                BerthingRecordFacadeBean n = new BerthingRecordFacadeBean();
                n.add(bRecord);
            }catch(Exception e){
                System.out.println("Message bean error: " + e);
            }
        }
    }

    public ShipAgent deserialize(String paf){
        JsonValue jsonVal = Json.createReader( new StringReader( paf )).read();
        JsonObject obj = (JsonObject) jsonVal;
        return createNOA(obj);
    }

    private ShipAgent createNOA(JsonObject obj){
        int imo = new Integer(obj.get("imoNum").toString());
        JsonString quayObj = (JsonString) obj.get("quay");
        String quay = quayObj.getString();
        quay = quay.replace("\"", "");
        int bNum = new Integer(obj.get("bNum").toString());
        int eta = new Integer(obj.get("eta").toString());
        int ata = new Integer(obj.get("ata").toString());
        int etd = new Integer(obj.get("etd").toString());
        int atd = new Integer(obj.get("atd").toString());
        int status = new Integer(obj.get("status").toString());
        return new ShipAgent(imo,quay,bNum,eta,ata,etd,atd,status);
    }
}
