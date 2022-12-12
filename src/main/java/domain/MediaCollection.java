package domain;

import exceptions.FileNotLoadedException;
import exceptions.MediaNotInArrayException;

import java.io.IOException;
import java.util.List;

public interface MediaCollection {
    MediaCollection getCollectionByGenre(String genre);

    MediaCollection getCollectionByName(List<String> chosenMedia);

    MediaCollection getCollectionByType(String mediaType) throws FileNotLoadedException;
    Media getMediaByName(String chosenMedia) throws MediaNotInArrayException;
    public MediaCollection getCollectionByName(String chosenMedia) throws MediaNotInArrayException;
    void sortByRating();

    void sortByRatingReverse();

    void sortByReleaseYear();

    void sortByReleaseYearReverse();

    void sortByAlphabetical();

    void sortByAlphabeticalReverse();

    List<Media> getMedia();

    String getGenre();
}
