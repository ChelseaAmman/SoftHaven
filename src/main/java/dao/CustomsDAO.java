package dao;

import beans.PreArrivalForm;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
