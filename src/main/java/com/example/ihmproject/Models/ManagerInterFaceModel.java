package com.example.ihmproject.Models;

import com.example.ihmproject.ManagerInterFace;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class ManagerInterFaceModel {
    public void loadScene(BorderPane UserContainer, String sc, Button BTN) throws IOException {
        Parent root = FXMLLoader.load(ManagerInterFace.class.getResource(sc));
        UserContainer.setCenter(root);
        ButtonSelected(BTN);
    }
    private void ButtonSelected(Button BTN){

    }
}
