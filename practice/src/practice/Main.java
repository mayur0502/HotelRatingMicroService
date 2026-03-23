package practice;
// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
class Main {
    // Reverse using StringBuilder (fast and simple)
    public static String reverseWithStringBuilder(String s) {
        if (s == null) return null;
        return new StringBuilder(s).reverse().toString();
    }

    // Reverse iteratively by swapping characters (in-place on char array)
    public static String reverseIterative(String s) {
        if (s == null) return null;
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++; j--;
        }
        return new String(arr);
    }

    // Reverse recursively
    public static String reverseRecursive(String s) {
        if (s == null) return null;
        if (s.length() <= 1) return s;
        return reverseRecursive(s.substring(1)) + s.charAt(0);
    }

    public static void main(String[] args) {    
//        String[] arr = {"cat","dog","tacs","god","bird"};
//
//        Map<String,Integer> map = new HashMap<>();
//
//        for (String string : arr) {
//			char[] ch = string.toCharArray();
//			Arrays.sort(ch);
//			String key = new String(ch);
//			map.put(key, map.getOrDefault(key, 0)+1);
//			
//		}
//        for (String string : arr) {
//        	char[] ch = string.toCharArray();
//			Arrays.sort(ch);
//			String key = new String(ch);
//        	if(map.get(key)==1) {
//        		System.out.println(string);
//        	}
//		}
// 
    	
//        String[] arr = {"cat","dog","tac","god","bird"};
//        Map<String, Integer> map = new HashMap();
//        List<String> result = new ArrayList<>();
//        for (String string : arr) {
//			
//        	char[] ch = string.toCharArray();
//        	Arrays.sort(ch);
//        	String str = new String(ch);
//        	
//        	map.put(str, map.getOrDefault(str, 0)+1);
//        	
//		}
//        for (String string : arr) {
//			char[] ch = string.toCharArray();
//			Arrays.sort(ch);
//			String str = new String(ch);
//			
//			if(map.get(str) ==1) {
//				result.add(string);
//			}
//		}
//        System.out.println(result);
// 
    	
//    	String[] arr = {"cat","dog","tac","god","bird"};
//    	
//    	Map<String, Long> collect = Arrays.stream(arr).map(s ->{ char[] ch = s.toCharArray();
//    		Arrays.sort(ch);
//    		
//    	return new String(ch);
//    	}).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//    	
//    	List<String> filter = Arrays.stream(arr).filter(s-> {
//    		char[] ch = s.toCharArray();
//    		Arrays.sort(ch);
//    		String key = new String(ch);
//    		return collect.get(key)==1;
//    	}).collect(Collectors.toList());
    	
//    	String[] arr = {"cat","dog","tac","god","bird"};
//    	
//    	List<String> result = Arrays.stream(arr)
//    	        .collect(Collectors.groupingBy(s -> {
//    	            char[] ch = s.toCharArray();
//    	            Arrays.sort(ch);
//    	            return new String(ch);
//    	        }))
//    	        .values()
//    	        .stream()
//    	        .filter(list -> list.size() == 1)
//    	        .flatMap(List::stream)
//    	        .collect(Collectors.toList());
//
//    	String[] arr1 = {"cat","dog","tac","god","bird"};
//    	
//    	List<List<String>> collect = Arrays.stream(arr1).collect(Collectors.groupingBy(s -> {
//    		char[] ch = s.toCharArray();
//    		Arrays.sort(ch);
//    		return new String(ch);
//    	})).values().stream().filter(list -> list.size()==1).collect(Collectors.toList());
//   
//    
//    String str = "mayur@gail.com";
//    String regress = "[@,.]";
//    String[] strArr= str.split(regress);
//    System.out.println(Arrays.toString(strArr));
//    String name = strArr[0];
//    String Domainname = strArr[1];
//    String extension = strArr[2];
//    String firstName = name.charAt(0) +  name.substring(1, name.length()-1).chars()
//					.mapToObj(c -> "*").collect(Collectors.joining())+ 
//					name.charAt(name.length()-1)+"@"+Domainname.charAt(0)+
//					Domainname.substring(1,Domainname.length()) .
//					chars().mapToObj(s->"*").collect(Collectors.joining())
//					+"."+extension;
//    
//    System.out.println(firstName);
//    
//
//    String mobNo = "9823190728";
//    
//    String upMobNo = mobNo.substring(0,2)+mobNo.substring(2,mobNo.length()-2).
//    		chars().mapToObj(c-> "*").collect(Collectors.joining())+
//    		mobNo.substring(mobNo.length()-2,mobNo.length());
//    System.out.println(upMobNo);
//    
//    String collect2 = IntStream.range(0, mobNo.length()).
//    mapToObj(i-> mobNo.charAt(mobNo.length()-1-i)).map(String::valueOf) .collect(Collectors.joining());
//    
//    System.out.println(collect2);
//    
//String finalStr =     mobNo.substring(0,5)+ mobNo.substring(5,mobNo.length()).chars().mapToObj(c-> "*").collect(Collectors.joining()) ;
//    System.out.println(finalStr);
//    
//    String nameStr = "programming";
//    String[] strArr1 = nameStr.split("");
//
//    Map<String, Long>  mainResult = 
//    
//    Arrays.stream(strArr1).collect(Collectors.groupingBy(s-> s,Collectors.counting()));
//    System.out.println(mainResult);
//    
//    String mainStr = "i Like to have ";
//String[] mainStrArr = mainStr.split("");
//Map<String, Long> collect3 = Arrays.stream(mainStrArr).filter(s-> !s.equals(" " )).collect(Collectors.groupingBy(s->s,Collectors.counting()));
//System.out.println(collect3);
//
//
//String resultStr = IntStream.range(0, mainStr.length()) .mapToObj(i-> String.valueOf(mainStr.length()-1-i)).collect(Collectors.joining());
//System.out.println(resultStr);
//

    	String str= "programming";
    	
    	StringBuffer collect = str.chars().distinct().collect(StringBuffer::new,StringBuffer::appendCodePoint,StringBuffer::append);
    	System.out.println(collect);
    	
    	String disStr = "swiss";
    	String[] arrStr = disStr.split("");
    	Entry<String, Long> first = Arrays.stream(arrStr)
    			.collect(Collectors.groupingBy(s -> 
    			s,LinkedHashMap::new,	Collectors.counting())).
    			entrySet().stream().
    			filter(e-> e.getValue() ==1).findFirst().orElse(null);
    	
    	System.out.println("First non-repeated char entry in '"+disStr+"' = " + first);
    	
    	// Demonstrate the three reversal methods
    	String sample = "Hello, World!";
    	System.out.println("Original: " + sample);
    	System.out.println("reverseWithStringBuilder: " + reverseWithStringBuilder(sample));
    	System.out.println("reverseIterative: " + reverseIterative(sample));
    	System.out.println("reverseRecursive: " + reverseRecursive(sample));
    	
    	// Quick edge-case checks
    	System.out.println("Empty string -> '" + reverseWithStringBuilder("") + "'");
    	System.out.println("Null -> " + reverseWithStringBuilder(null));
    	
    	}
}