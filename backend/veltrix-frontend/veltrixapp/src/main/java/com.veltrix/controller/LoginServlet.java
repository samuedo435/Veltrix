package com.veltrix.controller;

// IMPORTACIONES

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.veltrix.dao.ClienteDAO;
import com.veltrix.model.Cliente;

// RUTA DEL SERVLET

@WebServlet("/login")


// CLASE PRINCIPAL

public class LoginServlet extends HttpServlet {

    
    // MÉTODO POST
    
    @Override
protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    
    // DATOS DEL FORMULARIO
    
    String correo = request.getParameter("correo");
    String contrasena = request.getParameter("clave");

    
    // OBJETO DAO
    
    ClienteDAO clienteDAO = new ClienteDAO();

    
    // VALIDAR LOGIN
    
    Cliente cliente = clienteDAO.validarLogin(correo, contrasena);

    
    // SI EL CLIENTE EXISTE
    
    if(cliente != null) {

        response.sendRedirect("productos.jsp");

    } else {

        response.sendRedirect("login.jsp");

    }
}

}