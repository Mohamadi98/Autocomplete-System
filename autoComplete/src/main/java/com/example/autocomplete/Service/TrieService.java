package com.example.autocomplete.Service;

import Data.PrefixTrie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TrieService {

    private PrefixTrie prefixTrie;


    @Autowired
    public TrieService() {
        this.prefixTrie = new PrefixTrie();
    }

    @Autowired
    private redisService redis;

    public void insertPrefix(String prefix) {
        prefixTrie.insertPrefix(prefix);
    }

    public List<String> getResults(String prefix) {
        List<String> TrieResults;
        Set<String> results = redis.retrieveFromCache(prefix);
        if (results.size() == 0) {
            TrieResults = prefixTrie.searchResults(prefix);
            if(TrieResults.size() > 0){
                for(String result: TrieResults){
                    redis.insertToCache(prefix, result);
                }
            }
            return TrieResults;
        }
        return new ArrayList<>(results);
    }


}

