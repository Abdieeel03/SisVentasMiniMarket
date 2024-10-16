package com.grupo1.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    protected Connection conexion;
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost/mkwilson";
    private final String USER = "root";
    private final String PASS = "";
    
    public void conectar() throws ClassNotFoundException{
        try {
            conexion = DriverManager.getConnection(DB_URL,USER,PASS);
            Class.forName(JDBC_DRIVER);
            System.out.println("Conectado correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrar() throws SQLException{
        if (conexion!=null){
            if(!conexion.isClosed()){
                conexion.close();
            }
        }
    }
}
