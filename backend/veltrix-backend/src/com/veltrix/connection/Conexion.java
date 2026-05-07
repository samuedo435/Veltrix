package com.veltrix.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/veltrix";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para conectar
    public static Connection conectar() {

        Connection conexion = null;

        try {

            // Establecer conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Conexión exitosa a la base de datos");

        } catch (SQLException e) {

            System.out.println("Error en la conexión");
            e.printStackTrace();
        }

        return conexion;
    }
}