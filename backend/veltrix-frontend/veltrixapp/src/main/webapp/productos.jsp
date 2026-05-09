<%@ page import="com.veltrix.model.Cliente" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Productos Veltrix</title>
</head>

<body>
<%

    Cliente cliente =
        (Cliente) session.getAttribute("cliente");

    if(cliente == null){

        response.sendRedirect("login.jsp");
        return;
    }

%>

    <h1>Bienvenido <%= cliente.getNombre() %></h1>

    <h2>Login exitoso</h2>

</body>
</html>