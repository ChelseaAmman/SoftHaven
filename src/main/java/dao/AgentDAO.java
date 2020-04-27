package dao;

import beans.BerthRecord;
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
public class AgentDAO {

    @Resource( lookup="jdbc/MyPortAuthorityDB")
    private DataSource dataSource;

    public BerthRecord find(final int id) {

        return withDB( new RunJDBC<BerthRecord>() {
            public BerthRecord run( Connection con) throws Exception {
                PreparedStatement req = con.prepareStatement(
                        "select * from Book where id = ?");
                req.setInt(1,  id);
                ResultSet rs = req.executeQuery();
                if (rs.next()) {
                    BerthRecord record = new BerthRecord();
                    record.setImoNum(rs.getInt("IMO Number"));
                    record.setQuay(rs.getString("Quay"));
                    record.setbNum(rs.getInt("Berth Number"));
                    return record;
                } else {
                    return null;
                }
            }
        });
    }

    public void add(BerthRecord record) {

        withDB( new RunJDBC<BerthRecord>() {
            public BerthRecord run( Connection con) throws Exception {
                PreparedStatement req = con.prepareStatement(
                        "insert into berthingrecord values (?,?,?,?,?,?,?,?)" );
                req.setInt(1,  record.getImoNum());
                req.setString(2,  record.getQuay());
                req.setInt(3, record.getbNum());
                req.setInt(4, record.getEta());
                req.setInt(5,record.getAta());
                req.setInt(6,record.getEtd());
                req.setInt(7,record.getAtd());
                req.setInt(8,record.getStatus());
                int nbLines = req.executeUpdate();
                if (nbLines != 1) {
                    throw new RecordException("Failed insertion!");
                }
                return null;
            }
        });
    }

    public void setRecordState(BerthRecord record, final int imo, int state){
        withDB( new RunJDBC<BerthRecord>() {
            public BerthRecord run( Connection con) throws Exception {
                PreparedStatement req = con.prepareStatement(
                        "update berthingrecord set Status=? where IMO=?");
                req.setInt(1, record.getStatus());
                req.setInt(2, record.getImoNum());
                int nbLines = req.executeUpdate();
                if (nbLines != 1) {
                    throw new RecordException("Failed insertion!");
                }
                return null;
            }
        });
    }

    static interface RunJDBC <T>{
        T run(Connection con) throws Exception;
    }

    private <T> T withDB(RunJDBC<T> runner) {
        Connection con = null;
        T result = null;
        try {
            con = dataSource.getConnection();
            boolean auto = con.getAutoCommit();
            con.setAutoCommit(false);
            result = runner.run(con);
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
    private class RecordException extends Exception {
        /**
         *
         */
        private static final long serialVersionUID = 7418836201378164160L;

        public RecordException( String mess) {
            super( mess) ;
        }
    }


    public List<PreArrivalForm> getPafs(){
        return withDB(new RunJDBC<List<PreArrivalForm>>() {
            @Override
            public List<PreArrivalForm> run(Connection con) throws Exception {
                List<PreArrivalForm> pafList = new ArrayList<PreArrivalForm>();
                Statement s = con.createStatement();
                final String sql="select * from prearrival where FormValidation=0;";
                ResultSet rs = s.executeQuery(sql);
                while(rs.next()){
                    PreArrivalForm paf = new PreArrivalForm();
                    paf.setsName(rs.getString("Ship Name"));
                    paf.setCallSign(rs.getString("Call Sign"));
                    paf.setImoNum(rs.getInt("IMO Num"));
                    paf.setAgentInfo(rs.getString("Agent Information"));
                    paf.setaForm(rs.getString("Arriving From"));
                    paf.setEta(rs.getInt("Estimated Time of Arrival (ETA)"));
                    paf.setbNum(rs.getInt("Berth Number"));
                    paf.setNextPort(rs.getString("Next Port"));
                    paf.setEtd(rs.getInt("Estimated Time of Departure (ETD)"));
                    paf.setDiscargoD(rs.getString("Discharging Cargo Description"));
                    paf.setDisCargoA(rs.getInt("Discharging Cargo Amount"));
                    paf.setLoadCargoD(rs.getString("Loading Cargo Description"));
                    paf.setLoadCargoA(rs.getInt("Loading Cargo Amount"));
                    paf.setnPassArr(rs.getInt("Number of Passengers on Arrival"));
                    paf.setnPassDep(rs.getInt("Number of Passengers on Departure"));
                    paf.setFormVal(rs.getInt("Form Validation"));

                    pafList.add(paf);
                }
                return pafList;
            }
        });
    }
}
