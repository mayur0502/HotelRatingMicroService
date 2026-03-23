package practice;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapExample {

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
		/**
		 * @param args
		 */
		/**
		 * @param args
		 */
		public static void main(String[] args) {
		System.out.println("Hi you here for flatmap practice");
		
//		Flat into single list
		 List<List<Integer>> list = Arrays.asList(
	                Arrays.asList(1, 2, 3),
	                Arrays.asList(4, 5),
	                Arrays.asList(6, 7, 8)
	        );
		 
		 List<Integer> list2 = list.stream().flatMap(Collection::stream).toList();
		 System.out.println(list);
		 System.out.println("-----------------");
		 System.out.println(list2);
	
		
//		🔹 2. Flatten List of Strings into Characters
		
		List<String> words = Arrays.asList("java", "stream");

		List<String> collect = words.stream().flatMap(s-> Arrays.stream(s.split(""))).collect(Collectors.toList());
		System.out.println("---------------\nFlatten List of Strings into Characters\n");
		System.out.println(collect);
		
		List<String> collect2 = words.stream().flatMap(s-> Arrays.stream(s.split(""))).distinct().collect(Collectors.toList());
		List<String> list3 = words.stream().flatMap(s-> Arrays.stream(s.split(""))).distinct().sorted(Comparator.reverseOrder()).toList();
		System.out.println(collect2);
		System.out.println(list3);
		
		List<String> list4 = words.stream().flatMap(s-> Arrays.stream(s.split(""))).distinct().sorted().toList();
		System.out.println(list4);
		
//		🔹 4. Flatten Object List (Employee → Skills)
		
		List<EmployeeSecond> employees = Arrays.asList(
		        new EmployeeSecond("A", Arrays.asList("Java", "Spring")),
		        new EmployeeSecond("B", Arrays.asList("Angular", "JS")),
		        new EmployeeSecond("C", Arrays.asList("Java", "Microservices"))
		);
		
		System.out.println(employees);
		List<String> list5 = employees.stream().flatMap(emp -> emp.getSkills().stream()).distinct().toList();
		System.out.println(list5);
		
		List<String> list6 = employees.stream().flatMap(
				emp->emp.getSkills()
				.stream().map(skill -> emp.getName() +"- "+skill )).toList();
		System.out.println(list6);
		
//		Unique skills with employee names
//		
		Map<String, List<String>> flatMap = employees.stream()
							.flatMap(emp -> emp.getSkills().stream()
									.map(skill-> Map.entry(skill,emp.getName())))
							.collect(Collectors.groupingBy(Map.Entry::getKey
			 						,Collectors.mapping(Map.Entry::getValue, Collectors.toList())
			 						));

		
		System.out.println(flatMap);
		System.out.println("-------------------------------------------------");
		System.out.println(employees);
		
		
		Map<String,List<String>> list7		= employees.stream()
						.flatMap(emp-> emp.getSkills().stream().map(skill-> Map.entry(skill,emp.getName())))
						.collect(Collectors.groupingBy(Map.Entry::getValue,Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
		System.out.println(list7.toString());
		
		Map<String, List<String>> collect3 = employees.stream().flatMap(emp-> emp.getSkills().stream().map(skill-> Map.entry(skill, emp.getName())))
		.collect(Collectors.groupingBy(Map.Entry::getValue,Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
		
		System.out.println(collect3);
		
//		🔹 6. Real Interview Example (Numbers from Strings)
		
		List<String> strList = Arrays.asList("1,2,3", "4,5", "6,7,8");

		System.out.println(strList);
		
		List<Integer> list8 = strList.stream().flatMap(str -> Arrays.stream(str.split(","))).map(Integer::parseInt).toList();
		System.out.println(list8);
		
		String str = "1,2,3";
		List<Integer> collect4 = Arrays.stream(str.split(",")).map(Integer::parseInt).collect(Collectors.toList());
		System.out.println(collect4);
		
//		Cartesian Product
		
		List<String> list1 = Arrays.asList("A", "B");
		List<Integer> listNum = Arrays.asList(1, 2);
		
		List<String> collect5 = list1.stream().flatMap(s-> listNum.stream().map(i-> s+i)).collect(Collectors.toList());
		System.out.println(collect5);
		
//		Get all even numbers from nested lists
		
		List<List<Integer>> numList = Arrays.asList(
		        Arrays.asList(1, 2, 3),
		        Arrays.asList(4, 5, 6)
		);
		
		List<Integer> list9 = numList.stream().flatMap(Collection::stream).toList();
		System.out.println(list9);
		List<Integer> list10 = list9.stream().filter(i-> i%2==0).toList();
		System.out.println(list10);
		
		Map<String, List<Integer>> collect6 = numList.stream().flatMap(Collection::stream).collect(Collectors.groupingBy(i->(i%2==0)? "Even": "Odd"));
		System.out.println("Even numbers: "+collect6.get("Even"));
		System.out.println("Odd numvers: "+collect6.get("Odd"));
		
		List<String> strNumList = Arrays.asList("1","2","3","4","5","6","7");
		
		Map<String, List<Integer>> collect7 = strNumList.stream().map(Integer::parseInt).collect(Collectors.groupingBy(i-> (i%2==0)?"even":"odd"));
		System.out.println("Even number: "+collect7.get("even"));
		System.out.println("Odd number: "+collect7.get("odd"));
		
		System.out.println(numList);
		
//		🔥 3. Count Total Elements in Nested List
		long count = numList.stream().flatMap(Collection::stream).count();
		System.out.println("Number of elments: "+count);
		long count2 = strNumList.stream().count();
		System.out.println(count2);
		
//		🔥 4. Find Max from Nested List
		
		Integer max = numList.stream().flatMap(Collection::stream).max(Integer::compareTo).get();
		System.out.println(max);
		
		Integer min = numList.stream().flatMap(Collection::stream).min(Integer::compareTo).get();
		System.out.println(min);
		
		System.out.println(employees); 
		
//		Question: Group employees by skill
		
		//		employees.stream()
		//.flatMap(emp -> emp.getSkills().stream()
		//		.map(skill-> Map.entry(skill,emp.getName())))
		//.collect(Collectors.groupingBy(Map.Entry::getKey
		//			,Collectors.mapping(Map.Entry::getValue, Collectors.toList())
		//			));
		
		
		Map<String, List<String>> collect8 = employees.stream().flatMap(emp -> emp.getSkills().stream().map(skills-> Map.entry(skills, emp.getName())))
			.collect(Collectors.groupingBy(Map.Entry::getKey,Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
		System.out.println(collect8);
		
		List<List<Integer>> lsits= Arrays.asList(Arrays.asList(1,2,3,4) , Arrays.asList(4,5,1,2,6,7,8,9));
		
//		🔥 6. Remove Duplicates After Flatten
		
		List<Integer> list11 = lsits.stream().flatMap(Collection::stream).distinct().toList();
		System.out.println(list11);
		
//		Convert Map<String, List<String>> → single list
		
		Map<String, List<String>> map = new HashMap<>();
		map.put("A", Arrays.asList("x", "y"));
		map.put("B", Arrays.asList("z"));
		
		List<String> list12 = map.values().stream().flatMap(Collection::stream).toList();
		System.out.println(list12);
		
		List<String> list13 = map.entrySet().stream().flatMap(entry-> Stream.concat(Stream.of(entry.getKey()), entry.getValue().stream())).sorted().toList();
		System.out.println(list13);
		}		
}
