package beans;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import java.io.StringReader;

@MessageDriven(mappedName = "jms/myLog")
public class PortAuthority implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage tm = (TextMessage) message;
            try {
                String text = tm.getText();
                System.out.println("Message Received: " + text);
                ShipMaster paf = deserialize(text);
                PreArrivalFacadeBean n = new PreArrivalFacadeBean();
                n.add(paf);
            }catch(Exception e){
                System.out.println("Message bean error: " + e);
            }
        }
    }

    public ShipMaster deserialize(String paf){
        JsonValue jsonVal = Json.createReader( new StringReader( paf )).read();
        JsonObject obj = (JsonObject) jsonVal;
        return createNOA(obj);
    }

    private ShipMaster createNOA(JsonObject obj){
        JsonString nameObj = (JsonString) obj.get("sName");
        String sName = nameObj.getString();
        sName = sName.replace("\"", "");
        JsonString callSignObj = (JsonString) obj.get("callSign");
        String callSign = callSignObj.getString();
        callSign = callSign.replace("\"", "");
        int imo = new Integer(obj.get("imoNum").toString());
        JsonString agentInfoObj = (JsonString) obj.get("agentInfo");
        String agentInfo = agentInfoObj.getString();
        agentInfo = agentInfo.replace("\"", "");
        JsonString aFormObj = (JsonString) obj.get("aForm");
        String aForm = aFormObj.getString();
        aForm = aForm.replace("\"", "");
        int eta = new Integer(obj.get("eta").toString());
        int bNum = new Integer(obj.get("bNum").toString());
        JsonString nextPortObj = (JsonString) obj.get("nextPort");
        String nextPort = nextPortObj.getString();
        nextPort = nextPort.replace("\"", "");
        int etd = new Integer(obj.get("etd").toString());
        int disCargoA = new Integer(obj.get("disCargoA").toString());
        JsonString disCargoDObj = (JsonString) obj.get("disCargoD");
        String disCargoD = disCargoDObj.getString();
        disCargoD = disCargoD.replace("\"", "");
        int loadCargoA = new Integer(obj.get("loadCargoA").toString());
        JsonString loadCargoDObj = (JsonString) obj.get("loadCargoD");
        String loadCargoD = loadCargoDObj.getString();
        loadCargoD = loadCargoD.replace("\"", "");
        int nPassArr = new Integer(obj.get("nPassArr").toString());
        int nPassDep = new Integer(obj.get("nPassDep").toString());
        return new ShipMaster(sName,callSign,imo,agentInfo,aForm,eta,bNum,nextPort,
                etd,disCargoD,disCargoA,loadCargoD,loadCargoA,nPassArr,nPassDep);
    }
}
