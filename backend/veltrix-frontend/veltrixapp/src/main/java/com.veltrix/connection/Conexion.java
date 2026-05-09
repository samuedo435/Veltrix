package com.veltrix.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/veltrix";

    private static final String USER = "root";

    private static final String PASSWORD =
            "$Amuelchocolate777";

    public static Connection conectar() {

        Connection conexion = null;

        try {

            
            // CARGAR DRIVER
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            // CONECTAR
            
            conexion = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Conexión exitosa");

        } catch (ClassNotFoundException e) {

            System.out.println("Driver no encontrado");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Error de conexión");
            e.printStackTrace();
        }

        return conexion;
    }
}