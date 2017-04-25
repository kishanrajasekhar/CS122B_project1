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
		
		//Read in credit card ID
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your credit card ID: ");
		String cc_id = sc.nextLine().trim();
		
		//Execute SQL statement that finds customer's credit card in table
		Statement select = connection.createStatement();
		ResultSet result = select.executeQuery("Select * from creditcards where id = '" + cc_id + "';");
	
		//Grab into for insertion
		String id = null, firstName = null, lastName = null, expiration = null;
		if(result.next()){
			id 			= result.getString(1);
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
		if(id == null){
			System.out.println("Credit Card not found, Customer not added to Database");
			System.out.println("Enter a correct credit card!");
			return;
		}
		
		
		//insert customer into table
		Statement update = connection.createStatement();
		String queryString = String.format("INSERT INTO customers VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s');", null, firstName, lastName, id, "", "", "");
		System.out.println(queryString);
		int retID = update.executeUpdate(queryString);
		System.out.println("retID = " + retID + "\n");
	}

	
}
