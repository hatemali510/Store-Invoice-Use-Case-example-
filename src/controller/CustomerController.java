package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;
import utils.DB;
import utils.Mapper;

public class CustomerController {
	
	private String TableName="customer";
	
	public boolean insertCustomer(String name,String phone,String address) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		int res=DB.get_DB_Instance().insertRecord(TableName, CustomerAtts(), CustomerValues(name,phone,address));
		if(res!=-1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Customer getCustomer(int id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		ResultSet res=DB.get_DB_Instance().getRecordFromTable(TableName, id);
		return Mapper.ToCustomer(res);
	}
	
	// Customer 
	public boolean UpdateCustomerData(String name,String phone,String address,int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ArrayList<String> updatedParams=new ArrayList<>(); ArrayList<String> updatedValues=new ArrayList<>();
		if(name!=""){
			updatedParams.add("name");
			updatedValues.add(name);
		}
		if(phone!=""){
			updatedParams.add("phone");
			updatedValues.add(phone);
		}
		if(address!=""){
			updatedParams.add("address");
			updatedValues.add(address);
		}
		return DB.get_DB_Instance().updateRecordsOnTable(TableName, updatedParams, updatedParams, String.valueOf(id));
	}
	
	static String[] CustomerValues(String name,String phone,String address){
		return new String[]{name,phone,address};
	}
	static String[] CustomerAtts(){
		return  new String[]{"name","phone","address"};
	}
}
