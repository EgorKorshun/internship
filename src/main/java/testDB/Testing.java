package testDB;

import domain.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class Testing {
    static final String USER = "prog724";
    static final String PASS = "";
    static final String URL = "jdbc:informix-sqli://10.247.12.31:1525/oper:INFORMIXSERVER=ids_delta_1;DB_LOCALE=ru_RU.866;CLIENT_LOCALE=ru_RU.utf8;DBDATE=Y4MD-";
    static final String DRIVER = "com.informix.jdbc.IfxDriver";
    public static final String io ="io";
    public static final String surname ="sirname";
    public static final String operPhone ="oper_phone";



    public List<Employee> test1() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        Connection c = getConnection(URL, USER, PASS);
        Statement stmt = null;
        ResultSet rs = null;

        List<Employee> result = new ArrayList<>();

        try {
            c.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            /*для БД.  ___четыре уровня изолированность___

            * Isol Levels

            * ACID
            атомарность(либо оба, либо ничего),консистентность(постоянное выполнение чего-либо),
            изолированность(невозможность изменения для других потоков), дюрабилити (гарантированность исхода)

           */

            c.setAutoCommit(false);           // не для БД.
            stmt = c.createStatement();
            rs = stmt.executeQuery("select sirname, io,oper_phone from ratsg:a15 where oper_user = USER");

            while (rs.next()) {
                String surname = rs.getString("sirname");
                String io = rs.getString("io");
                String operPhone = rs.getString("oper_number");

                System.out.println(io +
                        " " + surname +
                        "phone number is: " + operPhone);

                Employee employee = new Employee(USER);
                employee.setSurname(surname);
                employee.setIo(io);
                employee.setOperNumber(operPhone);
                result.add(employee);

            }
            c.commit();
//            return result;
        } catch (SQLException e) {
c.rollback();

        } finally {
            if(rs !=null)
            rs.close();
            if(stmt!=null)
            stmt.close();
            if (c != null) {
                c.close();
            }
        }
        return result;
    }

}
