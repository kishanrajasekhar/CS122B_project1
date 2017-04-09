import java.sql.*;
import java.util.Scanner;


public class processSQL {
	Connection connection;
	Scanner sc = new Scanner(System.in);
	
	
	public processSQL(Connection connection){
		this.connection = connection;
	}

	
	public void processSQLQuery(){
		
		//Grab user input and extract user command
		//different commands require different calls to the connection
		System.out.println("Enter a SQL command(SELECT, UPDATE, INSERT, DELETE): ");
		String query = sc.nextLine().trim();
		String[] args = query.split("\\s+"); //remove whitespace
		String command = args[0];
		ResultSet result = null;
		ResultSetMetaData metaData;
		int returnID;
		
		try {
			//create connection 
			Statement select = connection.createStatement();
			
			if(command.equals("SELECT")){
				//execute query
				result = select.executeQuery(query);
				
				//print all columns returned
				metaData = result.getMetaData();
				int columns = metaData.getColumnCount();
				while (result.next()) {
				    for (int i = 1; i <= columns; ++i) {
				        if (i > 1) System.out.print(",  ");
				        String columnValue = result.getString(i);
				        System.out.print(columnValue + " " + metaData.getColumnName(i));
				    }
				    System.out.println("");
				}
				
			}
			else if (command.equals("UPDATE") ||
					command.equals("INSERT") || command.equals("DELETE") ){
				returnID = select.executeUpdate(query); 
				System.out.println("Number of rows modified: " + returnID);
			}
			else{
				System.out.println("Error: SQL command does not match one of the above");
			}			
		} catch (SQLException e) {
			System.out.println("Error: There was an error executing your SQL command: ");
			System.out.println(e.toString());
			System.out.println();
		}
		
		
	} //end processSQLQuery
	
	public void displayResults(ResultSet result, String command ){
		
	}
	
} //end class processSQL
