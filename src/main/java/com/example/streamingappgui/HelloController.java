package com.example.streamingappgui;

import domain.Media;
import domain.MediaCollection;
import domain.MediaList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane flowPane;
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
    List<Media> mediaList;
    MediaCollection mediaCollection;
    MediaCollection currentMediaCollection;



    // ActionEvents

    @FXML
    public void updateMediaCards() {
    }
    @FXML
    public void sortedBy(ActionEvent event) {
    }
    @FXML
    public void search(MouseEvent event) {
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