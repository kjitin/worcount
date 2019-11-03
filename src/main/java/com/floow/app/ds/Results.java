package com.floow.app.ds;

/**
 * Class used for storing results from Mongodb
 */

public class Results implements Comparable<Results>{

    private String wordId;

    private Double count;


    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    @Override
    public int compareTo(Results o) {
        double compareCount = ((Results) o).getCount();
        return (int)Math.round(this.getCount() - compareCount);
    }
}
