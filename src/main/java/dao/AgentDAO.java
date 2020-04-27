package dao;

import beans.BerthRecord;

import java.math.BigDecimal;
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

    public void add( final int id, final String title, final String desc, final String author, final String price) {

        withDB( new RunJDBC<BerthRecord>() {
            public BerthRecord run( Connection con) throws Exception {
                PreparedStatement req = con.prepareStatement(
                        "insert into Book (id, title, description, " +
                                "author, price) values (?,?,?,?,?)" );
                req.setInt(1,  id);
                req.setString(2,  title);
                req.setString(3, desc);
                req.setString(4, author);

                try {
                    req.setBigDecimal(5, new BigDecimal(price));
                } catch ( NumberFormatException e) {
                    req.setBigDecimal(5,  new BigDecimal("0.0"));
                }
                int nbLines = req.executeUpdate();
                if (nbLines!=1) {
                    throw new RecordException("Failed insertion!");
                }
                return null;
            }
        });
    }

    public void modify( final int id, final String title, final String desc, final String author, final String price) {

        System.out.println("in Modify()");

        withDB( new RunJDBC<BerthRecord>() {
            public BerthRecord run( Connection con) throws Exception {

                PreparedStatement req = con.prepareStatement(
                        "update Book set title=?, description=?, " +
                                "author=?, price=? where id=?");
                req.setString(1,  title);
                req.setString(2,  desc);
                req.setString(3, author);

                try {
                    req.setBigDecimal(4, new BigDecimal(price));
                } catch ( NumberFormatException e) {
                    req.setBigDecimal(4,  new BigDecimal("0.0"));
                }
                req.setInt(5,  id);
                int nbLines = req.executeUpdate();
                System.out.println("in Modify()");
                if (nbLines!=1) {
                    System.out.println("Exception during modify");
                    throw new RecordException("Failed update!");
                }
                return null;
            }
        });
    }


    public void delete( final int id ) {
        withDB( new RunJDBC<BerthRecord> () {
            public BerthRecord run( Connection con) throws Exception {
                PreparedStatement req = con.prepareStatement(
                        "delete from Book where id = ?"
                );
                req.setInt(1, id);
                int nbLines = req.executeUpdate();
                if (nbLines != 1){
                    throw new RecordException("Deletion failed");
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


    private List<BerthRecord> list(final String sortkey ){
        return withDB( new RunJDBC<List<BerthRecord>>() {
            public List<BerthRecord> run (Connection con) throws Exception {
                List<BerthRecord> list = new ArrayList<BerthRecord>();
                Statement stt = con.createStatement();
                final String req = "select * from Book" +
                        (sortkey!=null? " ORDER BY " + sortkey + ";" : ";");
                ResultSet rs = stt.executeQuery(req);
                while (rs.next()) {
                    BerthRecord record = new BerthRecord();
                    list.add( record );
                }

                return list;
            }
        });
    }
}
