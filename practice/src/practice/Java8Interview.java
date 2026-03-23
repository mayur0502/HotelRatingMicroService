package practice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;

public class Java8Interview {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 3, 4, 5, 6, 7, 8, 9, 10);

		Map<Boolean, List<Integer>> collect = list.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));

		System.out.println("Even numbers: " + collect.get(true));
		System.out.println("Odd numbers: " + collect.get(false));

		System.out.println("List size before removing duplicates: " + list.size());

		List<Integer> collect2 = list.stream().distinct().collect(Collectors.toList());
		System.out.println(collect2);
		System.out.println("List size after removing duplicates: " + collect2.size());

		String str = "Java Concept Of The Day: Java 8 Stream API";
		Map<Character, Long> collect3 = str.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(collect3);
		// here i want to remove the spaces from the string and then count the
		// characters
		Map<Character, Long> collect4 = str.chars().mapToObj(c -> (char) c).filter(ch -> ch != ' ')
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(collect4);

		Map<Character, Long> collect5 = str.toLowerCase().chars().mapToObj(c -> (char) c)
				.filter(ch -> ch != ' ' && ch != 'a')
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		System.out.println(collect5);

		List<String> stationeryList = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler",
				"Note Book", "Pencil");

		Map<String, Long> mapStr = stationeryList.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(mapStr);

		Map<String, Long> collect6 = stationeryList.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(e -> e.getValue() == 2).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        System.out.println(collect6);

		HashSet<String> set = new HashSet<>();

		stationeryList.stream().filter(s -> !set.add(s)).findFirst().ifPresent(System.out::println);

		List<Double> decimalList = Arrays.asList(12.45, 23.58, 17.13, 42.89, 33.78, 71.85, 56.98, 21.12);

		// Sort in decending order

		List<Double> collect7 = decimalList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(collect7);

		List<Double> collect8 = decimalList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());

		System.out.println(collect8);

		List<String> listOfStrings = Arrays.asList("Facebook", "Twitter", "YouTube", "WhatsApp", "LinkedIn");

		String collect9 = listOfStrings.stream().collect(Collectors.joining(", ", "[", "]"));
		System.out.println(collect9);

		List<String> collect10 = listOfStrings.stream().map(s -> "[" + s + "]").collect(Collectors.toList());
		System.out.println(collect10);

		String strJoin = listOfStrings.stream().map(s -> "[" + s + "], ").collect(Collectors.joining());
		System.out.println(strJoin);

		List<String> list2 = listOfStrings.stream().map(s -> "I" + s.substring(1)).toList();
		System.out.println(list2);
		List<String> list3 = listOfStrings.stream().map(s -> s.substring(0, s.length() - 2)).toList();
		System.out.println(list3);

		// 7) From the given list of integers, print the numbers which are multiples of
		// 5?

		List<Integer> listOfIntegers = Arrays.asList(4125, 1152, 5126, 1165, 2514, 7135, 21, 1839);
		List<Integer> collect11 = listOfIntegers.stream().filter(a -> a % 5 == 0).collect(Collectors.toList());
		System.out.println(collect11);

		// Number starts with 1

		List<String> collect12 = listOfIntegers.stream().map(s -> s + "").filter(s -> s.startsWith("1"))
				.collect(Collectors.toList());
		System.out.println(collect12);

		System.out.println();
		List<String> list4 = listOfIntegers.stream().map(String::valueOf).filter(s -> s.charAt(s.length() / 2) == '1')
				.toList();
		System.out.println("listOfIntegers ==> " + list4);

		List<Integer> list5 = listOfIntegers.stream().map(String::valueOf).filter(s -> {
			int len = s.length();
			return s.charAt(len / 2) == '1' && s.charAt((len - 1) / 2) == '1';
		}).map(Integer::valueOf).toList();

		System.out.println(list5);

//       8) Given a list of integers, find maximum and minimum of those numbers?

		List<Integer> listOfInteger = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);

		Object[] array = listOfInteger.stream().sorted(Comparator.naturalOrder()).toArray();

		System.out.println("Min value: " + array[0]);
		System.out.println("max value: " + array[array.length - 1]);

		Integer Max = listOfInteger.stream().max(Comparator.naturalOrder()).get();
		Integer Min = listOfInteger.stream().min(Comparator.naturalOrder()).get();

		System.out.println("Max: " + Max + "\nMin: " + Min);

//       9) How do you merge two unsorted arrays into single sorted array using Java 8 streams?
		int[] a = new int[] { 4, 2, 7, 1 };

		int[] b = new int[] { 8, 3, 9, 5, 1, 2 };

		int[] sorted = IntStream.concat(Arrays.stream(a), Arrays.stream(b)).sorted().toArray();
		System.out.println(Arrays.toString(sorted));
		int[] array2 = IntStream.concat(Arrays.stream(a), Arrays.stream(b)).sorted().distinct().toArray();
		System.out.println(Arrays.toString(array2));

//		11) How do you get three maximum numbers and three minimum numbers from the given list of integers?

		List<Integer> listOfIntegerss = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);

		List<Integer> list6 = listOfIntegerss.stream().sorted().limit(3).toList();
		List<Integer> list7 = listOfIntegerss.stream().sorted(Comparator.reverseOrder()).limit(3).toList();
		System.out.println(list6 + "\n" + list7);

