package com.app.core;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TrieNode implements Serializable {
	public char c;
	public boolean isEndChar;
	public TrieNode[] children;

	// Constructor
	public TrieNode(char c) {
		this.c = c;
		isEndChar= false;
		children = new TrieNode[26];
		for (int i = 0; i < 26; i++) {
			children[i] = null;
		}
	}
	
}