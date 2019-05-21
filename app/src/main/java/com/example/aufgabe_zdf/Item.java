package com.example.aufgabe_zdf;

public class Item {
    private String mImageUrl;
    private String mHeadline;
    private String mTitel;
    private String mBeschreibung;

    // item with title and image
    public Item(String imageUrl, String headline, String titel, String beschreibung) {
        this.setImageUrl(imageUrl);
        this.setHeadline(headline);
        this.setTitel(titel);
        this.setBeschreibung(beschreibung);
    }

    // item only with title
    public Item(String headline, String titel, String beschreibung) {
        this.setImageUrl("");
        this.setHeadline(headline);
        this.setTitel(titel);
        this.setBeschreibung(beschreibung);
    }

    // item only with image
    public Item(String imageUrl) {
        this.setImageUrl(imageUrl);
        this.setHeadline("");
        this.setTitel("");
        this.setBeschreibung("");
    }

    private void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    private void setHeadline(String mHeadline) {
        this.mHeadline = mHeadline;
    }

    private void setTitel(String mTitel) {
        this.mTitel = mTitel;
    }

    private void setBeschreibung(String mBeschreibung) {
        this.mBeschreibung = mBeschreibung;
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
