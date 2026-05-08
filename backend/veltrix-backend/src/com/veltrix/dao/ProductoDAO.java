package com.veltrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.veltrix.connection.Conexion;
import com.veltrix.model.Producto;

public class ProductoDAO {

    public void insertarProducto(Producto producto) {

        String sql = "INSERT INTO Producto(nombre, descripcion, talla, color, precio, stock, id_categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setInt(3, producto.getTalla());
            statement.setString(4, producto.getColor());
            statement.setInt(5, producto.getPrecio());
            statement.setInt(6, producto.getStock());
            statement.setInt(7, producto.getIdCategoria());

            statement.executeUpdate();

            System.out.println("Producto insertado correctamente");

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al insertar producto");
            e.printStackTrace();
        }
    }
}