import java.sql.*;
import java.util.Scanner;

public class insertCustomer{
	
	Connection connection;
	Scanner sc = new Scanner(System.in);
	
	public insertCustomer(Connection connection){
		this.connection = connection;
	}
	
	public void insertCustomerFunction() throws SQLException{
		/* Insert a customer into database connect if 
		 * their credit card exists in the credit card table
		 * */
		
		//Read in customer name
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name (First Last): ");
		String name = sc.nextLine().trim();
		String first,last;
		
		
		//split into first,last name
		if (name.contains(" ")){
			String[] nameArray = name.split("\\s+");
			first = nameArray[0];
			last  = nameArray[1];
		}
		else{
			first = "";
			last = name;
		}
		
		
		//Execute SQL statement that finds customer's credit card in table
		Statement select = connection.createStatement();
		ResultSet result = select.executeQuery("Select * from creditcards where first_name = '" + first + "' AND last_name = '" + last + "';");
		
	
		//Grab into for insertion
		String firstName = null, lastName = null, expiration = null;
		int id = 0;
		if(result.next()){
			id 			= result.getInt(1);
			firstName 	= result.getString(2);
			lastName 	= result.getString(3);
			expiration	= result.getString(4);
			
			//debugging print statements
			/*
			System.out.println("Id = " + id);
			System.out.println("Name = " +  firstName + " " + lastName);
			System.out.println("Expiration = " + expiration);
			*/
		}
		
		
		//if customer has no credit card, do not add to database
		//I used firstName because id was already initalized
		if(firstName == null){
			System.out.println("Customer first and last name not found, Customer not added to Database");
			return;
		}
		
		
		//insert customer into table
		Statement update = connection.createStatement();
		String queryString = String.format("INSERT INTO customers VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s');", null, firstName, lastName, id, "", "", "");
		int retID = update.executeUpdate(queryString);
		System.out.println("retID = " + retID + "\n");
	}
	
}
