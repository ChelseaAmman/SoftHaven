package abandonedbeans;

import javax.ejb.Local;
import java.util.List;

@Local
public interface BerthingRecordFacade {

    public void add(ShipAgent br);
    public void delete(String imo);
    public ShipAgent searchBR(String imo);
    public List<ShipAgent> display();
}
