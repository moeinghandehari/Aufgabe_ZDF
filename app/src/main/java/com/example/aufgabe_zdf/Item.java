package com.example.aufgabe_zdf;

public class Item {
    private String mImageUrl;
    private String mHeadline;
    private String mTitel;
    private String mBeschreibung;

    // A constructor for test without image
    public Item(String headline, String titel, String beschreibung) {
        mHeadline = headline;
        mTitel = titel;
        mBeschreibung = beschreibung;
    }

    public Item(String imageUrl, String headline, String titel, String beschreibung) {
        mImageUrl = imageUrl;
        mHeadline = headline;
        mTitel = titel;
        mBeschreibung = beschreibung;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public String getTitel() {
        return mTitel;
    }

    public String getBeschreibung() {
        return mBeschreibung;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
