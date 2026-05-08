package com.veltrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.veltrix.connection.Conexion;
import com.veltrix.model.Producto;
//CRUD
public class ProductoDAO {
//insertar producto
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
//consultar productos
    public void listarProductos() {

    String sql = "SELECT * FROM Producto";

    try {

        Connection conexion = Conexion.conectar();

        PreparedStatement statement = conexion.prepareStatement(sql);

        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {

            System.out.println("ID: " + resultado.getInt("id_producto"));
            System.out.println("Nombre: " + resultado.getString("nombre"));
            System.out.println("Precio: " + resultado.getInt("precio"));
            System.out.println("Stock: " + resultado.getInt("stock"));
            System.out.println("----------------------------");
        }

        conexion.close();

    } catch (Exception e) {

        System.out.println("Error al listar productos");
        e.printStackTrace();
    }
}
//actualizar producto
public void actualizarProducto(Producto producto) {

    String sql = "UPDATE Producto SET nombre = ?, descripcion = ?, talla = ?, color = ?, precio = ?, stock = ?, id_categoria = ? WHERE id_producto = ?";

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

        // ID del producto a actualizar
        statement.setInt(8, producto.getIdProducto());

        int filasActualizadas = statement.executeUpdate();

        if (filasActualizadas > 0) {

            System.out.println("Producto actualizado correctamente");

        } else {

            System.out.println("No se encontró el producto");
        }

        conexion.close();

    } catch (Exception e) {

        System.out.println("Error al actualizar producto");
        e.printStackTrace();
    }
}
//eliminar producto
public void eliminarProducto(int idProducto) {

    String sql = "DELETE FROM Producto WHERE id_producto = ?";

    try {

        Connection conexion = Conexion.conectar();

        PreparedStatement statement = conexion.prepareStatement(sql);

        statement.setInt(1, idProducto);

        int filasEliminadas = statement.executeUpdate();

        if (filasEliminadas > 0) {

            System.out.println("Producto eliminado correctamente");

        } else {

            System.out.println("No se encontró el producto");
        }

        conexion.close();

    } catch (Exception e) {

        System.out.println("Error al eliminar producto");
        e.printStackTrace();
    }
}
}   