package com.veltrix.main;

import com.veltrix.dao.ProductoDAO;
import com.veltrix.model.Producto;

public class Main {

    public static void main(String[] args) {

        Producto producto = new Producto();

        // ID del producto que ya existe
        producto.setIdProducto(1);

        producto.setNombre("Nike Air Max Plus");
        producto.setDescripcion("Zapatos deportivos actualizados");
        producto.setTalla(43);
        producto.setColor("Blanco");
        producto.setPrecio(420000);
        producto.setStock(15);
        producto.setIdCategoria(1);

        ProductoDAO dao = new ProductoDAO();

        dao.actualizarProducto(producto);
    }
}