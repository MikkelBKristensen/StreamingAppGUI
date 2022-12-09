package com.example.streamingappgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ImageView appLogo;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private ComboBox<String> sortByComboBox;
    private final String[] sortByOptions = {"All genres", "Favorites", "Rating (Highest first", "Rating (Lowest first",
            "Release year (Newest first)", "Release year (Oldest first)"};

    String searchedName;

    //TODO Add rest of the genres
    @FXML
    private ComboBox<String> genreComboBox;
    private final String[] genres = {"All genres", "Action", "Adventure", "Biography", "Crime", "Horror"};


    @FXML
    public void search(ActionEvent event) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sortByComboBox.getItems().addAll(sortByOptions);
        genreComboBox.getItems().addAll(genres);

    }

    public void sortedBy(ActionEvent event) {
        throw new UnsupportedOperationException();
    }
}