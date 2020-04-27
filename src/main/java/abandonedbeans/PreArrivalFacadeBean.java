package abandonedbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PreArrivalFacadeBean implements PreArrivalFacade {

    @PersistenceContext(name="myPortCall")
    EntityManager em;


    @Override
    public void add(ShipMaster paf) {
        em.persist(paf);
    }

    @Override
    public void delete(String imo) {
        ShipMaster paf = em.find(ShipMaster.class,imo);
        em.remove(paf);
    }

    @Override
    public ShipMaster searchPaf(String imo) {
        return em.find(ShipMaster.class, imo);
    }

    @Override
    public List<ShipMaster> display() {
        String sql = "SELECT c from ShipAgent c";
        Query query = em.createQuery(sql);
        System.out.println(query.toString());
        List<ShipMaster> list = query.getResultList();
        return list;
        }
    }
