<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.veltrix.model.Cliente"%>
<%

Cliente clienteSesion =
        (Cliente) session.getAttribute("cliente");

if(clienteSesion != null){

    out.println("Bienvenido " + clienteSesion.getNombre());

}else{

    out.println("No hay sesión iniciada");
}

%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Veltrix | Iniciar sesión</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/styles.css">
  <style>

.mensaje-exito{

    position: fixed;

    top: 20px;
    right: 20px;

    background: #111;
    color: white;

    padding: 14px 20px;

    border-radius: 10px;

    font-family: 'Inter', sans-serif;

    z-index: 9999;

    animation: aparecer 0.3s ease;
}

@keyframes aparecer{

    from{

        opacity: 0;
        transform: translateY(-10px);
    }

    to{

        opacity: 1;
        transform: translateY(0);
    }
}

</style>
</head>
<body>
  <header class="site-header">
    <div class="container header-inner">
      <a href="index.jsp" class="brand">Veltrix</a>
      <nav class="main-nav" aria-label="Main navigation">
        <a href="index.jsp">Inicio</a>
        <a href="products.jsp">Tienda</a>
        
        <a href="cart.jsp">Carrito</a>
        <a href="login.jsp" class="button button--ghost">Iniciar sesión</a>
      </nav>
      <button class="nav-toggle" aria-label="Toggle navigation">
        <span></span>
        <span></span>
        <span></span>
      </button>
    </div>
  </header>

  <main>
      <%

String registro =
        request.getParameter("registro");

if("ok".equals(registro)){

%>

<div id="mensajeExito" class="mensaje-exito">
    Usuario creado con éxito
</div>

<%

}

%>
    <section class="section-block auth-section">
      <div class="container auth-grid">
        <div class="auth-panel auth-intro">
          <span class="eyebrow">Bienvenido de nuevo</span>
          <h1>Accede a tu cuenta Veltrix</h1>
          <p>Guarda favoritos, gestiona pedidos y paga más rápido. Únete al movimiento con calzado deportivo de alto rendimiento.</p>
          <div class="feature-list">
            <p>• Lanzamientos exclusivos</p>
            <p>• Pago rápido</p>
            <p>• Seguimiento de pedidos</p>
          </div>
        </div>

<div class="auth-panel auth-form">

  <div class="form-switch">

    <button type="button"
            class="tab active"
            id="btnLoginTab">

      Iniciar sesión

    </button>

    <button type="button"
            class="tab"
            id="btnRegistroTab">

      Registrarse

    </button>

  </div>

  <!-- FORMULARIO LOGIN -->
  <form id="formLogin"
        action="${pageContext.request.contextPath}/login"
        method="post">

    <input type="hidden"
           name="accion"
           value="login">

    <label>
      <span>Correo electrónico</span>
      <input type="email"
             name="correo"
             placeholder="nombre@dominio.com">
    </label>

    <label>
      <span>Contraseña</span>
      <input type="password"
             name="clave"
             placeholder="Ingresa tu contraseña">
    </label>

    <button type="submit"
            class="button button--primary button--full">

      Iniciar sesión

    </button>

  </form>

  <!-- FORMULARIO REGISTRO -->
  <form id="formRegistro"
        action="${pageContext.request.contextPath}/login"
        method="post"
        style="display:none;">

    <input type="hidden"
           name="accion"
           value="registro">

    <label>
      <span>Nombre</span>
      <input type="text"
             name="nombre"
             placeholder="Ingresa tu nombre">
    </label>

    <label>
      <span>Apellido</span>
      <input type="text"
             name="apellido"
             placeholder="Ingresa tu apellido">
    </label>

    <label>
      <span>Teléfono</span>
      <input type="text"
             name="telefono"
             placeholder="Ingresa tu teléfono">
    </label>

    <label>
      <span>Dirección</span>
      <input type="text"
             name="direccion"
             placeholder="Ingresa tu dirección">
    </label>

    <label>
      <span>Correo electrónico</span>
      <input type="email"
             name="correo"
             placeholder="nombre@dominio.com">
    </label>

    <label>
      <span>Contraseña</span>
      <input type="password"
             name="clave"
             placeholder="Crea una contraseña">
    </label>

    <button type="submit"
            class="button button--primary button--full">

      Registrarse

    </button>

  </form>

</div>
        </div>
      </div>
    </section>
  </main>

  <footer class="site-footer footer-compact">
    <div class="container footer-grid">
      <div>
        <h3>Veltrix</h3>
        <p>Performance footwear designed for athletes.</p>
      </div>
      <div>
        <h4>Contact</h4>
        <p>hello@veltrixbrand.com</p>
      </div>
    </div>
  </footer>

  <script src="js/script.js"></script>
  <script>

const btnLoginTab =
    document.getElementById("btnLoginTab");

const btnRegistroTab =
    document.getElementById("btnRegistroTab");

const formLogin =
    document.getElementById("formLogin");

const formRegistro =
    document.getElementById("formRegistro");

btnRegistroTab.addEventListener("click", () => {

    formLogin.style.display = "none";

    formRegistro.style.display = "block";

    btnRegistroTab.classList.add("active");

    btnLoginTab.classList.remove("active");

});

btnLoginTab.addEventListener("click", () => {

    formRegistro.style.display = "none";

    formLogin.style.display = "block";

    btnLoginTab.classList.add("active");

    btnRegistroTab.classList.remove("active");

});

</script>
<script>

setTimeout(() => {

    const mensaje =
        document.getElementById("mensajeExito");

    if(mensaje){

        mensaje.style.display = "none";
    }

}, 3000);

</script>
</body>
</html>

