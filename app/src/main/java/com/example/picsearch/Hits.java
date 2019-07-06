package com.example.picsearch;

public class Hits {
    private String largeImageURL;
    private String previewURL;
    private  String webformatURL;
    private int downloads;
    private  int likes;

    public Hits(String largeImageURL, String previewURL, String webformatURL, int downloads, int likes) {
        this.largeImageURL = largeImageURL;
        this.previewURL = previewURL;
        this.webformatURL = webformatURL;
        this.downloads = downloads;
        this.likes = likes;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
