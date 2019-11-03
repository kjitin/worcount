package com.floow.app.ds;


import java.util.HashMap;
import java.util.Map;

public class TrieNode implements Comparable<TrieNode>{


    private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean endOfWord;
    private int count;

    private String key;


    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean isEndOfWord() {
        return endOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {

        this.endOfWord = endOfWord;
        setCount();
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public int getCount() {
        return count;
    }

    public void setCount() {
        synchronized (this) {
            this.count++;
        }
    }




    @Override
    public int compareTo(TrieNode o) {
        return Integer.compare(o.count, this.count);
    }
}
