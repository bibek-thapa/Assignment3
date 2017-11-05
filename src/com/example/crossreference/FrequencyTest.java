package com.example.crossreference;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

/**
 * This program counts the frequences of all words and symbol sequences in a
 * file.
 */
public class FrequencyTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
			System.out.print("Input file: ");
			String inFile = in.next();

			Scanner inf = new Scanner(new File(inFile));

			FrequencyCount counter = new FrequencyCount();
			counter.parseText(inf);

			counter.displayCounts();

			inf.close();
		} catch (IOException exception) {
			System.out.println("Error: " + exception);
		}

		in.close();
	}
}
