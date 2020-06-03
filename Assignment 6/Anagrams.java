import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * 
 * @author Ankit 
 *
 */
public class Anagrams {
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 
								67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	public Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	
	private void buildLetterTable() {
		letterTable = new HashMap<Character,Integer>();
		Character[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
									'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		for(int i = 0; i < 26; i++) {
			letterTable.put(alphabet[i], primes[i]);
		}
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	private Long myHashCode(String s) {
		long key = 1;
		for(int i = 0; i < s.length(); i++) {
			key = key * (long)letterTable.get(s.charAt(i));
		}
		return key;
	}
	
	/**
	 * 
	 * @param s
	 */
	private void addWord(String s) {	
		Long hc = this.myHashCode(s);
		if(anagramTable.get(hc) == null) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(s);
			anagramTable.put(hc, temp);
		} else {
			anagramTable.get(hc).add(s);
		}
	}
	
	/**
	 * 
	 * @param s
	 * @throws IOException
	 */
	private void processFile(String s) throws IOException {
		FileInputStream fStream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
		String strLine;
		while((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}
	
	/**
	 * 
	 * @return
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		
		ArrayList<Map.Entry<Long,ArrayList<String>>> temp = new ArrayList<>(); 
		int max = 0;
		for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
			if(entry.getValue().size() > max) {
				temp.clear();
				temp.add(entry);
				max = entry.getValue().size();
			} else if(entry.getValue().size() == max) {
				temp.add(entry);
			}
		}
		 return temp;
		 
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main (String [] args) {
		Anagrams a = new Anagrams();
	
		final long startTime = System.nanoTime();
		try {
			a.processFile ("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries ();
		final long estimatedTime = System.nanoTime() - startTime ;
		final double seconds = ((double)estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: " + maxEntries);
	}
	
}
