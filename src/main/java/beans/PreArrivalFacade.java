package beans;

import beans.ShipMaster;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PreArrivalFacade {

    public void add(ShipMaster paf);
    public void delete(String imo);
    public ShipMaster searchPaf(String imo);
    public List<ShipMaster> display();
}
