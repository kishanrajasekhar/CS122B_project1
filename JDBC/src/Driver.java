import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		MyJDBCProgram jdbc = new MyJDBCProgram();
//		InsertStar insertStar = new InsertStar();
		
		while(true){
			try{
				System.out.println("Enter 1 to login or 0 to exit the program");
				int option = chooseIntOption(sc, 0, 1);
				if(option == 1){
					System.out.print("Enter your username: ");
					String username = sc.next();
					System.out.print("Enter your password: ");
					String password = sc.next();
					System.out.println("Logging in...\n");
					jdbc.login(username, password);	
					while(option != 7){
						printMenuOptions();
						option = chooseIntOption(sc, 1,7);
						switch(option){
						case 1:
							jdbc.queryMovie();
							break;
						case 2:
							System.out.println("insertNewStar function");
							jdbc.insertStar();
							break;
						case 3:
							System.out.println("insertCustomer function");
							break;
						case 4:
							System.out.println("deleteCustomer function");
							jdbc.deleteCustomer();
							break;
						case 5:
							System.out.println("getMetadata function");
							break;
						case 6:
							System.out.println("processSQL function");
							break;
						default:
							System.out.println("Invalid selection");
							break;
						}
					}
				}else{
					System.out.println("Program exited. Thank you, have a nice day fam :) ");
					break;
				}
			}
			catch(InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e){
				System.out.println("Connection Error or Login Error");
				e.printStackTrace();
			}
		}
	}
	
	
	//Helper function to ensure the correct integer value was inputed
	public static int chooseIntOption(Scanner sc, int start, int finish){
		int result = Integer.MIN_VALUE;
		while(true){
			try{
				result = Integer.parseInt(sc.next());
				if(result>=start && result<=finish)
					return result;
			}catch(NumberFormatException e){
				System.out.println("Please input an integer");
				sc.next();
			}
		}
	}
	
	public static void printMenuOptions(){
		System.out.println("Select an option:");
		System.out.println("1. Find a movie");
		System.out.println("2. Insert a new star");
		System.out.println("3. Insert a customer");
		System.out.println("4. Delete a customer");
		System.out.println("5. Get database metadata");
		System.out.println("6. Enter SQL command");
		System.out.println("7. Exit the menu");	
		System.out.println("Input a number from 1 to 7");
	}
}
