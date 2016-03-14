package anagrams;

import java.io.*;
/*
 * This class is for writing anagram classes 
 * in anagram1 that contain more than 5 words
 * 
 * This must run after the main class, since it needs Anagram1.txt
 */
public class Anagram1_5 {

	public static void main(String[] args){
		try {
			//open the file reader
			FileReader fr = new FileReader("data/Anagram1.txt");
			BufferedReader br = new BufferedReader(fr);
			
			//open the file writer
			PrintWriter writer = new PrintWriter("data/5.txt");
			
			String tmp;
			// if the anagram group contains more than five
			//words, then write it to a new file
			while ((tmp=br.readLine()) != null) {
				if(tmp.split(",").length > 6 ){
					writer.write(tmp);
					writer.println("");
				}
			}
			
			System.out.println("Done!");
			br.close();
			writer.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
