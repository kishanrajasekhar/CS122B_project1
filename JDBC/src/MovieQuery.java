import java.sql.*;
import java.util.Scanner;

public class MovieQuery {
	Connection connection; // will use an already initialized connection
	
	/* The connection parameter should already 
	 * be established */
	public MovieQuery(Connection connection){
		this.connection = connection;
	}
	
	/* Prints the movies based on the user's 
	 * input parameters */
	public void searchMovie() throws SQLException{
		Scanner sc = new Scanner(System.in);
		printOptions();
		int option = Driver.chooseIntOption(sc, 1, 4);
		String firstName, lastName, id, query = null;
		switch(option){
		case 1:
			System.out.print("Enter movie id: ");
			id = sc.nextLine();
			query = getMovieQueryFromId(id);
			break;
		case 2:
			System.out.print("Enter star's first name: ");
			firstName = Driver.chooseStringOption(sc).trim();
			query = createFirstNameQuery(firstName);
			break;
		case 3:
			System.out.print("Enter star's last name: ");
			lastName = Driver.chooseStringOption(sc).trim();
			query = createLastNameQuery(lastName);
			break;
		case 4:
			System.out.print("Enter star's first name: ");
			firstName = Driver.chooseStringOption(sc).trim();
			System.out.print("Enter star's last name: ");
			lastName = Driver.chooseStringOption(sc).trim();
			query = createFullNameQuery(firstName, lastName);
			break;
		}
		printMovieQueryData(query);
	}
	
	/* Prints the user's options for input parameters */
	private void printOptions(){
		System.out.println("Select an option (1 to 4):");
		System.out.println("1. Enter movie id");
		System.out.println("2. Enter first name");
		System.out.println("3. Enter last name");
		System.out.println("4. Enter first and last name");
	}
	
	/* Returns the completed query using a star's id */
	private String getMovieQueryFromId(String id){
		return "SELECT * FROM movies WHERE id="+id+";";
	}
	
	/* Returns the completed query using a star's name */
	private String getMovieQueryFromName(String nameQuery){
		return "SELECT * from movies  WHERE id in (SELECT movie_id FROM stars_in_movies WHERE star_id in (SELECT id FROM stars WHERE " + nameQuery + "));";
	}
	
	/* Returns the query based on the stars first name */
	private String createFirstNameQuery(String firstName){
		return getMovieQueryFromName("first_name='"+firstName+"'");
	}
	
	/* Returns the query based on the stars first name */
	private String createLastNameQuery(String lastName){
		return getMovieQueryFromName("last_name='"+lastName+"'");
	}
	
	/* Return the query on both first name and last name */
	private String createFullNameQuery(String firstName, String lastName){
		return getMovieQueryFromName("first_name='"+firstName+"' AND last_name='"+lastName+"'");
	}
	
	/* Prints data about all the movies returned from the query */
	private void printMovieQueryData(String query) throws SQLException{
		Statement select = connection.createStatement();
		ResultSet result = select.executeQuery(query);
		while (result.next()) {
			System.out.println("Id = " + result.getInt(1));
			System.out.println("Title = " + result.getString(2));
			System.out.println("Year = " + result.getInt(3));
			System.out.println("Director = " + result.getString(4));
			System.out.println();
		}
	}
	
	/* Just a test function */
	public void testMovieQuery() throws SQLException{
		String query = getMovieQueryFromId("156005");
		printMovieQueryData(query);
	}
	
}
