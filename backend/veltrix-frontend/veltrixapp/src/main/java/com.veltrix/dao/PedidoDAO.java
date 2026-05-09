package com.veltrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.veltrix.connection.Conexion;
import com.veltrix.model.Pedido;
//CRUD
public class PedidoDAO {
//CREATE
    public void insertarPedido(Pedido pedido) {

        String sql = "INSERT INTO Pedido(id_cliente, fecha_pedido, estado, monto_total) VALUES (?, ?, ?, ?)";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, pedido.getIdCliente());
            statement.setObject(2, pedido.getFechaPedido());
            statement.setString(3, pedido.getEstado());
            statement.setInt(4, pedido.getTotal());

            statement.executeUpdate();

            System.out.println("Pedido insertado correctamente");

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al insertar pedido");
            e.printStackTrace();
        }
    }
//READ
    public void listarPedidos() {
    String sql = "SELECT * FROM Pedido";
    try {

        Connection conexion = Conexion.conectar();

        PreparedStatement statement = conexion.prepareStatement(sql);

        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {

            System.out.println("ID: " + resultado.getInt("id_pedido"));
            System.out.println("ID Cliente: " + resultado.getInt("id_cliente"));
            System.out.println("Fecha Pedido: " + resultado.getObject("fecha_pedido"));
            System.out.println("Estado: " + resultado.getString("estado"));
            System.out.println("Total: " + resultado.getInt("monto_total"));
        }

        conexion.close();

    } catch (Exception e) {

        System.out.println("Error al listar pedidos");
        e.printStackTrace();
    }
    }
//UPDATE
    public void actualizarEstadoPedido(int idPedido, String nuevoEstado) {

        String sql = "UPDATE Pedido SET estado = ? WHERE id_pedido = ?";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, nuevoEstado);
            statement.setInt(2, idPedido);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Estado del pedido actualizado correctamente");
            } else {
                System.out.println("No se encontró el pedido con ID: " + idPedido);
            }

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al actualizar estado del pedido");
            e.printStackTrace();
        }
    }
//DELETE
    public void eliminarPedido(int idPedido) {

        String sql = "DELETE FROM Pedido WHERE id_pedido = ?";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, idPedido);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Pedido eliminado correctamente");
            } else {
                System.out.println("No se encontró el pedido con ID: " + idPedido);
            }

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al eliminar pedido");
            e.printStackTrace();
        }
    }
}