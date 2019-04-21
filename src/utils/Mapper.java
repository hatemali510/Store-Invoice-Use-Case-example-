package utils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Customer;
import model.Invoice;
import model.Item;

public class Mapper {
	
	public static List<Invoice> ToInvoice(ResultSet result) throws SQLException, ParseException{
		List<Invoice> invoices=new ArrayList<Invoice>();
		while(result.next()) {
			Invoice invoice=new Invoice();
			invoice.setId(result.getInt("id"));
			invoice.setCustomer_id(result.getInt("customer_id"));
			invoice.setTotal_price(result.getDouble("total_price"));
			invoice.setDate(result.getString("date"));
			invoice.setStatus(result.getInt("status"));
			invoices.add(invoice);
		}
		return invoices;
	}

	public static Map<Item, Integer> ToItems(ResultSet recordFromTable) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Map<Item, Integer> itemsTable=new HashMap<>();
		while(recordFromTable.next()){
			int item_id=recordFromTable.getInt("item_id");
			int quantity=recordFromTable.getInt("quantity");
			ResultSet result=DB.get_DB_Instance().getRecordFromTable("item", item_id);
			itemsTable.put(Mapper.toItem(result),quantity);
		}
		return itemsTable;
	}
	
	 public static Item toItem(ResultSet result) throws SQLException{
		Item item=new Item();
		while(result.next()){
			item.setId(result.getInt("id"));
			item.setName(result.getString("name"));
			item.setPrice(result.getDouble("price"));
		}
		return item;
	}
	 
	 
	 public static Customer ToCustomer(ResultSet result) throws SQLException{
		 Customer cus=new Customer();
		 while(result.next()){
			 cus.setId(result.getInt("id"));
			 cus.setName(result.getString("name"));
		 }
		 return cus;
	 }
	
}
