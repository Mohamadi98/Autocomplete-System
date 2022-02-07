package Data;


import java.util.*;

public class PrefixTrie {
    public static class TrieNode {
        protected Map<Character, TrieNode> children;
        protected Boolean endOfWord;
        protected String phrase;

        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
            phrase = "";
        }
    }

    private TrieNode root;

    public PrefixTrie() {
        root = new TrieNode();
    }

    private Boolean lastNode(TrieNode node) {
        if (node.children.size() == 0) {
            return true;
        }
        return false;
    }

    public void insertPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        insertPrefix(prefix, root, 0);

    }

    private void insertPrefix(String prefix, TrieNode root, int index) {
        if (prefix.length() == index) {
            root.endOfWord = true;
            root.phrase = prefix;
            return;
        }
        char currChar = prefix.charAt(index);
        TrieNode currNode = root.children.get(currChar);

        if (currNode == null) {
            currNode = new TrieNode();
            root.children.put(currChar, currNode);
        }
        insertPrefix(prefix, currNode, index + 1);
    }


    //finding all possible search results
    public List<String> searchResults(String prefix) {
        List<String> Results = new ArrayList<>();
        TrieNode sequenceLastNode = getSearchSequenceNode(prefix);
        if (sequenceLastNode != null) {
            return searchResults(sequenceLastNode, Results);
        }
        return Collections.emptyList();
    }

    private List<String> searchResults(TrieNode root, List<String> Results) {
        if (root.endOfWord) {
            Results.add(root.phrase);
            if (lastNode(root)) {
                return Results;
            }
        }
        for (char key : root.children.keySet()) {
            searchResults(root.children.get(key), Results);
        }
        return Results;
    }


    // finding the node at the end of the search prefix
    private TrieNode getSearchSequenceNode(String sequence) {
        sequence = sequence.toLowerCase();
        return getSearchSequenceNode(sequence, root, 0);
    }

    private TrieNode getSearchSequenceNode(String sequence, TrieNode root, int index) {
        if (sequence.length() == 0 || sequence.length() == index) {
            return root;
        }
        char currchar = sequence.charAt(index);
        TrieNode currNode = root.children.get(currchar);
        if (currNode == null) {
            return null;
        }
        return getSearchSequenceNode(sequence, currNode, index + 1);
    }
}
