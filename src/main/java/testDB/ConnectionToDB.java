package testDB;

import com.informix.asf.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static testDB.Testing.*;

public abstract class ConnectionToDB {

    private Connection getConnection() throws SQLException {
        String URL = "jdbc:informix-sqli://10.247.12.31:1525/oper:INFORMIXSERVER=ids_delta_1;DB_LOCALE=ru_RU.866;CLIENT_LOCALE=ru_RU.utf8;DBDATE=Y4MD-";
        Properties properties = new Properties();
        properties.setProperty("user","pass");
        Object s =  properties.setProperty("prog724","");
        return (Connection) DriverManager.getConnection(URL,"prog724","");
}

    public static final java.sql.Connection databaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Properties properties = new Properties();
        properties.get(URL);

        return  DriverManager.getConnection(URL, USER, PASS);
    }
}
