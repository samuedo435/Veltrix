package com.veltrix.main;

import com.veltrix.dao.ClienteDAO;
import com.veltrix.model.Cliente;

public class Main {

    public static void main(String[] args) {

        Cliente cliente = new Cliente();

        cliente.setNombre("Samuel");
        cliente.setApellido("Ortega");
        cliente.setCorreo("samuel@gmail.com");
        cliente.setTelefono("3001234567");
        cliente.setDireccion("Cali");
        cliente.setContrasena("123456");

        ClienteDAO dao = new ClienteDAO();

        dao.insertarCliente(cliente);
    }
}