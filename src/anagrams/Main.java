package anagrams;


import java.io.*;
import java.util.*;

public class Main {
	
	/**help method to sort the single word(using Counting-Sort)
	 *  
	 * @param input: the word
	 * @return: word with letters in alphabetical order
	 */
	private static String SortWord(String input){
		char[] A = input.toCharArray();
		char[] B = input.toCharArray();
		final int k = 26;
		Counting_Sort(A, B, k);
		return String.valueOf(B);
	}
	
	
	/*
	 * Counting-Sort
	 */
	public static void Counting_Sort(char[] A, char[] B, int k){
		int[] C = new int[k];
		for(int i=0; i<k;i++){
			C[i] = 0;
		}
		for(int j=0; j<A.length; j++){
			C[A[j]-97]++;
		}
		for(int i=1; i<k; i++){
			C[i] += C[i-1];
		}
		for(int i=0; i<k; i++){
				C[i]--;
		}
		for(int j=A.length-1; j>=0;j--){
			B[C[A[j]-97]] = A[j];
			C[A[j]-97]--;
		}
		
	}
	
	
	/*
	 * Help method to sort the whole word list
	 */
	private static void MergeSort(ArrayList<Tuple> input, int p, int r){
		if(p < r){
			int q = (p+r)/2;
			MergeSort(input, p, q);
			MergeSort(input, q+1, r);
			Merge(input, p, q, r);
		}
	}
	private static void Merge(ArrayList<Tuple> input,int p,int q,int r){
		int n1 = q-p+1;
		int n2 = r-q;
		ArrayList<Tuple> L = new ArrayList<Tuple>();
		ArrayList<Tuple> R = new ArrayList<Tuple>();
		for(int i=0; i<n1; i++){
			L.add(i, input.get(p+i));
		}
		for(int j=0; j<n2; j++){
			R.add(j, input.get(q+j+1));
		}
		//"z" is like infinity 
		Tuple t =  new Tuple("z","z");
		L.add(t);		
		R.add(t);		
		int c1=0;
		int c2=0;
		for (int z = p; z<=r ; z++) {
			if((L.get(c1).get(1)).compareTo((R.get(c2).get(1)))<0){
				input.set(z, L.get(c1));
			  c1++;
			}
			else{
				input.set(z, R.get(c2));
				c2++;
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		try {
			//get the start time
			long startTime = System.nanoTime();
			
			// open fileReader
			FileReader fr = new FileReader("data/dict2");
			BufferedReader br = new BufferedReader(fr);
			
			
			//create a list of tuple
			ArrayList<Tuple> store = new ArrayList<Tuple>();
			
			
			String tmp;
			while ((tmp=br.readLine()) != null) {
				//original word
				String original = tmp;
				// sort the original word
				String sorted = SortWord(tmp);
				//store them into the tuple 
				Tuple tuple = new Tuple(original, sorted);
				//add into the list
				store.add(tuple);
			}
			
			//sort the whole list
			MergeSort(store, 0, store.size()-1);
			
			//create a writer
			PrintWriter writer = new PrintWriter("data/Anagram2.txt");
			
			Tuple temp = store.get(0);
			int Switch = 1;
			int newline = 0;
			//count for anagram groups
			int anagramsCount = 0;
			//write out anagram groups 
			for(int z=1; z<store.size();z++){
				Tuple temp2 = store.get(z);

				//start of a new anagram group
				if(temp2.get(1).equals(temp.get(1)) && Switch == 1){
					anagramsCount++;
					//start of the line
					writer.write("Group "+anagramsCount+": "+temp.get(0).toString()+", "+temp2.get(0).toString()+", ");
					//if find more anagrams, we don't need to switch 
					Switch = 0;
					//we need a new line
					//after we print all the members of current anagram group
					newline = 1;
				}
				//if we find more anagrams, write to the files
				else if(temp2.get(1).equals(temp.get(1))){
					writer.write(temp2.get(0).toString()+", ");
					newline = 1;
				}
				//next anagram group
				else{
					anagramsCount++;
					//print the new line
					if(newline == 1)
						writer.println("");
					
					//handle the special cases for the beginning of word list
					//handle the case that the first element is empty
					if(temp.get(1).equals("")){
						temp = temp2;				
					}
					//handle the case that the first anagram group has only one element 
					if(anagramsCount == 1){
						writer.write("Group "+anagramsCount+": "+temp.get(0).toString()+", ");
						writer.println("");
						writer.println("");
						temp = temp2;
						Switch = 1;
						newline = 0;
						continue;
					}
					
					
					temp = temp2;
					//we need to start write a new anagram group
					Switch = 1;
					newline = 0;
					
					writer.println("");
					writer.write("Group "+anagramsCount+": "+temp.get(0).toString()+", ");
					
					//if this anagram group has more than one member, set switch to 0
					if(z+1 < store.size() && store.get(z+1).get(1).equals(temp.get(1))){
						Switch = 0;
					}
					//else means there is only one word in this group
					//we start a newline
					else writer.println("");

				}
			}
			
			System.out.println("There are " + anagramsCount+" anagram groups in this file");
			
			
			//close fileWriter
			writer.close();
			
			//close the BufferedReader
			br.close();

			// use to calculate the time
			long endTime = System.nanoTime();
			System.out.println("Took "+(endTime - startTime)*0.000000001 + " s"); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
