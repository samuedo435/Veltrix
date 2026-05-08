package com.veltrix.main;

import com.veltrix.dao.ClienteDAO;

public class Main {

    public static void main(String[] args) {

        ClienteDAO dao = new ClienteDAO();

        dao.listarClientes();
    }
}