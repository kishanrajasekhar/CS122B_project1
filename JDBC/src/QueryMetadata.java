import java.sql.*;
import java.util.ArrayList;

public class QueryMetadata {
	Connection connection;
	
	public QueryMetadata(Connection connection){
		this.connection = connection;
	}
	
	/* Prints the metadata of the database
	 * It prints out the table names and 
	 * the attributes (and its type) in each table */
	public void getMetaData() throws SQLException{
		ArrayList<String> tableNames = new ArrayList<String>();
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet tables = databaseMetaData.getTables(null, null, null, null);
		while(tables.next()){
			String tbl = tables.getString(3);
			System.out.println("Table: " + tbl);
			ResultSet result = databaseMetaData.getColumns(null, null, tbl, null);
			
			while(result.next()){
				System.out.println(result.getString(4) + " (" + JDBCType.valueOf(result.getInt(5)).getName() + ")");
			}
			System.out.println();
		}
	}
}
