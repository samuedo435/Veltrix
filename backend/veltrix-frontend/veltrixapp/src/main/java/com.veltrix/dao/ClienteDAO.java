package com.veltrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.veltrix.connection.Conexion;
import com.veltrix.model.Cliente;
//CRUD
public class ClienteDAO {
//CREATE
    public void insertarCliente(Cliente cliente) {

        String sql = "INSERT INTO Cliente(nombre, apellido, correo, telefono, direccion, contrasena) VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getCorreo());
            statement.setString(4, cliente.getTelefono());
            statement.setString(5, cliente.getDireccion());
            statement.setString(6, cliente.getContrasena());

            statement.executeUpdate();

            System.out.println("Cliente insertado correctamente");

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al insertar cliente");
            e.printStackTrace();
        }
    }
//READ
public void listarClientes() {

    String sql = "SELECT * FROM Cliente";

    try {

        Connection conexion = Conexion.conectar();

        PreparedStatement statement = conexion.prepareStatement(sql);

        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {

            System.out.println("ID: " + resultado.getInt("id_cliente"));
            System.out.println("Nombre: " + resultado.getString("nombre"));
            System.out.println("Apellido: " + resultado.getString("apellido"));
            System.out.println("Correo: " + resultado.getString("correo"));
            System.out.println("Telefono: " + resultado.getString("telefono"));
            System.out.println("Direccion: " + resultado.getString("direccion"));

            System.out.println("-----------------------------");
        }

        conexion.close();

    } catch (Exception e) {

        System.out.println("Error al listar clientes");
        e.printStackTrace();
    }
    }
//UPDATE
public void actualizarCliente(Cliente cliente) {

    String sql = "UPDATE Cliente SET nombre = ?, apellido = ?, correo = ?, telefono = ?, direccion = ?, contrasena = ? WHERE id_cliente = ?";

    try {

        Connection conexion = Conexion.conectar();

        PreparedStatement statement = conexion.prepareStatement(sql);

        statement.setString(1, cliente.getNombre());
        statement.setString(2, cliente.getApellido());
        statement.setString(3, cliente.getCorreo());
        statement.setString(4, cliente.getTelefono());
        statement.setString(5, cliente.getDireccion());
        statement.setString(6, cliente.getContrasena());

        // ID del cliente a actualizar
        statement.setInt(7, cliente.getIdCliente());

        int filasActualizadas = statement.executeUpdate();

        if (filasActualizadas > 0) {

            System.out.println("Cliente actualizado correctamente");

        } else {

            System.out.println("No se encontró el cliente");
        }

        conexion.close();

    } catch (Exception e) {

        System.out.println("Error al actualizar cliente");
        e.printStackTrace();
    }
    }
//DELETE
public void eliminarCliente(int idCliente) {

    String sql = "DELETE FROM Cliente WHERE id_cliente = ?";

    try {

        Connection conexion = Conexion.conectar();

        PreparedStatement statement = conexion.prepareStatement(sql);

        statement.setInt(1, idCliente);

        int filasEliminadas = statement.executeUpdate();

        if (filasEliminadas > 0) {

            System.out.println("Cliente eliminado correctamente");

        } else {

            System.out.println("No se encontró el cliente");
        }

        conexion.close();

    } catch (Exception e) {

        System.out.println("Error al eliminar cliente");
        e.printStackTrace();
    }
    }
}