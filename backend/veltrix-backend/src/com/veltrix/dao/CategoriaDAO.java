package com.veltrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.veltrix.connection.Conexion;
import com.veltrix.model.Categoria;
//CRUD
public class CategoriaDAO {
//CREATE 
    public void insertarCategoria(Categoria categoria) {

        String sql = "INSERT INTO Categoria(nombre_categoria, descripcion) VALUES (?, ?)";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, categoria.getNombreCategoria());
            statement.setString(2, categoria.getDescripcion());

            statement.executeUpdate();

            System.out.println("Categoria insertada correctamente");

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al insertar categoria");
            e.printStackTrace();
        }
    }
//READ
    public void listarCategorias() {
        String sql = "SELECT * FROM Categoria";

        try {
            Connection conexion = Conexion.conectar();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id_categoria"));
                System.out.println("Nombre: " + resultSet.getString("nombre_categoria"));
                System.out.println("Descripción: " + resultSet.getString("descripcion"));
                System.out.println("------------------------");
            }

            conexion.close();
        } catch (Exception e) {
            System.out.println("Error al listar categorias");
            e.printStackTrace();
        }
    }
    //UPDATE
    public void actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE Categoria SET nombre_categoria = ?, descripcion = ? WHERE id_categoria = ?";

        try {
            Connection conexion = Conexion.conectar();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, categoria.getNombreCategoria());
            statement.setString(2, categoria.getDescripcion());

            //ID de la categoria a actualizar
            statement.setInt(3, categoria.getIdCategoria());

        int filasActualizadas = statement.executeUpdate();

        if (filasActualizadas > 0) {

            System.out.println("Categoria actualizada correctamente");

        } else {

            System.out.println("No se encontró la categoria");
        }

            conexion.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar categoria");
            e.printStackTrace();
        }
    }
    //DELETE
    public void eliminarCategoria(int idCategoria) {
        String sql = "DELETE FROM Categoria WHERE id_categoria = ?";

        try {
            Connection conexion = Conexion.conectar();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, idCategoria);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {

                System.out.println("Categoria eliminada correctamente");

            } else {

                System.out.println("No se encontró la categoria");
            }

            conexion.close();
        } catch (Exception e) {
            System.out.println("Error al eliminar categoria");
            e.printStackTrace();
        }
    }
}