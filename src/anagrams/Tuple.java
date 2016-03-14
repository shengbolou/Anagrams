package anagrams;

/*
 * This is the tuple class
 * The String x is the original word
 * The String y is the sorted word
 * 
 */
public class Tuple{
	  String x;
	  String y;
	
	public Tuple(String x,String y) {
		this.x = x;
		this.y = y;
	}
	
	public String get(int position){
		if(position == 0)
			return x;
		else {
			return y;
		}
	}
	public void set(int position,String input){
		if(position == 0)
			this.x = input;
		else
			this.y = input;
		
	}
}
