package com.veltrix.main;

import com.veltrix.dao.ClienteDAO;
import com.veltrix.model.Cliente;

public class Main {

    public static void main(String[] args) {

        Cliente cliente = new Cliente();

        // ID del cliente existente
        cliente.setIdCliente(1);

        cliente.setNombre("Samuel Eduardo");
        cliente.setApellido("Ortega Diaz");
        cliente.setCorreo("samuelortega@gmail.com");
        cliente.setTelefono("3119876543");
        cliente.setDireccion("Cali, Colombia");
        cliente.setContrasena("nueva123");

        ClienteDAO dao = new ClienteDAO();

        dao.actualizarCliente(cliente);
    }
}