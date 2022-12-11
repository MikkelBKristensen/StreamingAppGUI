package com.example.streamingappgui;


import domain.Media;

import domain.*;
import exceptions.FileNotLoadedException;
import exceptions.MediaNotInArrayException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private MediaCollection primaryMediaList;
    private MediaCollection activeMediaList;
    private ProfileCollection profileList;

    public HelloController() {
        try {
            primaryMediaList = new MediaList();
            activeMediaList = new MediaList();
            profileList = new ProfileList();
        } catch (FileNotLoadedException e) {
            throw new RuntimeException(e);
        }
    }

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
    EventHandler<ActionEvent> sortByComboHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {

            switch(sortByComboBox.getValue()) {
                case "Default" -> {
                    activeMediaList.getMedia().clear();
                    activeMediaList.getMedia().addAll(primaryMediaList.getMedia());
                    redrawMediaPane(mediaPane);
                }
                case "Favorites" -> {
                    activeMediaList = primaryMediaList.getCollectionByName(profileList.getActiveProfile().getFavorites());
                    redrawMediaPane(mediaPane);
                }
                case "Alphabetical (A-Z)" -> {
                    activeMediaList.sortByAlphabetical();
                    redrawMediaPane(mediaPane);
                }
                case "Alphabetical (Z-A)" -> {
                    activeMediaList.sortByAlphabeticalReverse();
                    redrawMediaPane(mediaPane);
                }
                case "Rating (Highest first)" -> {
                    activeMediaList.sortByRating();
                    redrawMediaPane(mediaPane);
                }
                case "Rating (Lowest first)" -> {
                    activeMediaList.sortByRatingReverse();
                    redrawMediaPane(mediaPane);
                }
                case "Release year (Newest first)" -> {
                    activeMediaList.sortByReleaseYear();
                    redrawMediaPane(mediaPane);
                }
                case "Release year (Oldest first)" -> {
                    activeMediaList.sortByReleaseYearReverse();
                    redrawMediaPane(mediaPane);
                }
            }
        }
    };
    EventHandler<ActionEvent> genreComboBoxHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {

            switch(genreComboBox.getValue()) {
                case "All genres" -> {
                    activeMediaList.getMedia().clear();
                    activeMediaList.getMedia().addAll(primaryMediaList.getMedia());
                    redrawMediaPane(mediaPane);
                }
                default -> {
                    activeMediaList = primaryMediaList.getCollectionByGenre(genreComboBox.getValue());
                    redrawMediaPane(mediaPane);
                }
            }
        }
    };

    @FXML
    public void search(ActionEvent event) {
        /*searchedName = searchTextField.getText();
        for (Media media : mediaCollection) {
            currentMediaCollection.add(media);
        }
        for (Media media : currentMediaCollection) {
        }*/
    }

    @FXML
    private FlowPane mediaPane;


    // Making lists for comboBoxes
    private final String[] sortByOptions = {"Default","Favorites", "Alphabetical (A-Z)","Alphabetical (Z-A)",
            "Rating (Highest first)", "Rating (Lowest first)", "Release year (Newest first)", "Release year (Oldest first)"};
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

        //Set Active profile
        profileList.setActiveProfile(1);

        //Add event handlers.
        sortByComboBox.addEventFilter(ActionEvent.ACTION, sortByComboHandler);
        genreComboBox.addEventFilter(ActionEvent.ACTION, genreComboBoxHandler);

        //Draw the mediaPane to fill up with most recent mediaList;
        redrawMediaPane(mediaPane);
    }

    //Used to draw the mediapane on initialization, or whenever the mediaList has been altered in any way.
    public void redrawMediaPane(FlowPane mediaPane) {

        mediaPane.getChildren().clear();

        for(Media media : activeMediaList.getMedia()) {
            mediaPane.getChildren().add(mediaCard(media));
        }
    }

    //Used to generate the cards containing media.
    public Group mediaCard(Media media) {

        Group mediaCard = new Group();
        ImageView mediaPosterWrapper = new ImageView(new Image(media.getPosterURL()));
        // Text mediaTitle = new Text(media.getTitle());

        mediaCard.getChildren().add(mediaPosterWrapper);
        // mediaCard.getChildren().add(mediaTitle);

        return mediaCard;
    }
}