package com.floow.app;

import com.floow.app.ds.Trie;
import org.junit.Test;

import java.util.TreeMap;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TrieNodeTest {


    @Test
    public void givenATrie_whenAddingElements_thenTrieHasThoseElements() {
        Trie trie = createExampleTrie();

        assertFalse(trie.containsNode("3"));
        assertFalse(trie.containsNode("vida"));

        assertTrue(trie.containsNode("Programming"));
        assertTrue(trie.containsNode("is"));
        assertTrue(trie.containsNode("a"));
        assertTrue(trie.containsNode("way"));
        assertTrue(trie.containsNode("of"));
        assertTrue(trie.containsNode("life"));
        System.out.println(trie.getCountOfWords("Programming"));
        System.out.println(trie.getCountOfWords("of"));
        System.out.println(trie.getCountOfWords("is"));



    }

    private Trie createExampleTrie() {
        Trie trie = new Trie(new TreeMap<>());

        trie.insert("Programming");
        trie.insert("is");
        trie.insert("a");
        trie.insert("way");
        trie.insert("of");
        trie.insert("Programming");
        trie.insert("is");
        trie.insert("is");
        trie.insert("life");

        return trie;
    }
}