//		Check below two string are anagram or not

		String s1 = "RaceCar";
		String s2 = "CarRace";

		String sorted1 = Stream.of(s1.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
		System.out.println(sorted1);
		String sorted2 = Stream.of(s2.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
		System.out.println(sorted2);
		if (sorted1.equals(sorted2)) {
			System.out.println("String is anagram");
		} else {
			System.out.println("String is not anagram");
		}
		String[] str1 = s1.toUpperCase().split("");
		String[] str2 = s2.toUpperCase().split("");
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();

		Arrays.sort(str1);
		Arrays.sort(str2);
		for (String string : str1) {
			sb1.append(string);
		}
		for (String string : str2) {
			sb2.append(string);
		}
		System.out.println(sb1);
		System.out.println(sb2);

		if ((sb1.toString()).equals(sb2.toString())) {
			System.out.println("Anagram");
		} else {
			System.out.println("Not anagram");
		}

		int i = 15623;
		Integer collect13 = Stream.of(String.valueOf(i).split("")).collect(Collectors.summingInt(Integer::parseInt));

		System.out.println(collect13);

//	14) Find second largest number in an integer array?

		List<Integer> listOfInt = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);

		Integer first = listOfInt.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
		Integer first2 = first;
		System.out.println(first2);

		int ab = 123456789;
		Integer collect14 = Stream.of(String.valueOf(ab).split("")).collect(Collectors.summingInt(Integer::parseInt));
		System.out.println(collect14);

//	15) Given a list of strings, sort them according to increasing order of their length?

		List<String> listOfStrins = Arrays.asList("Java", "Python", "C#", "HTML", "Kotlin", "C++", "COBOL", "C");
		List<String> list8 = listOfStrins.stream().sorted(Comparator.comparing(String::length)).toList();
		System.out.println(list8);

//    Find first non repeating character from given string 
		String charStr = "iswiss";
		Map<Character, Long> collect15 = charStr.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

		Character character = collect15.entrySet().stream().filter(entry -> entry.getValue() == 1)
				.map(Map.Entry::getKey).findFirst().get();
		System.out.println(character);

		Character character2 = charStr.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).skip(1).findFirst()
				.orElse('\0');
		System.out.println("character2  => " + character2);
		HashSet<Character> sh = new HashSet<Character>();
		char first3 = charStr.chars().mapToObj(c -> (char) c).filter(c -> !sh.add(c)).findFirst().orElse('\0'); // orElseGet(0);
		System.out.println(first3);
		HashSet newhash = new HashSet();
		String orElse = Arrays.stream(charStr.split("")).filter(s -> !newhash.add(s)).findFirst().orElse(null);
		System.out.println(newhash + "\n" + orElse);

		HashSet second = new HashSet();
		String orElse2 = Arrays.stream(charStr.split("")).filter(s -> second.add(s)).skip(1).findFirst().orElse(null);

		System.out.println("Second repeating char -> " + orElse2);

//	 sort number from number of list

		List<Integer> numbers = Arrays.asList(10, 2, 5, 8, 1, 3, 7, 1, 6);

		List<Integer> sorted3 = numbers.stream().sorted().toList();
		System.out.println(sorted3);

		List<Integer> list9 = numbers.stream().sorted(Comparator.reverseOrder()).toList();
		System.out.println(list9);

		Set<Integer> collect16 = numbers.stream().collect(Collectors.toSet());

		if (numbers.size() != collect16.size()) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}

		System.out.println(
				LocalDate.now() + "\n" + LocalTime.now() + "\n" + LocalDateTime.now() + "\n" + ZonedDateTime.now());

//	calcuate cube and find num< 50;

		List<Integer> numInt = Arrays.asList(3, 5, 20, 11, 9, 7, 21);

		List<Integer> list10 = numInt.stream().map(n -> n * n * n).filter(n -> n > 50).toList();
		System.out.println(list10);

		List<String> names = Arrays.asList("alice", "bob", "charlie", "david");

		List<String> list11 = names.stream().map(String::toUpperCase).toList();
		System.out.println(list11);

		List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");

		Map<String, Long> collect17 = words.stream()
				.collect(Collectors.groupingBy(word -> word, Collectors.counting()));
		System.out.println(collect17);
		for (Map.Entry<String, Long> entry : collect17.entrySet()) {
			String key = entry.getKey();
			Long val = entry.getValue();
			System.out.println(key + " " + val);
		}

		List<String>  mapStr1 = collect17.entrySet().stream().filter(entry -> entry.getValue() > 1).map(Map.Entry::getKey).toList();
		System.out.println(mapStr1);
		
		List<String> myList = Arrays.asList("apple", "banana", "orange");
		
		Optional.ofNullable(myList).filter(lists->!lists.isEmpty()).ifPresent(lists->lists.forEach(System.out::println));
		

		
		BiFunction<Integer, Integer, Integer> sum = (a1,b1)-> a1+b1;
		
		System.out.println(sum.apply(10, 30));
		
		List<Integer> findSqrNumber = Arrays.asList(1, 2, 3, 4, 5);
		
		List<Integer> list12 = findSqrNumber.stream().map(n-> n*n).toList();
		System.out.println(list12);
		
		findSqrNumber.stream().map(n-> n*n).forEach(System.out::println);
		
		List<String> strings = Arrays.asList("apple", "banana", "cherry", "date");
		
		strings.stream().map(String::length).forEach(System.out::println);
		
		Map<String, Long> collect18 = strings.stream().collect(Collectors.groupingBy(strs-> strs, Collectors.counting()));
		
		System.out.println(collect18);
		
		Map<String, Integer> collect19 = strings.stream().collect(Collectors.toMap(strs-> strs,strs->strs.length()));
		System.out.println(collect19);
	}
}
