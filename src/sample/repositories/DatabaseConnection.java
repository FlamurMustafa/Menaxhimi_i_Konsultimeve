package sample.repositories;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection dbLink;

    public Connection getConn(){
        String dbName = "konsultimet_test";
        String dbuser = "root";
        String dbpass = "1234";

        String url = "jdbc:mysql://localhost/" + dbName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbLink = DriverManager.getConnection(url,dbuser,dbpass);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dbLink;
    }
}
