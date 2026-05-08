package com.veltrix.main;

import com.veltrix.dao.ProductoDAO;

public class Main {

    public static void main(String[] args) {

        ProductoDAO dao = new ProductoDAO();

        dao.eliminarProducto(1);
    }
}