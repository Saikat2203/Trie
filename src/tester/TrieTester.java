package tester;

import static utils.IOUtils.*;
import java.util.Scanner;
import com.app.core.Trie;

public class TrieTester {

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			Trie tNode = new Trie();
			tNode.insert("Hello");
			tNode.insert("this");
			tNode.insert("is");
			tNode.insert("testing");
			tNode.insert("let");
			tNode.insert("talker");
			tNode.insert("talk");
			tNode.insert("talkative");

//			System.out.println(tNode.search("talker"));
//			System.out.println(tNode.delete("letp"));
//		tNode.display();
			boolean loop = true;
			
			while(loop) {
				System.out.println("Welcome to Trie Data Structure!!!");
				System.out.println("Press 1 to insert a word. The words must be in lowercase and aplhabet characters only");
				System.out.println("Press 2 to search for a word element");
				System.out.println("Press 3 to delete a word from the Trie data structure");
				System.out.println("Press 4 to display the words present in Trie");
				System.out.println("Press 5 to store the Trie data in a file in binary format");
				System.out.println("Press 6 to restore the binary data from a file and display them on the console");
				System.out.println("Press 7 to read data from a file and insert the words in the trie");
				System.out.println("Press 10 to exit");
				System.out.print("Enter choice: ");
				int choice = scan.nextInt();
				
				try {
					switch (choice) {
					case 1:
						System.out.println("Enter the word to insert: ");
						if(tNode.insert(scan.next()))
							System.out.println("Word has been added successfully!!!");
						else
							System.out.println("Word not added. Either the word already exists in the tree or wrong format!!!");
						break;

					case 2:
						System.out.println("Enter the word to search: ");
						if(tNode.search(scan.next()))
							System.out.println("Word found!!!");
						else
							System.out.println("Word not found!!!");
						break;
						
					case 3:
						System.out.println("Enter the word to delete: ");
						if(tNode.delete(scan.next()))
							System.out.println("Word has been successfully deleted!!!");
						else
							System.out.println("Word not deted!!! It either doesn't exist or wrong format");
						break;
						
					case 4:
						tNode.display();
						break;
						
					case 5:
						System.out.println("Enter the file name with valid extension type to store data");
						storeTrie(tNode, scan.next());
						break;
						
					case 6:
						System.out.println("Enter the filename along with full path to restore data from: ");
						String file = scan.next();
						System.out.println("The following words exist in the file: ");
						tNode = restoreDetails(file);
						break;
						
					case 7:
						System.out.println("Enter the filename along with full path to load the data in the Trie");
						tNode = readAndInsert(scan.next());
						break;
						
					case 10:
						loop = false;
						System.out.println("Thank you!!!");
						break;
						
					default:
						System.out.println("Please select a valid choice!!!");
						break;
					}
				} catch (Exception e) {
					System.out.println(e);;
				}
				scan.nextLine();
			} 
		}
	}
}