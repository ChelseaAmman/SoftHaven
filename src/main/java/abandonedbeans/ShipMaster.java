package abandonedbeans;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.persistence.*;
import java.io.Serializable;
import java.io.StringWriter;

@Entity(name="ShipMaster")
@Table(name="Prearrival")
public class ShipMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    private String sName;
    private String callSign;

    @Id
    private int imoNum;

    private String agentInfo;
    private String aForm;
    private int eta;
    private int bNum;
    private String nextPort;
    private int etd;
    private int disCargoA;
    private String discargoD;
    private int loadCargoA;
    private String loadCargoD;
    private int nPassArr;
    private int nPassDep;


    public ShipMaster(String sName, String callSign, int imoNum, String agentInfo, String aForm,
                      int eta, int bNum, String nextPort, int etd, String disCargoD, int disCargoA,
                      String loadCargoD, int loadCargoA, int nPassArr, int nPassDep) {
        this.sName = sName;
        this.callSign = callSign;
        this.imoNum = imoNum;
        this.agentInfo = agentInfo;
        this.aForm = aForm;
        this.eta = eta;
        this.bNum = bNum;
        this.nextPort = nextPort;
        this.etd = etd;
        this.disCargoA = disCargoA;
        this.discargoD = disCargoD;
        this.loadCargoA = loadCargoA;
        this.loadCargoD = loadCargoD;
        this.nPassArr = nPassArr;
        this.nPassDep = nPassDep;
    }

    public ShipMaster(){}


    public String toJsonString() {
        StringWriter sw = new StringWriter();
        JsonWriter jw = Json.createWriter(sw);
        jw.writeObject(toJsonObject());
        jw.close();
        return sw.toString();
    }

    public JsonObject toJsonObject() {
        JsonObject model = Json.createObjectBuilder()
                .add("sName", sName)
                .add("callSign", callSign)
                .add("imoNum", imoNum)
                .add("agentInfo", agentInfo)
                .add("aForm", aForm)
                .add("eta", eta)
                .add("bNum", bNum)
                .add("nextPort", nextPort)
                .add("etd", etd)
                .add("disCargoA", disCargoA)
                .add("discargoD", discargoD)
                .add("loadCargoA", loadCargoA)
                .add("loadCargoD", loadCargoD)
                .add("nPassArr", nPassArr)
                .add("nPassDep", nPassDep)
                .build();
        return model;
    }


    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public int getImoNum() {
        return imoNum;
    }

    public void setImoNum(int imoNum) {
        this.imoNum = imoNum;
    }

    public String getAgentInfo() {
        return agentInfo;
    }

    public void setAgentInfo(String agentInfo) {
        this.agentInfo = agentInfo;
    }

    public String getaForm() {
        return aForm;
    }

    public void setaForm(String aForm) {
        this.aForm = aForm;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public int getbNum() {
        return bNum;
    }

    public void setbNum(int bNum) {
        this.bNum = bNum;
    }

    public String getNextPort() {
        return nextPort;
    }

    public void setNextPort(String nextPort) {
        this.nextPort = nextPort;
    }

    public int getEtd() {
        return etd;
    }

    public void setEtd(int etd) {
        this.etd = etd;
    }

    public int getDisCargoA() {
        return disCargoA;
    }

    public void setDisCargoA(int disCargoA) {
        this.disCargoA = disCargoA;
    }

    public String getDiscargoD() {
        return discargoD;
    }

    public void setDiscargoD(String discargoD) {
        this.discargoD = discargoD;
    }

    public int getLoadCargoA() {
        return loadCargoA;
    }

    public void setLoadCargoA(int loadCargoA) {
        this.loadCargoA = loadCargoA;
    }

    public String getLoadCargoD() {
        return loadCargoD;
    }

    public void setLoadCargoD(String loadCargoD) {
        this.loadCargoD = loadCargoD;
    }

    public int getnPassArr() {
        return nPassArr;
    }

    public void setnPassArr(int nPassArr) {
        this.nPassArr = nPassArr;
    }

    public int getnPassDep() {
        return nPassDep;
    }

    public void setnPassDep(int nPassDep) {
        this.nPassDep = nPassDep;
    }
}
