package com.veltrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.veltrix.connection.Conexion;
import com.veltrix.model.Pago;

public class PagoDAO {

    // INSERT
    public void insertarPago(Pago pago) {

        String sql = "INSERT INTO Pago(id_pedido, fecha_pago, monto, metodo_pago, estado_pago) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, pago.getIdPedido());

            statement.setObject(2, pago.getFechaPago());

            statement.setInt(3, pago.getMonto());

            statement.setString(4, pago.getMetodoPago());

            statement.setString(5, pago.getEstado());

            statement.executeUpdate();

            System.out.println("Pago insertado correctamente");

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al insertar pago");
            e.printStackTrace();
        }
    }

    // READ
    public void listarPagos() {

        String sql = "SELECT * FROM Pago";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {

                System.out.println("ID Pago: " + resultado.getInt("id_pago"));

                System.out.println("ID Pedido: " + resultado.getInt("id_pedido"));

                System.out.println("Fecha Pago: " + resultado.getObject("fecha_pago"));

                System.out.println("Monto: " + resultado.getInt("monto"));

                System.out.println("Metodo Pago: " + resultado.getString("metodo_pago"));

                System.out.println("Estado Pago: " + resultado.getString("estado_pago"));

                System.out.println("--------------------------------");
            }

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al listar pagos");
            e.printStackTrace();
        }
    }

    // UPDATE
    public void actualizarPago(Pago pago) {

        String sql = "UPDATE Pago SET id_pedido = ?, fecha_pago = ?, monto = ?, metodo_pago = ?, estado_pago = ? WHERE id_pago = ?";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, pago.getIdPedido());

            statement.setObject(2, pago.getFechaPago());

            statement.setInt(3, pago.getMonto());

            statement.setString(4, pago.getMetodoPago());

            statement.setString(5, pago.getEstado());

            statement.setInt(6, pago.getIdPago());

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {

                System.out.println("Pago actualizado correctamente");

            } else {

                System.out.println("No se encontró el pago");
            }

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al actualizar pago");
            e.printStackTrace();
        }
    }

    // DELETE
    public void eliminarPago(int idPago) {

        String sql = "DELETE FROM Pago WHERE id_pago = ?";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, idPago);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {

                System.out.println("Pago eliminado correctamente");

            } else {

                System.out.println("No se encontró el pago");
            }

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al eliminar pago");
            e.printStackTrace();
        }
    }
}