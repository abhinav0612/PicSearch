package com.example.picsearch;

import java.util.ArrayList;

public class Pixabay {
    private String total;
    private String totalHits;
    private ArrayList<Hits> hits;

    public Pixabay(String total, String totalHits, ArrayList<Hits> hits) {
        this.total = total;
        this.totalHits = totalHits;
        this.hits = hits;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(String totalHits) {
        this.totalHits = totalHits;
    }

    public ArrayList<Hits> getHits() {
        return hits;
    }

    public void setHits(ArrayList<Hits> hits) {
        this.hits = hits;
    }
}
