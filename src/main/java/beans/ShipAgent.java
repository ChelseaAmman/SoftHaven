package beans;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.io.Serializable;
import java.io.StringWriter;

public class ShipAgent implements Serializable {

    private static final long serialVersionUID = 1L;
    private int imoNum;
    private String quay;
    private int bNum;
    private int eta;
    private int ata;
    private int etd;
    private int atd;
    private int status;


    public ShipAgent(int imoNum,String quay, int bNum, int eta, int ata, int etd, int atd, int status) {
        this.imoNum = imoNum;
        this.quay = quay;
        this.bNum = bNum;
        this.eta = eta;
        this.ata = ata;
        this.etd = etd;
        this.atd = atd;
        this.status = status;
    }

    public ShipAgent(){}


    public String toJsonString() {
        StringWriter sw = new StringWriter();
        JsonWriter jw = Json.createWriter(sw);
        jw.writeObject(toJsonObject());
        jw.close();
        return sw.toString();
    }

    public JsonObject toJsonObject() {
        JsonObject model = Json.createObjectBuilder()
                .add("imoNum", imoNum)
                .add("quay", quay)
                .add("bNum", bNum)
                .add("eta", eta)
                .add("ata", ata)
                .add("etd", etd)
                .add("atd", atd)
                .add("status", status)
                .build();
        return model;
    }

    public int getImoNum() {
        return imoNum;
    }

    public void setImoNum(int imoNum) {
        this.imoNum = imoNum;
    }

    public String getQuay() {
        return quay;
    }

    public void setQuay(String quay) {
        this.quay = quay;
    }

    public int getbNum() {
        return bNum;
    }

    public void setbNum(int bNum) {
        this.bNum = bNum;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public int getAta() {
        return ata;
    }

    public void setAta(int ata) {
        this.ata = ata;
    }

    public int getEtd() {
        return etd;
    }

    public void setEtd(int etd) {
        this.etd = etd;
    }

    public int getAtd() {
        return atd;
    }

    public void setAtd(int atd) {
        this.atd = atd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
