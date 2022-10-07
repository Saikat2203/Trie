package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.app.core.Trie;
import com.app.core.TrieNode;

public class IOUtils {

	// To store data in a file in binary form
	public static void storeTrie(Trie trieData, String fileName) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName)))) {
			out.writeObject(trieData);
			System.out.println("Data added to the file sussessfully!!!");
		}
	}

	// To read from a binary file
	public static Trie restoreDetails(String fileName)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(fileName);
		if (file.isFile() && file.canRead()) {
			try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
				Trie restoreTrie = (Trie) in.readObject();
				restoreTrie.display();
				return restoreTrie;
			}
		}
		System.out.println("Invalid file name");
		return null;
	}

	// To read from a character file
	public static Trie readAndInsert(String fileName) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			Trie loadTrie = new Trie();
			br.lines().map(w -> w.toLowerCase()).forEach(w -> loadTrie.insert(w));
			System.out.println("File loaded in the Trie successfully!!!");
			return loadTrie;
		}

	}

}