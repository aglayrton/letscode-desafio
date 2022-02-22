package com.cardgame.enu;

public enum Gender {

    COMEDY("https://www.imdb.com/search/title/?genres=comedy"),
    SCI_FI("https://www.imdb.com/search/title/?genres=sci-fi"),
    HORROR("https://www.imdb.com/search/title/?genres=horror"),
    ACTION("https://www.imdb.com/search/title/?genres=action"),
    FANTASY("https://www.imdb.com/search/title/?genres=fantasy");

    private final String url;

    Gender(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
