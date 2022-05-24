/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Luisp
 */
public class Conexion {

//    String bd = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
//    String usuario = "root";
//    String contrasenia = "";
    String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";

    Connection con;

    String host = "localhost";
    String port = "3306";
    String db = "iResident";
    String user = "root";
    String pass = "";
    String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public Conexion() {
        Conectar();
    }

    /**/
    /**
     *
     * @param URL
     */
    public Conexion(String URL) {

        this.url = URL;
        Conectar();
    }

    public void Conectar() {
        try {
            Class.forName(DATABASE_DRIVER);
            System.out.println("Encontro Driver");
            try {
                con = (Connection) conectar();
                System.out.println("SE establecio conexion con la BD");
//                Statement st= con.createStatement();
//                String sql="select from persona";
//                ResultSet rs=st.executeQuery(sql);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("No se pudo establecer conexion con la BD");
                System.exit(0);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro Driver \n" + e);
        }
    }

    public ResultSet querie(String querie) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            String sql = querie;
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Error en querie: " + e);
        }
//        JOptionPane.showMessageDialog(null, "");
        return rs;

    }

    public int DML(String querie) throws SQLException {
        int rs = 0;

        Statement st = con.createStatement();
        String sql = querie;
        rs = st.executeUpdate(sql);
//            st.

//        JOptionPane.showMessageDialog(null, "");
        return rs;
    }

}
