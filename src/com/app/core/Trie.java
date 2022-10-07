package com.app.core;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Trie implements TrieIntf, Serializable {

	private TrieNode root;

	public Trie() {
		root = new TrieNode('\0');
	}

	// Method to insert string
	@Override
	public boolean insert(String word) {
		// Setting current node for traversing
		TrieNode current = root;
		// If no word has been entered
		if (word == "")
			return false;
		word = word.toLowerCase();
		// If the word already exist
		if (search(word) == true)
			return false;
		// Check character by character and insert in node if they are absent
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			// Checking if the nodes doesn't contain the character
			TrieNode newNode = new TrieNode(c);
			if (current.children[index] == null)
				current.children[index] = newNode;
			// If yes
			current = current.children[index];
		}
		// Setting the last character as true
		current.isEndChar = true;
		return true;
	}

	// Method to search String
	@Override
	public boolean search(String word) {
		// Setting current node for traversing
		TrieNode checkNode = getLastNodeStatus(word);
		return checkNode != null && checkNode.isEndChar;
	}

	private TrieNode getLastNodeStatus(String word) {
		// Setting current node for traversing
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (current.children[index] == null)
				return null;
			current = current.children[index];
		}
		// returns the last node
		return current;
	}

	// Method to delete string from trie
	@Override
	public boolean delete(String word) {
		TrieNode trav = root;
		
		// If the trie is empty
		if (root == null)
			return false;
		
		// If the word doesn't exist in the trie
		if (search(word) == false)
			return false;
		
		int width = 0;
		delete(trav, word, width);
		return true;
	}

	// Recursive function to delete a key from given Trie
	private TrieNode delete(TrieNode trav, String word, int width) {
		// If last character of key is being processed
		if (width == word.length()) {

			// Change the word's last node's isEndChar status to false
			if (trav.isEndChar) {
				trav.isEndChar = false;
			}

			// If the children node of the last node is empty
			if (isEmpty(trav)) {
				trav = null;
			}
			return trav;
		}

		// Recursion until the last character is reached
		int index = word.charAt(width) - 'a';
		delete(trav.children[index], word, width + 1);
		return trav;

	}

	// Method to check if there is any children node exist
	private boolean isEmpty(TrieNode trav) {
		
		/* Checks each node one by one
		 * If children node exists, returns false
		 */
		for (int i = 0; i < 26; i++)
			if (trav.children[i] != null)
				return false;
		return true;
	}

	// Method to print trie
	public TrieNode display() {
		TrieNode trav = root;
		StringBuilder sb = new StringBuilder();
		int level = 0;
	
		// Calling overloaded display method
		display(trav, level, sb);
		return trav;
	}
	
	// Overloaded display method to print via recursion
	public void display(TrieNode trav, int level, StringBuilder str) {
		// If the Trie is empty
		if (trav == null)
			return;

		// If it's the last character of a word
		if (trav.isEndChar == true) {
			str = str.insert(level, trav.c);
			
			// If the character isn't the last node of the Trie
			if (isEmpty(trav)) {
				System.out.println(str);
				str.deleteCharAt(level);
				return;
			} else {
				System.out.println(str);
				str.deleteCharAt(level);
			}
		}

		// recursion until the last character is reached
		for (int i = 0; i < 26; i++) {
			if (trav.children[i] != null) {
				str = str.insert(level, trav.c);
				display(trav.children[i], level + 1, str);
				str.deleteCharAt(level);
			}
		}
	}

}