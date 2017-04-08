import java.sql.*;
import java.util.Scanner;

public class DeleteCustomer
{
	Connection connection;
	Scanner sc = new Scanner(System.in);
	
	public DeleteCustomer(Connection connection){
		this.connection = connection;
	}
	
	public void deleteCustomer() throws SQLException{
		String[] names = getUserInput();
		Statement select = connection.createStatement();
		
		//Create query in String format 
		String tempStr = "DELETE FROM sales WHERE customer_id in (SELECT id FROM customers "
			+ "WHERE first_name = '" + names[0] + "' and last_name = '" + names[1] + "');\n";
		System.out.println(tempStr);	
		
		//Needs to be Update instead of Query because we are modifying the query
		select.executeUpdate(tempStr); 
		
		//Create query in String format 
		String tempStr2 = "DELETE FROM customers WHERE first_name = '" + names[0] + "' and last_name = '" + names[1] + "';\n";
		System.out.println(tempStr2);	
		select.executeUpdate(tempStr2);
		
	}
	
	public String[] getUserInput(){
		String[] result = new String[2]; 	//result is a string array where the first index is the first name 
											//and the second index is the last name
		
		System.out.println("Enter the first name of the Customer: ");
		String firstName = sc.next();
		System.out.println("Enter the last name of the Customer: ");
		String lastName = sc.next();
		result[0] = firstName;
		result[1] = lastName;
		return result;
		
		
	}
}
