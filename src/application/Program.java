package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Map<String, Integer> candidates = new TreeMap<>();

		System.out.print("Please enter file full path: ");
		File file = new File(sc.nextLine());

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = br.readLine();

			while (line != null) {
				String[] str = line.split(",");
				String key = str[0];
				Integer value = Integer.parseInt(str[1]);

				if (!candidates.containsKey(key)) {
					candidates.put(key, value);
					
				} else {

					int valorAnterior = candidates.get(key);
					candidates.put(key, valorAnterior + value);
				}
				line = br.readLine();
			}
			
		} catch (IOException e) {
			System.out.print("Error: " + e.getMessage());
		}

		String folder = file.getParent();

		//Creating the output file
		String newPath = folder + "\\result.txt";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(newPath))) {
			
			for (String key : candidates.keySet()) {

				bw.write(key + ": " + candidates.get(key) + " votes");
				bw.newLine();
			}	
			
		} catch (IOException e) {
			System.out.print("Error writing file: " + e.getMessage());
		}

		sc.close();
	}

}
