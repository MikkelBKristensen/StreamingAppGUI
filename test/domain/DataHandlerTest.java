package domain;

import data.FileHandler;
import data.FileHandlerImpl;
import exceptions.FileNotLoadedException;
import exceptions.FileNotSavedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DataHandlerTest {

    private DataHandler dataHandler;
    private FileHandler fileHandler;
    private File profileIds;

    @BeforeEach
    void setUp() {
        dataHandler = DataHandler.getInstance();
        fileHandler = new FileHandlerImpl();
        profileIds = new File("src/lib/profiles/profileIds.txt");
    }

    @AfterEach
    void tearDown() {
        dataHandler = null;
        fileHandler = null;
        profileIds = null;
    }

    @Test
    void assembleMediaListContainsAllMedia() {

        List<Media> mediaList = null;

        try {
            mediaList = dataHandler.assembleMediaList();
        } catch(FileNotLoadedException e) {
            fail(e.getMessage());
        }

        assert(mediaList.size() == 200);

    }

    @Test
    void assembleMovieListContainsAllMovies() {

        List<Media> movieList = null;

        try {
            movieList = dataHandler.assembleMovieList();
        } catch(FileNotLoadedException e) {
            fail(e.getMessage());
        }

        assert(movieList.size() == 100);

    }

    @Test
    void assembleSeriesListContainsAllSeries() {

        List<Media> seriesList = null;

        try {
            seriesList = dataHandler.assembleSeriesList();
        } catch(FileNotLoadedException e) {
            fail(e.getMessage());
        }

        assert(seriesList.size() == 100);

    }

    @Test
    void assembleProfileMapConatinsAllProfiles() {

        Map<Integer, Profile> profileMap = null;
        int profilesAmount = -1;

        try {
            profilesAmount = fileHandler.loadFile(new File("lib/profiles/profileIds.txt")).size();
            profileMap = dataHandler.assembleProfileMap();
        } catch (FileNotLoadedException e) {
            fail(e.getMessage());
        }

        assert(profileMap.keySet().size() == profilesAmount );

    }

    @Test
    void saveProfileSavesAndIsLoadable() {

        int testId = 1026245;
        String profileName = stringGenerator(10);
        List<String> testFavorites = new ArrayList<>();
        List<String> loadedFavorites = null;
        List<String> loadedProfileData = null;

        for (int i = 0; i < 50; i++) {
            testFavorites.add(stringGenerator(15));
        }

        Profile testProfile = new Profile(testId, profileName, testFavorites);

        try {
            dataHandler.saveProfile(testProfile);
            loadedProfileData = fileHandler.loadFile(new File("lib/profiles/1026245.txt"));
        } catch (FileNotSavedException e) {
            fail(e.getMessage());
        } catch (FileNotLoadedException e) {
            fail(e.getMessage());
        }

        loadedFavorites = loadedProfileData.subList(2, loadedProfileData.size());

        assertEquals(Integer.parseInt(loadedProfileData.get(0)), testId);
        assertEquals(loadedProfileData.get(1), profileName);
        assert(loadedFavorites.equals(testFavorites));
    }

    @Test
    void saveProfileMapSavesAndIsLoadable() {

        Map<Integer, Profile> profileMap = null;
        Map<Integer, Profile> comparatorProfileMap = null;
        List<String> addedProfilefavourites = new ArrayList<>();
        Profile addedProfile= new Profile((int) (100000*Math.random()), stringGenerator(10), addedProfilefavourites);

        for (int i = 0; i < 10; i++) {
            addedProfilefavourites.add(stringGenerator(15));
        }

        try {
            profileMap = dataHandler.assembleProfileMap();
        } catch(FileNotLoadedException e) {
            fail(e.getMessage());
        }

        profileMap.merge(addedProfile.getId(), addedProfile, (a,b) -> b);

        try {
            dataHandler.saveProfileMap(profileMap);
            dataHandler.saveProfile(addedProfile);
            comparatorProfileMap = dataHandler.assembleProfileMap();
        } catch (FileNotSavedException e) {
            fail(e.getMessage());
        } catch (FileNotLoadedException e) {
            fail(e.getMessage());
        }

        for(int profileId : comparatorProfileMap.keySet()) {
            assert(profileMap.containsKey(profileId));
        }
    }

    private String stringGenerator(int size) {

        String randomString = "";
        String lowercaseAlphabet = "abcdefghijklmn";

        for (int i = 0; i < size; i++) {
            int index = (int)(lowercaseAlphabet.length() * Math.random());
            randomString += lowercaseAlphabet.charAt(index);
        }

        return randomString;

    }
}