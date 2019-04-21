package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;



public class DB {
    static Connection DB_Connection = null; 
    private static DB dbInstace=null;
    String driver = "com.mysql.cj.jdbc.Driver";
    private Statement statement;
    
    private static String DB_name="accounting";
    private static String DB_user="root";
    private static String DB_password="";
	
	
	// constructor 
	private DB() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException { 
		Class.forName(driver).newInstance();
		DB_Connection = DriverManager.getConnection(
			       "jdbc:mysql://localhost:3306/"+DB_name, DB_user, DB_password);
	} 
	
	
	// SET DATABASE PARAMETERS FROM CONFIGURATIONS FILES .
	// USING SET FUNCTIONS 
	
	public static DB get_DB_Instance() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (DB_Connection==null) {
			dbInstace=new DB();
		}
		return dbInstace;
	}
	
	public Connection GetConnect() {
		if(DB_Connection!=null) {
		    return DB_Connection;
		}else {
			return null;
		}
	}
	
	public ResultSet executeDbQuery(String query) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        statement = DB.get_DB_Instance().GetConnect().createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
	}
	public int executeDbUpdateQuery(String query) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        statement = DB.get_DB_Instance().GetConnect().createStatement();
        int res = statement.executeUpdate(query);
        return res;
	}
	   
    public ResultSet getAllFromTable(String table) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		   String Query="select * from "+table;
		   return executeDbQuery(Query);
	}
   
    public ResultSet getRecordFromTable(String table,int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		   String Query="select * from "+table+ " where id="+id;
		   return executeDbQuery(Query);
    }
    public boolean updateRecordsOnTable(String table,List<String> args,List<String> values,String id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	String Query="UPDATE "+table+" SET ";
    	String fields=PrepeareQuery(args, values);
    	Query+=fields+" Where id ="+id;
        statement = DB.get_DB_Instance().GetConnect().createStatement();
        int res=statement.executeUpdate(Query);
        if(res==1) {
    		return true;
    	}else {
    		return false;
    	}
    }
    public boolean DeleteRecord(String tableName,String id ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	String Query="DELETE FROM "+tableName +" Where id ="+id;
    	statement = DB.get_DB_Instance().GetConnect().createStatement();
 		int res=statement.executeUpdate(Query);
 		if(res==1) {
 			return true;
 		}
    	return false ;
    }
    
    public int insertRecord(String tableName,String[] args,String[] values) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	String params=PrepeareParams(args); String Values=PrepeareValues(values);
    	String Query="INSERT INTO "+tableName+" ("+params+") values  ("+Values+")" ;
    	statement = DB.get_DB_Instance().GetConnect().createStatement();
 		int res=statement.executeUpdate(Query,Statement.RETURN_GENERATED_KEYS );
 		if(res==1) {
 			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
 	            if (generatedKeys.next()) {
 	                int id=generatedKeys.getInt(1);
 	                return id;
 	            }else {
 	                throw new SQLException("Creating user failed, no ID obtained.");
 	            }
 	        }
 		}else {
 			return -1;
 		}
    }
    
    public String PrepeareParams(String[] args) {
    	String params="";
    	for(int i=0;i<args.length;i++) {
    		params+=args[i];
    		if(i!=args.length-1) {
    			params+=", ";
    		}
    	}
    	return params;
    }
    
    public String PrepeareValues(String[] values) {
    	String vals="";
    	for(int i=0;i<values.length;i++) {
    		vals+="'"+values[i]+"'";
    		if(i!=values.length-1) {
    			vals+=", ";
    		}
    	}
    	return vals;
    }
    
    public String PrepeareQuery(List<String> args,List<String> values) {
    	String Query="";
    	for(int i=0;i<args.size();i++) {
    		Query+=args.get(i)+" = '"+values.get(i)+"'";
    		if(i!=args.size()-1) {
    			Query+=", ";
    		}
    	}
    	return Query;
    }
	
    public String PrepeareWhereQuery(List<String> args,List<String> values) {
    	String Query=" where ";
    	for(int i=0;i<args.size();i++) {
    		Query+=args.get(i)+" = '"+values.get(i)+"'";
    		if(i!=args.size()-1) {
    			Query+="AND ";
    		}
    	}
    	return Query;
    }
}
