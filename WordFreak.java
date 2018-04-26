import java.io.*;
import java.util.Scanner;
import java.util.Map.*;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;



public class WordFreak {

    public static void main(String[] args) throws IOException {

        //args: inputFile, outputCount


        //hashmap (effectively a hashset) of words and their counts
        Map<String, Integer> wMap = new HashMap<>();

        try(Scanner s = new Scanner(new BufferedReader(new FileReader(args[0])))) {

            while (s.hasNext()) {
            	//read line, set all lower case, remove special chars except spaces, split on spaces
                String[] words = s.next().toLowerCase().replaceAll("[^a-z0-9\\s+]","").split("\\s+");
                for(String w : words){
                	//add to hashset if absent with count 1, else increment count
                	wMap.replace(w, (wMap.putIfAbsent(w, 1)==null ? 0 : wMap.putIfAbsent(w, 1))+1);
                }
            }

            Map<String, Integer> wOutMap = sortByValue(wMap);

            int limit = Integer.parseInt(args[1]);
            for(String w : wOutMap.keySet()){
	            System.out.printf("%s -- %d\n", w, wMap.get(w));
	            limit--;
	            if(limit <=0) break;
	        }

        }
    }

    //adapted from Carter Page, https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values?noredirect=1&lq=1
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());
        Collections.reverse(list);

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}