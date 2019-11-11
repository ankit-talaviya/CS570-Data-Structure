public class Complexity {
	
	//------Method 1------A method that has time complexity O(n^2).
	 public static void method1(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
		System.out.println("The time complexity O(n^2) is: " + counter);
	 }
	 
	//------Method 2------A method that has time complexity O(n^3).
	 public static void method2(int n) {
		 int counter = 0;
		 for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
					for (int k = 0; k < n; k++) {
						System.out.println("Operation " + counter);
						counter++;
					}
				}
		 	}
		 System.out.println("The time complexity O(n^3) is: " + counter);
	 }
	 
	//------Method 3------A method that has time complexity O(logn).
	 public static void method3(int n) {
		 int counter = 0;
		 for (int i = 1; i < n; i = i * 2) {
			 System.out.println("Operation " + counter);
			 counter++;
		 }
		 System.out.println("The time complexity O(logn) is: " + counter);
	 }
	 
	//------Method 4------A method that has time complexity O(nlogn).
	 public static void method4(int n) {
		 int counter = 0;
		 for (int i = 1; i <= n; i++) {
			    for(int j = 1; j < n; j = j * 2) {
			    	System.out.println("Operation " + counter);
			    	counter++;
			    }
		}
		 System.out.println("The time complexity O(nlogn) is: " + counter);
	 }
	 
	//------Method 5------A method that has time complexity O(loglogn).
	 public static void method5(int n) {
		 int counter = 0;
		 for (double i = 2; i < n; i = i * i) {
			 System.out.println("Operation " + counter);
			 counter++;
		}
		 System.out.println("The time complexity O(loglogn) is: " + counter);
	 }
	
	//------Method 6------A method that has time complexity O(2^n).
	 static int total = 0;
	 public static int method6(int n) {
		 if(n == 0) {
			 total++;
			 System.out.println("Operation " + total);
			 return total;
		 }
		 else if(n == 1) {
			 System.out.println("Operation " + total);
			 total++;
			 System.out.println("Operation " + total);
			 total++;
			 
			 return total;
		 }
		 method6(n-1);
		 method6(n-1);
		 return total;
	 }
	 
	 public static void main(String[] args) {
		 
		 System.out.println("------------Method 1------------");
		 Complexity.method1(2);
		 System.out.println("------------Method 2------------");
		 Complexity.method2(2);
		 System.out.println("------------Method 3------------");
		 Complexity.method3(32);
		 System.out.println("------------Method 4------------");
		 Complexity.method4(4);
		 System.out.println("------------Method 5------------");
		 Complexity.method5(256);
		 System.out.println("------------Method 6------------");
		 Complexity.method6(3);
		 System.out.println("The time complexity O(2^n) is: " + total);
	 }
}