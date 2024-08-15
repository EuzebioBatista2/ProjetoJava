import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ConnectSQL {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/qrwatchdb";
        String user = "root";
        String password = "";

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();

            String sql = "SELECT * FROM users";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");

                System.out.println("Name: " + name);
                System.out.println("Lastname: " + lastname);
                System.out.println("Email: " + email);
                System.out.println();
            }

            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}