package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DB;
import utils.Mapper;
import model.Item;

public class ItemController {
	// add 
	// update 
	// delete
	// read
	private String ItemTable="item";
	
	public Item getItem(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ResultSet result=DB.get_DB_Instance().getRecordFromTable(ItemTable, id);
		return Mapper.toItem(result);
	}
	
	public Boolean InsertItem(String name,Double price ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
		int result=DB.get_DB_Instance().insertRecord(ItemTable, ItemAtts(), ItemValues(name, price));
		if(result!=-1){
			return true;
		}
		return false;
	}
	
	public boolean UpdateItemName(String name,int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ArrayList<String> updatedParams=new ArrayList<>(); ArrayList<String> updatedValues=new ArrayList<>();
		updatedParams.add("name");
		updatedValues.add(name);
		if(DB.get_DB_Instance().updateRecordsOnTable(ItemTable, updatedParams, updatedValues, String.valueOf(id))){
			return true;
		}else{
			return false;
		}
	}
	public boolean UpdateItemPrice(Double price,int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ArrayList<String> updatedParams=new ArrayList<>(); ArrayList<String> updatedValues=new ArrayList<>();
		updatedParams.add("name");
		updatedValues.add(String.valueOf(price));
		if(DB.get_DB_Instance().updateRecordsOnTable(ItemTable, updatedParams, updatedValues, String.valueOf(id))){
			return true;
		}else{
			return false;
		}
	}
	static String[] ItemValues(String name,Double price){
		return new String[]{name,""+price};
	}
	static String[] ItemAtts(){
		return  new String[]{"name","price"};
	}
}
