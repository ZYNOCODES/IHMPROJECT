package com.example.ihmproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VenteDB extends Vente {
    static Connection connection = DataBase.getConnection();
    static PreparedStatement statement = null;
    static ResultSet resultSet = null;

    //getting vente from DB
    public static ObservableList<Vente> getVente(){
        ObservableList<Vente> Vente = FXCollections.observableArrayList();

        String query = "select * from Vente";
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int med_ID = resultSet.getInt("med_ID");
                int VenteNumber = resultSet.getInt("VenteNumber");
                int Med_Quantity = resultSet.getInt("med_Quantity");
                int Med_Price = resultSet.getInt("med_Price");
                int med_Total = resultSet.getInt("med_TotalPrice");
                String Med_Name = resultSet.getString("med_Name");
                Vente vente = new Vente(med_ID,VenteNumber,Med_Quantity,Med_Name,med_Total,Med_Price);
                Vente.add(vente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Vente;
    }
    //add a vente to DB
    public static void ADDVente(Integer VenteNumber, String MedName, Integer MedQuantity, Integer MedPrice, Integer MedTotalPrice){
        String query = "insert into Vente(med_Name, med_Price, med_Quantity, med_TotalPrice, VenteNumber) values(?, ?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, MedName);
            statement.setInt(2, MedPrice);
            statement.setInt(3, MedQuantity);
            statement.setInt(4, MedTotalPrice);
            statement.setInt(5, VenteNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //update vente at the expense of an id
    public static void updateVente(int Med_ID, int MedQuantity, int TotalPrice){

        String query = "update Vente set med_Quantity = ? ,med_TotalPrice = ? where med_ID = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, MedQuantity);
            statement.setInt(2, TotalPrice);
            statement.setInt(3, Med_ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //remove vente at the expense of an id
    public static void RemoveVente(int Med_Id){
        String query = "delete from Vente where med_ID = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, Med_Id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public int getVenteNumber(){
//        int number;
//        String query = "select * from Vente ORDER BY VenteNumber DESC LIMIT 1";
//
//        try {
//            statement = connection.prepareStatement(query);
//            resultSet = statement.executeQuery();
//            number = resultSet.getInt("VenteNumber");
//            return number;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
