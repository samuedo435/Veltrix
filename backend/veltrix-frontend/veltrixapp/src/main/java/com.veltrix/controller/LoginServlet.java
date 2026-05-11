package com.veltrix.controller;

import com.veltrix.dao.ClienteDAO;
import com.veltrix.model.Cliente;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {

@Override
protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

    String accion =
            request.getParameter("accion");

    ClienteDAO clienteDAO =
            new ClienteDAO();

    // ====================================
    // REGISTRO
    // ====================================
    if (accion.equals("registro")) {

        String nombre =
                request.getParameter("nombre");

        String apellido =
                request.getParameter("apellido");

        String telefono =
                request.getParameter("telefono");

        String direccion =
                request.getParameter("direccion");

        String correo =
                request.getParameter("correo");

        String clave =
                request.getParameter("clave");

        Cliente nuevoCliente =
                new Cliente();

        nuevoCliente.setNombre(nombre);
        nuevoCliente.setApellido(apellido);
        nuevoCliente.setTelefono(telefono);
        nuevoCliente.setDireccion(direccion);
        nuevoCliente.setCorreo(correo);

        // IMPORTANTE
        nuevoCliente.setContrasena(clave);

        // INSERTAR EN BD
        clienteDAO.insertarCliente(nuevoCliente);

        response.sendRedirect("login.jsp?registro=ok");

        return;
    }

    // ====================================
    // LOGIN
    // ====================================

    String correo =
            request.getParameter("correo");

    String clave =
            request.getParameter("clave");

    Cliente cliente =
            clienteDAO.validarLogin(correo, clave);

    if (cliente != null) {

        HttpSession sesion =
                request.getSession();

        sesion.setAttribute("cliente", cliente);

        response.sendRedirect("products.jsp");

    } else {

        response.getWriter().println(
                "Correo o contraseña incorrectos"
        );
    }
}
}