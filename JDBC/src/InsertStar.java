import java.sql.*;
import java.util.Scanner;

public class InsertStar
{
	Connection connection;
	Scanner sc = new Scanner(System.in);
	
	public InsertStar(Connection connection){
		this.connection = connection;
	}
	
	public void insertNewStar() throws SQLException{
		String[] names = getUserInput();
		Statement select = connection.createStatement();
		
		//Create query in String format 
		String tempStr = "INSERT INTO stars VALUES(null, '" + names[0] + "','" + names[1] + "', null, null);";
		
		//Needs to be Update instead of Query because we are modifying the query
		select.executeUpdate(tempStr); 
			
	}
	
	public String[] getUserInput(){
		String[] result = new String[2]; 	//result is a string array where the first index is the first name 
											//and the second index is the last name
		
		//If the star has a single name, add it as his last_name and assign an empty string ("") to first_name
		printOptions();
		int option = Driver.chooseIntOption(sc, 1, 2);
		if (option == 1){
			System.out.println("Enter the last name of the star: ");
			String lastName = Driver.chooseStringOption(sc).trim();
			result[0] = "";
			result[1] = lastName;
		} else if (option == 2){
			System.out.println("Enter the first name of the star: ");
			String firstName = Driver.chooseStringOption(sc).trim();
			System.out.println("Enter the last name of the star: ");
			String lastName = Driver.chooseStringOption(sc).trim();
			result[0] = firstName;
			result[1] = lastName;
		} 
		return result;		
	}
	
	/* Prints the user's options for input parameters */
	private void printOptions(){
		System.out.println("Select an option (1 or 2):");
		System.out.println("1. Enter last name");
		System.out.println("2. Enter first and last name");
	}
}
