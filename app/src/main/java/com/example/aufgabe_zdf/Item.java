package com.example.aufgabe_zdf;

public class Item {
    private String mImageUrl;
    private String mHeadline;
    private String mTitel;
    private String mBeschreibung;

    // A constructor for test without image
    public Item(String headline, String titel, String beschreibung) {
        this.setHeadline(headline);
        this.setTitel(titel);
        this.setBeschreibung(beschreibung);
    }

    public Item(String imageUrl, String headline, String titel, String beschreibung) {
        this.setImageUrl(imageUrl);
        this.setHeadline(headline);
        this.setTitel(titel);
        this.setBeschreibung(beschreibung);
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
