package com.example.picsearch;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class Pixabay {
    private String total;
    private String totalHits;
    private List<Hits> hits;

    public Pixabay(String total, String totalHits, List<Hits> hits) {
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

    public List<Hits> getHits() {
        return hits;
    }

    public void setHits(List<Hits> hits) {
        this.hits = hits;
    }
}
