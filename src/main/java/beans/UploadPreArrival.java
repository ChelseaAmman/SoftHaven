package beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.*;

@SessionScoped
@Named("uploadPreArrival")
public class UploadPreArrival implements Serializable {

    private static final long serialVersionUID = 6081417964063918994L;

    public void uploadPreArrival() throws SQLException {
        try {
        String url = "jdbc:mysql://localhost:3306/MyPortAuthorityDB";
        String username = "root";
        String password = "pa$$";
        Class.forName("com.mysql.cj.jdbc.MysqlDataSource");
        Connection connect = DriverManager.getConnection(url, username, password);

        ShipMaster ship = new ShipMaster();

        System.out.println("hello");
        PreparedStatement statement = connect.prepareStatement("INSERT INTO prearrival (ShipName) values (?)");
        statement.setString(1, ship.getsName());
        ResultSet rs = statement.executeQuery();

        // close resources
        rs.close();
        connect.close();
        } catch (
                SQLException | ClassNotFoundException ex) {
            System.out.println("in exec");
            System.out.println(ex.getMessage());
        }
    }

}
