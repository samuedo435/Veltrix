package com.veltrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.veltrix.connection.Conexion;
import com.veltrix.model.Resenas;
//CRUD
public class ResenasDAO {
//CREATE
    public void insertarResena(Resenas resena) {

        String sql = "INSERT INTO Resenas(id_producto, id_cliente, calificacion, comentario, fecha) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, resena.getIdProducto());
            statement.setInt(2, resena.getIdCliente());
            statement.setInt(3, resena.getCalificacion());
            statement.setString(4, resena.getComentario());
            statement.setObject(5, resena.getFechaResena());
            statement.executeUpdate();

            System.out.println("Reseña insertada correctamente");

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al insertar reseña");
            e.printStackTrace();
        }
    }
//READ
    public void listarResenas() {
    String sql = "SELECT * FROM Resenas";
    try {

        Connection conexion = Conexion.conectar();

        PreparedStatement statement = conexion.prepareStatement(sql);

        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {

            System.out.println("ID Reseña: " + resultado.getInt("id_resena"));
            System.out.println("ID Producto: " + resultado.getInt("id_producto"));
            System.out.println("ID Cliente: " + resultado.getInt("id_cliente"));
            System.out.println("Calificación: " + resultado.getInt("calificacion"));
            System.out.println("Comentario: " + resultado.getString("comentario"));
            System.out.println("Fecha Reseña: " + resultado.getObject("fecha"));
        }

        conexion.close();
    } catch (Exception e) {
        System.out.println("Error al listar reseñas");
        e.printStackTrace();
    }
}
//UPDATE
    public void actualizarResena(Resenas resena) {
        String sql = "UPDATE Resenas SET id_producto = ?, id_cliente = ?, calificacion = ?, comentario = ?, fecha = ? WHERE id_resena = ?";
        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, resena.getIdProducto());
            statement.setInt(2, resena.getIdCliente());
            statement.setInt(3, resena.getCalificacion());
            statement.setString(4, resena.getComentario());
            statement.setObject(5, resena.getFechaResena());
            //ID Resena a actualizar
            statement.setInt(6, resena.getIdResena());

        int filasActualizadas = statement.executeUpdate();

        if (filasActualizadas > 0) {

            System.out.println("Reseña actualizada correctamente");

        } else {

            System.out.println("No se encontró la reseña");
        }
            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al actualizar reseña");
            e.printStackTrace();
        }
    }
//DELETE
    public void eliminarResena(int idResena) {

        String sql = "DELETE FROM Resenas WHERE id_resena = ?";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, idResena);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {

                System.out.println("Reseña eliminada correctamente");

            } else {

                System.out.println("No se encontró la reseña con ID: " + idResena);
            }

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al eliminar reseña");
            e.printStackTrace();
        }
    }
}