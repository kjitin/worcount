package com.floow.app.ds;

import java.util.TreeMap;

/**
 * Trie used to store words
 */

public class Trie {

    private TrieNode root;

    private TreeMap<String, Integer> topWords;

    public Trie(TreeMap<String, Integer> topWords){
        root = new TrieNode();
        this.topWords = topWords;
    }

    public void insert(String word) {

        TrieNode current = root;

        for (int i =0; i< word.length(); i++) {

            current = current.getChildren().computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }
        current.setEndOfWord(true);
        current.setKey(word);

        topWords.put(word, current.getCount());
    }

    public TreeMap<String, Integer> getTopWords() {
        return topWords;
    }

    public int getCountOfWords(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return -1;
            }
            current = node;
        }
        return current.getCount();
    }




    public boolean containsNode(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

    boolean isEmpty() {
        return root == null;
    }


}