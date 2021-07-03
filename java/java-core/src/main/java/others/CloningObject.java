package others;

public class CloningObject {

	static class Employee{
		private int employeeNumber;
		public int getEmployeeNumber() {
			return employeeNumber;
		}
		public void setEmployeeNumber(int employeeNumber) {
			this.employeeNumber = employeeNumber;
		}
		public Employee(Employee e){
			this(e.getEmployeeNumber());
		}
		public Employee(int employeeNumber){
			this.employeeNumber = employeeNumber;
		}
	}
	
	public static void main(String[] args) {
		Employee e1 = new Employee(1);
		System.out.println("print1->" + e1.getEmployeeNumber());
		Employee e2 = e1;
		e2.setEmployeeNumber(2);
		System.out.println("print2.1->" + e1.getEmployeeNumber());
		System.out.println("print2.2->" + e2.getEmployeeNumber());
		Employee e3 = new Employee(e1);
		e3.setEmployeeNumber(3);
		System.out.println("print3.1->" + e1.getEmployeeNumber());
		System.out.println("print3.2->" + e2.getEmployeeNumber());
		System.out.println("print3.3->" + e3.getEmployeeNumber());
		
	}
	
}
