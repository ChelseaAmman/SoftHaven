package abandonedbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class BerthingRecordFacadeBean implements BerthingRecordFacade {

    @PersistenceContext(name="myPortCall")
    EntityManager em;

    @Override
    public void add(ShipAgent br) {
        em.persist(br);
    }
    @Override
    public void delete(String imo) {
        ShipMaster paf = em.find(ShipMaster.class,imo);
        em.remove(paf);
    }

    @Override
    public ShipAgent searchBR(String imo) {
        return null;
    }

    @Override
    public List<ShipAgent> display() {
        String sql = "SELECT e from ShipMaster e";
        Query query = em.createQuery(sql);
        System.out.println(query.toString());
        List<ShipAgent> list = query.getResultList();
        return list;
    }
}
