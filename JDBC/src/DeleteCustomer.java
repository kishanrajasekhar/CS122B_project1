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
		String tempStr2 = "DELETE FROM customers WHERE first_name = '" + names[0] + "' and last_name = '" + names[1] + "';";
		System.out.println(tempStr2);	
		select.executeUpdate(tempStr2);
		//since the sales table foreign key cascades upon deletion
		//we do not need to modify anything there
		
	}
	
	public String[] getUserInput(){
		String[] result = new String[2]; 	//result is a string array where the first index is the first name 
											//and the second index is the last name
		
		System.out.println("Enter the first name of the Customer: ");
		String firstName = Driver.chooseStringOption(sc).trim();
		System.out.println("Enter the last name of the Customer: ");
		String lastName = Driver.chooseStringOption(sc).trim();
		result[0] = firstName;
		result[1] = lastName;
		return result;
	} //end getUserInput
	
} //end DeleteCustomer
