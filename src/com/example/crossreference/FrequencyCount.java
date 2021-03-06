package com.example.crossreference;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * This program identifies all words and symbol sequences from text in a file
 * and keeps track of their frequency counts
 */
public class FrequencyCount {
	Map<String, Set<Integer>> frequencies; // holds token-count pairs

	public FrequencyCount() {
		// create an object from class Map and
		// assign it to frequencies
		//LinkedHashMap is used so that the identifier goes in the order as it went through
		frequencies = new LinkedHashMap<String, Set<Integer>>();

	}

	public void parseText(Scanner in) throws IOException {
		String token = "";
		
		//line number always start from Line 1
		int lineNo = 1;

		while (in.hasNextLine()) {
			String line = in.nextLine() + " ";
			char[] list = line.toCharArray();
			int state = 0;
			//previous state is defined so that it can be helpful for the comment and comma
			int prevState = 0;

			for (char ch : list) {
				// process input text character by character,
				// identify each token and update its count

				switch (state) {

				case 0:
					if (Character.isLetter(ch) || ch == '_') {
						token += ch;
						state = 1;

					} else if (Character.isDigit(ch)) {
						token += ch;
						state = 2;

					}

					else if (ch == '/')//  checks for the first character of the comment
					{
						token += ch;
						state = 3;

					} else if (ch == '"') { // checks the first comma
						token += ch;
						state = 5;
					}

					else {
						token = "";
						state = 0;

					}
					break;

				case 1:
					if (Character.isLetterOrDigit(ch) || ch == '_') { //checks if it is letter or underscore
						token += ch;
						state = 1;

					}

					else if (ch == '/') {
						token += ch;
						state = 3;
					} else if (ch == '"') {
						token += ch;
						state = 5;

					}

					else {
						if (prevState != 2) {
							updateCounts(token, lineNo);
						}

						token = "";

						state = 0;

					}
					break;

				case 2:
					if (Character.isDigit(ch)) {
						token += ch;
						state = 2;
						prevState = 2;
					}

					else if (Character.isLetter(ch)) {
						token += ch;
						state = 1;
						prevState = 2;
					} else if (ch == '/') {
						token += ch;
						state = 3;
					} else if (ch == '"') {
						token += ch;
						state = 5;
					}

					else {
						token = "";
						state = 0;

					}
					break;

				case 3:
					if (ch == '/') {
						token += ch;
						state = 4;
						prevState = 3;

					}
					break;

				case 4:
					//checks the second comment state
					if (ch == '/') {

						token += ch;
						state = 4;
						prevState = 4;
					}

					//checks if the previous state was comment character.Both of these previous state 
					//gives the comment characters
					else

					{
						if (prevState == 4 || prevState == 3) {

							token = "";

						}
					}
					break;

				case 5:
					// checks the second comma
					if (ch == '"') { 
						token += ch;
						state = 6;
						prevState = 5;
					}
					break;

				case 6:
					if (prevState == 5) {
						token = "";
						prevState=6;
						break;
						
					}
					
					else if(prevState==6 && Character.isLetter(ch)) 
					{
						token+=ch;
						state =1;
					}
					break;
				}

			}

			lineNo++;
		}

		// System.out.println("number of lines is " + lineNo);
		in.close();
	}

	// Display all words and frequency counts on the screen
	public void displayCounts() {
		for (String name : frequencies.keySet()) {
			String key = name.toString();
			String value = frequencies.get(name).toString();

			System.out.println("Identifier is " + key + " AND " + "Line numbers for " + key + " are " + value);
			// System.out.print(" ");
		}

	}

	// Check and see if the specified token is in map frequencies
	// If not, put in this map a token-count pair where count is 1
	// otherwise, increment its frequency count and update the map
	public void updateCounts(String token, int linenumber) {

		
		Set<Integer> lineSet = frequencies.get(token);

		if (lineSet == null) {
			//TreeeSet defined so that the duplicate values do not come and integer goes in the numerical order
			lineSet = new TreeSet<>();

		}

		lineSet.add(linenumber);

		frequencies.put(token, lineSet);

	}
}