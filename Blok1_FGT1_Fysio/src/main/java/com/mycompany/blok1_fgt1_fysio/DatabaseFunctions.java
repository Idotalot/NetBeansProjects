/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.mycompany.blok1_fgt1_fysio;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import javafx.scene.control.ListView;
//
///**
// *
// * @author jordy
// */
//public class DatabaseFunctions {
//    public Connection con;
//    
//    public void select(String table, ListView<String> listView) {
//            
//            try {
//                con = DBCPDataSource.getConnection();
//                Statement stat = con.createStatement();
//                ResultSet result = stat.executeQuery("SELECT * FROM" + table);
//            
//            while(result.next()) {
////                String strStad = result.getString("stad");
////                listView.getItems().add(strStad);
//                  result.toString();
//                  listView.getItems().add(result);
//            }
//        } catch (SQLException se) {
//            System.out.println(se.getMessage());
//        } finally {
//            try {
//                con.close();
//            } catch(SQLException se) {
//                System.out.println(se.getMessage());
//            }
//        }
//    }
//}
