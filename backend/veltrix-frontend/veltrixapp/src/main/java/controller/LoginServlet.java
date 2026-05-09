package controller;

// IMPORTACIONES

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// RUTA DEL SERVLET

@WebServlet("/login")


// CLASE PRINCIPAL

public class LoginServlet extends HttpServlet {

    
    // MÉTODO POST
    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        
        // OBTENER DATOS DEL FORMULARIO
        
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");

        
        // VALIDACIÓN SIMPLE
        
        if(correo.equals("admin@gmail.com")
                && clave.equals("1234")) {

            
            // SI EL LOGIN ES CORRECTO
            
            response.sendRedirect("productos.jsp");

        } else {

            
            // SI EL LOGIN ES INCORRECTO
            
            response.sendRedirect("login.jsp");

        }

    }

}