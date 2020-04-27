package dao;

import beans.PreArrivalForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;


@ApplicationScoped @JDBC
public class MasterDAO {

    @Resource( lookup="jdbc/MyPortAuthorityDB")
    private DataSource dataSource;
    private List<PreArrivalForm> pafList = new ArrayList<PreArrivalForm>();

    static interface RunJDBC <T>{
        List<PreArrivalForm> run(Connection con) throws Exception;
    }

    private <T> T withDB(RunJDBC<T> runner) {
        Connection con = null;
        T result = null;
        try {
            con = dataSource.getConnection();
            boolean auto = con.getAutoCommit();
            con.setAutoCommit(false);
            result = (T) runner.run(con);
            con.commit();
            con.setAutoCommit(auto);
        } catch (Exception e) {
            System.out.println(e);
            try { con.rollback(); } catch( SQLException ex) {}
        } finally {
            if (con!=null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public void addPaf(final PreArrivalForm paf) {

        withDB((RunJDBC<PreArrivalForm>) con -> {
            PreparedStatement req = con.prepareStatement(
                    "INSERT INTO prearrival values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            req.setString(1, paf.getsName());
            req.setString(2, paf.getCallSign());
            req.setInt(3, paf.getImoNum());
            req.setString(4, paf.getAgentInfo());
            req.setString(5, paf.getaForm());
            req.setInt(6, paf.getEta());
            req.setInt(7, paf.getbNum());
            req.setString(8, paf.getNextPort());
            req.setInt(9, paf.getEtd());
            req.setString(10, paf.getDiscargoD());
            req.setInt(11, paf.getDisCargoA());
            req.setString(12, paf.getLoadCargoD());
            req.setInt(13, paf.getLoadCargoA());
            req.setInt(14, paf.getnPassArr());
            req.setInt(15, paf.getnPassDep());
            req.setInt(16, paf.getFormVal());
            int nbLines = req.executeUpdate();
            if (nbLines!=1) {
                throw new RecordException("Failed insertion!");
            }
            return null;
        });
    }

    public void addFormToList(PreArrivalForm paf) {
        this.pafList.add(paf);
    }


    private class RecordException extends Exception {
        /**
         *
         */
        private static final long serialVersionUID = 7418836201378164160L;

        public RecordException( String mess) {
            super( mess) ;
        }
    }


    private List<PreArrivalForm> list(){
        return withDB( new RunJDBC<List<PreArrivalForm>>() {
            @Override
            public List<PreArrivalForm> run (Connection con) throws Exception {
                List<PreArrivalForm> list = new ArrayList<PreArrivalForm>();
                Statement stt = con.createStatement();
                final String req = "select * from prearrival";
                ResultSet rs = stt.executeQuery(req);
                while (rs.next()) {
                    PreArrivalForm paf = new PreArrivalForm();
                    list.add( paf );
                }

                return list;
            }
        });
    }
}
