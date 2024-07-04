package br.qziul.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection(
            String host,
            String porta,
            String nomeBanco,
            String usuario,
            String senha
    ) throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://"+host+":"+porta+"/"+nomeBanco,
                usuario,
                senha
        );
    }
}
