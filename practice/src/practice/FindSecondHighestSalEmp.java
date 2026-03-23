package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindSecondHighestSalEmp {

	public static void main(String[] args) {
		   List<Employee> employees = Arrays.asList(
	                new Employee(1, "A", "Dev", 50000),
	                new Employee(2, "B", "QA", 60000),
	                new Employee(3, "C", "Dev", 70000),
	                new Employee(4, "D", "Manager", 70000),
	                new Employee(5, "E", "HR", 60000),
	                new Employee(6,"F","Dev",50000)
	        );
		   
		   Double secondHigSal = employees.stream().map(Employee::getSalary).distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
		   
		   List<Employee> collect = employees.stream().filter(emp-> emp.getSalary()==secondHigSal).collect(Collectors.toList());
		   System.out.println(collect);
		   
		   
		   List<Employee> collect2 = employees.stream().
				   filter(emp->  emp.getSalary() == employees.stream()
				   .map(Employee::getSalary).sorted(Comparator.reverseOrder()).distinct()
				   .skip(1).findFirst().orElse(Double.MIN_VALUE)).collect(Collectors.toList());
		   System.out.println(collect2);
		   
		   // get employee whos designation is dev
		   
		   List<Employee> collect3 = employees.stream().filter(emp-> emp.getDesignation()=="Dev").collect(Collectors.toList());
		   System.out.println(collect3);
		   
		   //get employee whos designation is dev and among them second highest sal emp
		   
		   List<Employee> collect4 = 
		   employees.stream().filter(e-> e.getDesignation().equals("Dev") && e.getSalary() ==
				   employees.stream().filter(emp-> emp.getDesignation().equals("Dev")).map(Employee::getSalary).distinct()
				   						.sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(Double.MIN_NORMAL)).collect(Collectors.toList());
		   System.out.println(collect4);
		   
		   Map<String, List<Employee>> collect5 = employees.stream().collect(Collectors.groupingBy(Employee::getDesignation));		   
		   
		   System.out.println(collect5);
		   
		   collect5.forEach((department,empList)->{
			   System.out.println("Department : "+department);
			   empList.forEach(emp-> System.out.println(emp));
			   System.out.println("--------------------");
		   });
}
}
