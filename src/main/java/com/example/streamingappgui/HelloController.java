package com.example.streamingappgui;

import domain.Media;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button logoButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private ComboBox<String> sortByComboBox;
    @FXML
    private ComboBox<String> profileComboBox;
    @FXML
    private ComboBox<String> genreComboBox;
    @FXML
    private ComboBox<String> mediaComboBox;


    String searchedName;

    // ActionEvents
    @FXML
    public void update
    @FXML
    public void sortedBy(ActionEvent event) {
    }
    @FXML
    public void search(ActionEvent event) {
        /*searchedName = searchTextField.getText();
        for (Media media : mediaCollection) {
            currentMediaCollection.add(media);
        }
        for (Media media : currentMediaCollection) {
        }*/
    }



    // Making lists for comboBoxes
    private final String[] sortByOptions = {"Default","Favorites", "Alphabetical (A-Z)","Alphabetical (Z-A)",
            "Rating (Highest first", "Rating (Lowest first", "Release year (Newest first)", "Release year (Oldest first)"};
    private final String[] profileOptions = {"Save profile", "Change profile" };
    private final String[] genres = {"All genres", "Action", "Adventure", "Biography", "Crime", "Horror"};
    private final String[] mediaTypes = {"All media", "Movies", "Series"};



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Adding lists to comboBoxes
        sortByComboBox.getItems().addAll(sortByOptions);
        genreComboBox.getItems().addAll(genres);
        mediaComboBox.getItems().addAll(mediaTypes);
        profileComboBox.getItems().addAll(profileOptions);


    }


}