package com.veltrix.main;

import com.veltrix.dao.ProductoDAO;
import com.veltrix.model.Producto;

public class Main {

    public static void main(String[] args) {

        Producto producto = new Producto();

        producto.setNombre("Nike Air Max");
        producto.setDescripcion("Zapatos deportivos");
        producto.setTalla(42);
        producto.setColor("Negro");
        producto.setPrecio(350000);
        producto.setStock(10);
        producto.setIdCategoria(1);

        ProductoDAO dao = new ProductoDAO();

        dao.insertarProducto(producto);
    }
}