package dao;

import beans.PreArrivalForm;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomsDAO {

    @Resource( lookup="jdbc/MyPortAuthorityDB")
    private DataSource dataSource;

    static interface RunJDBC <T>{
        T run(Connection con) throws Exception;
    }

    private <T> T withDB(CustomsDAO.RunJDBC<T> runner) {
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
            try { con.rollback(); } catch( SQLException ex) {
                ex.printStackTrace();
            }
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

    public void approveForm(int imo, boolean status) {
        withDB((RunJDBC<PreArrivalForm>) con -> {
            PreparedStatement req = con.prepareStatement(
                    "update prearrival set `FormValidation`=? where IMO=?");
            req.setInt(1, status? 1 : 0);
            req.setInt(2, imo);
            int nbLines = req.executeUpdate();
            if (nbLines != 1) {
                throw new RecordException("Failed insertion!");
            }
            return null;
        });
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
