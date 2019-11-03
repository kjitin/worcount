package com.floow.app;

import com.floow.app.db.MongoMapperService;
import com.floow.app.ds.Trie;

import java.util.*;

public class WordSplitter {



    public void splitWords(List<String> lines, String mongoUrl) {

        TreeMap<String, Integer> topWords = new TreeMap<>();

        Trie trie = new Trie(topWords);
        for (String line: lines) {

            String[] strings = line.split(" ");
            List<String> stringList = Arrays.asList(strings);
            for (String word: stringList) {
                trie.insert(word);
            }
        }
        MongoMapperService mapperService = new MongoMapperService();

        SortedSet<Map.Entry<String, Integer>> entries = entriesSortedByValues(topWords);
        mapperService.saveMappedDataToDB(entries, mongoUrl);
    }

    static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                (e1, e2) -> {
                    int res = e2.getValue().compareTo(e1.getValue());
                    return res != 0 ? res : 1;
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}
