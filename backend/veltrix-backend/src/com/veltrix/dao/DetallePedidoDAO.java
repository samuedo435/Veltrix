package com.veltrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.veltrix.connection.Conexion;
import com.veltrix.model.DetallePedido;
//CRUD
public class DetallePedidoDAO {
//CREATE
    public void insertarDetallePedido(DetallePedido detalle) {

        String sql = "INSERT INTO DetallePedido(id_pedido, id_producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, detalle.getIdPedido());
            statement.setInt(2, detalle.getIdProducto());
            statement.setInt(3, detalle.getCantidad());
            statement.setInt(4, detalle.getSubtotal());

            statement.executeUpdate();

            System.out.println("Detalle de pedido insertado correctamente");

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al insertar detalle de pedido");
            e.printStackTrace();
        }
    }
//READ
    public void listarDetallePedidos() {
    String sql = "SELECT * FROM DetallePedido";
    try {

        Connection conexion = Conexion.conectar();

        PreparedStatement statement = conexion.prepareStatement(sql);

        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {

            System.out.println("ID Detalle: " + resultado.getInt("id_detalle"));
            System.out.println("ID Pedido: " + resultado.getInt("id_pedido"));
            System.out.println("ID Producto: " + resultado.getInt("id_producto"));
            System.out.println("Cantidad: " + resultado.getInt("cantidad"));
            System.out.println("Subtotal: " + resultado.getInt("subtotal"));
        }

        conexion.close();
    } catch (Exception e) {

        System.out.println("Error al listar detalles de pedido");
        e.printStackTrace();
    }
}
//UPDATE
    public void actualizarDetallePedido(DetallePedido detalle) {

        String sql = "UPDATE DetallePedido SET id_pedido = ?, id_producto = ?, cantidad = ?, subtotal = ? WHERE id_detalle = ?";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, detalle.getIdPedido());
            statement.setInt(2, detalle.getIdProducto());
            statement.setInt(3, detalle.getCantidad());
            statement.setInt(4, detalle.getSubtotal());
            //ID del detalle a actualizar
            statement.setInt(5, detalle.getIdDetalle());

            statement.executeUpdate();

            System.out.println("Detalle de pedido actualizado correctamente");

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al actualizar detalle de pedido");
            e.printStackTrace();
        }
    }
//DELETE
    public void eliminarDetallePedido(int idDetalle) {

        String sql = "DELETE FROM DetallePedido WHERE id_detalle = ?";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, idDetalle);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Detalle de pedido eliminado correctamente");
            } else {
                System.out.println("No se encontró el detalle de pedido con ID: " + idDetalle);
            }

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al eliminar detalle de pedido");
            e.printStackTrace();
        }
    }
}