package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8Stream {

	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(10, 15, 8, 49, 25, 98, 32);

//		Find even number from list

		String str = "Mayur";
String collectstr1 = IntStream.range(0, str.length()).mapToObj(i -> str.charAt(str.length()-1-i)).map(String::valueOf).collect(Collectors.joining());
System.out.println(collectstr1);
		List<Integer> listEven = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
		System.out.println(listEven);

//		Find even number when arrays is given

//		int[] arr = {10,15,8,49,25,98,32};
//		List<Integer> collect = Arrays.stream(arr).boxed().filter(i -> i%2==0).collect(Collectors.toList());
//		System.out.println(collect);

//		Given a list of integers, find out all the numbers starting with 1 using Stream functions?

		List<Integer> myList = Arrays.asList(10, 15, 8, 49, 25, 98, 32);

		List<String> StrtWith = myList.stream().map(s -> s + "").filter(s -> s.startsWith("1"))
				.collect(Collectors.toList());
		System.out.println(StrtWith);

//		* When numbers are given as Array int[] arr = {10,15,8,49,25,98,32}; */

//		int[] arr = {10,15,8,49,25,98,32};
//		Arrays.stream(arr).boxed().map(s-> s+"").filter(s-> s.startsWith("1")).forEach(System.out::println);

//		How to find duplicate elements in a given integers list in java using Stream functions?

		List<Integer> myLists = Arrays.asList(10, 15, 8, 49, 25, 98, 98, 32, 15);
		HashSet<Integer> set = new HashSet<Integer>();

		List<Integer> collect = myLists.stream().filter(s -> !set.add(s)).collect(Collectors.toList());
		System.out.println(collect);

		List<Integer> collect2 = myLists.stream().distinct().collect(Collectors.toList());
		System.out.println(collect2);

//		Given the list of integers, find the first element of the list using Stream functions?
		myLists.stream().findFirst().ifPresent(System.out::println);

//		Given a list of integers, find the total number of elements present in the list using Stream functions?

		long count = myLists.stream().count();
		System.out.println(count);

//		Given a list of integers, find the maximum value element present in it using Stream functions?

		long maxNo = myLists.stream().max(Integer::compare).get();
		System.out.println(maxNo);

//		When numbers are given as Array int[] arr = {10,15,8,49,25,98,98,32,15}; 
		int[] arr = { 10, 15, 8, 49, 25, 98, 98, 32, 15 };
		long max = Arrays.stream(arr).boxed().max(Integer::compare).get();
		System.out.println(max);

//		Given a String, find the first non-repeated character in it using Stream functions?
		String input = "Java articles are Awesome";
		LinkedHashMap<String, Long> mapset = new LinkedHashMap<>();
		Character character = input.chars().mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s)))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(entry -> entry.getValue() == 1L).map(s -> s.getKey()).findFirst().get();

		System.out.println(character);

		char orElse = input.chars().mapToObj(ch -> (char) ch).filter(ch -> input.indexOf(ch) == input.lastIndexOf(ch))
				.findFirst().orElse(null);
		System.out.println(orElse);

		Character orElse2 = input.chars().mapToObj(s -> (char) s)
				.filter(ch -> input.indexOf(ch) != input.lastIndexOf(ch)).findFirst().orElse(null);
		System.out.println(orElse2);

		Character character2 = input.chars().mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s)))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(n -> n.getValue() != 1L).map(n -> n.getKey()).findFirst().get();
		System.out.println(character2);

		Character orElse3 = input.chars().mapToObj(ch -> (char) ch)
				.filter(s -> input.indexOf(s) == input.lastIndexOf(s)).skip(1).findFirst().orElse(null);
		System.out.println(orElse3);

//		Given a list of integers, sort all the values present in it using Stream functions?

		List<Integer> listInt = Arrays.asList(10, 20, 22, 14, 45, 23, 5, 56, 85, 92, 1);
		List<Integer> sorted = listInt.stream().sorted().collect(Collectors.toList());
		System.out.println(listInt + "\n" + sorted);

//		Given a list of integers, sort all the values present in it in descending order using Stream functions?

		List<Integer> collect3 = listInt.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(collect3);

//		Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

		int[] nums = { 10, 20, 30, 10, 40, 50, 30, 60 };
		List<Integer> collect4 = Arrays.stream(nums).boxed().collect(Collectors.toList());
		HashSet<Integer> listSet = new HashSet<>(collect4);

//		if(listSet.size() == collect4.size()) {
//			System.out.println(false);
//		}else {
//			System.out.println(true);
//		}

		boolean anyMatch = Arrays.stream(nums).boxed().anyMatch(s -> !listSet.add(s));
		System.out.println(anyMatch);

//		Write a Java 8 program to concatenate two Streams?

		List<String> list1 = Arrays.asList("Java", "8");
		List<String> list2 = Arrays.asList("explained", "through", "programs");

//		Stream.concat(list1.stream(), list2.stream()).forEach(str -> System.out.print(str + " "));
		System.out.println();
//        Java 8 program to perform cube on list elements and filter numbers greater than 50.
		List<Integer> integerList = Arrays.asList(4, 5, 6, 7, 1, 2, 3);

		List<Integer> cubeLst = integerList.stream().map(s -> s * s * s).filter(a -> a > 50)
				.collect(Collectors.toList());
		System.out.println(cubeLst);
		
//		Write a Java 8 program to sort an array and then convert the sorted array into Stream?
		
        int arrInt[] = { 99, 55, 203, 99, 4, 91 };
        Arrays.parallelSort(arrInt);
        
        Arrays.stream(arrInt).forEach(System.out::println);
        
//        How to use map to convert object into Uppercase in Java 8?
        
        String name = "mayur";
        name.chars().mapToObj(c-> character.toUpperCase((char)c)).forEach(System.out::println);
        String collect5 = name.chars().mapToObj(c-> String.valueOf(character.toUpperCase((char) c))).collect(Collectors.joining());
        System.out.println(collect5);
        
        List<String> strList =Arrays.asList("aa","bb","cc","dd");
        
        List<String> collect6 = strList.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect6);
        strList.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);
        
//        18. How to count each element/word from the String ArrayList in Java8?

        List<String> names = Arrays.asList("AA", "BB", "AA", "CC");
        Map<String, Long> collect7 = names.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(collect7);
        
//        19. How to find only duplicate elements with its count from the String ArrayList in Java8?
        
        List<Entry<String, Long>> collect8 = names.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
        		.entrySet().stream().filter(entry -> entry.getValue() > 1).collect(Collectors.toList());
        System.out.println(collect8);

//        21. Write a Program to find the Maximum element in an array?
        
        int[] arrs = {12,19,88,99,101,25,35};
        int asInt = Arrays.stream(arrs).max().getAsInt();
        System.out.println(asInt);
        
//        Write a program to print the count of each character in a String?
        
        String strNames = "string data to count each character";
        
        Map<String, Long> collect9 = Arrays.stream(strNames.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(collect9);
        }

}
