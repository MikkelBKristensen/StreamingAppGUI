package domain;

import exceptions.FileNotLoadedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MediaCollectionTest {

    private MediaCollection testCollection;

    @BeforeEach
    void setUp() {
        try {
            testCollection = new MediaList();
        } catch (FileNotLoadedException e) {
            fail(e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        testCollection = null;
    }

    @Test
    void getCollectionByNameReturnsCorrectMedia() {

        List<String> mediaToFetch = new ArrayList<>(Arrays.asList("Dances With Wolves", "The Green Mile", "Yankee Doodle Dandy", "Rocky"));
        MediaCollection returnedCollection = testCollection.getCollectionByName(mediaToFetch);
        List<String> returnedMedia = new ArrayList<>();

        returnedCollection.getMedia().forEach((media) -> returnedMedia.add(media.getTitle()));

        for(String mediaName : mediaToFetch) {
            assert(returnedMedia.contains(mediaName));
        }
    }

    //TODO write search function test

    @Test
    void sortByRating() {
        List<String> mediaToFetch = new ArrayList<>(Arrays.asList("Game of Thrones", "Crisis in six scenes", "Batman", "Terms of endearment"));
        MediaCollection collectionToSort = testCollection.getCollectionByName(mediaToFetch);
        collectionToSort.sortByRating();
        assertEquals (collectionToSort.getMedia().get(0).getTitle(), "Game Of Thrones");
        assertEquals (collectionToSort.getMedia().get(1).getTitle(), "Batman");
        assertEquals (collectionToSort.getMedia().get(2).getTitle(), "Terms of Endearment");
        assertEquals (collectionToSort.getMedia().get(3).getTitle(), "Crisis In Six Scenes");
    }

    @Test
    void sortByReverseRating() {
        List<String> mediaToFetch = new ArrayList<>(Arrays.asList("Game of Thrones", "Crisis in six scenes", "Batman", "Terms of endearment"));
        MediaCollection collectionToSort = testCollection.getCollectionByName(mediaToFetch);
        collectionToSort.sortByRatingReverse();
        assertEquals (collectionToSort.getMedia().get(3).getTitle(), "Game Of Thrones");
        assertEquals (collectionToSort.getMedia().get(2).getTitle(), "Batman");
        assertEquals (collectionToSort.getMedia().get(1).getTitle(), "Terms of Endearment");
        assertEquals (collectionToSort.getMedia().get(0).getTitle(), "Crisis In Six Scenes");
    }

    @Test
    void sortByReleaseYear() {

        List<String> mediaToFetch = new ArrayList<>(Arrays.asList("The Green Mile", "Dances With Wolves","Yankee Doodle Dandy", "Rocky"));
        List<String> expectedList = new ArrayList<>(Arrays.asList("Yankee Doodle Dandy", "Rocky", "Dances With Wolves", "The Green Mile"));
        List<String> comparisonList = new ArrayList<>();
        MediaCollection chosenMedia = testCollection.getCollectionByName(mediaToFetch);

        chosenMedia.sortByReleaseYear();

        chosenMedia.getMedia().forEach((media) -> comparisonList.add(media.getTitle()));

        assertEquals(comparisonList, expectedList);
    }

    @Test
    void sortByReleaseYearReversed() {

        List<String> mediaToFetch = new ArrayList<>(Arrays.asList("The Green Mile", "Dances With Wolves","Yankee Doodle Dandy", "Rocky"));
        List<String> expectedList = new ArrayList<>(Arrays.asList("The Green Mile", "Dances With Wolves", "Rocky", "Yankee Doodle Dandy"));
        List<String> comparisonList = new ArrayList<>();
        MediaCollection chosenMedia = testCollection.getCollectionByName(mediaToFetch);

        chosenMedia.sortByReleaseYearReverse();

        chosenMedia.getMedia().forEach((media) -> comparisonList.add(media.getTitle()));

        assertEquals(comparisonList, expectedList);
    }

    @Test
    void sortByAlphabetical() {
        List<String> mediaToFetch = new ArrayList<>(Arrays.asList("The Green Mile", "Crisis In Six Scenes", "Batman", "Terms of Endearment"));
        MediaCollection collectionToSort = testCollection.getCollectionByName(mediaToFetch);
        collectionToSort.sortByAlphabetical();
        assertEquals ("Batman", collectionToSort.getMedia().get(0).getTitle());
        assertEquals ("Crisis In Six Scenes", collectionToSort.getMedia().get(1).getTitle());
        assertEquals ("Terms of Endearment", collectionToSort.getMedia().get(2).getTitle());
        assertEquals ("The Green Mile", collectionToSort.getMedia().get(3).getTitle());

    }

    @Test
    void sortByReverseAlphabetical() {
        List<String> mediaToFetch = new ArrayList<>(Arrays.asList("The Green Mile", "Crisis In Six Scenes", "Batman", "Terms of Endearment"));
        MediaCollection collectionToSort = testCollection.getCollectionByName(mediaToFetch);
        collectionToSort.sortByAlphabeticalReverse();
        assertEquals (collectionToSort.getMedia().get(3).getTitle(), "Batman");
        assertEquals (collectionToSort.getMedia().get(2).getTitle(), "Crisis In Six Scenes");
        assertEquals (collectionToSort.getMedia().get(1).getTitle(), "Terms of Endearment");
        assertEquals (collectionToSort.getMedia().get(0).getTitle(), "The Green Mile");

    }
}