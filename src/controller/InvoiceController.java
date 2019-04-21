package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import utils.DB;
import utils.Mapper;
import model.Invoice;
import model.Item;

public class InvoiceController {
	private String Invoice_table_name="invoice";
	private String Invoice_Items_table_name="inv_items";
	
	
	// add invoice 
	//Map<ItemId,ItemQuantity>
	public boolean addInvoice(int customer_id,Map<Integer,Integer> items) throws Throwable{
		int  invoice_id=DB.get_DB_Instance().insertRecord(Invoice_table_name, InvoiceController.InvoiceAtts(), this.genetrateInvoiceValues(customer_id));
		if(invoice_id!=-1) {
			double total_price=0;
			for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
				total_price+=insert_invoice_item(entry.getKey(), invoice_id, entry.getValue());
			}
			return this.updateIvoiceItemTable(invoice_id, total_price);
		}else {
			return false;
		}
	}
	
	
	// delete invoice 
	public boolean deleteInvoice(int invoiceId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return DB.get_DB_Instance().DeleteRecord(Invoice_table_name, String.valueOf(invoiceId));
	}
	//-----------------------------------------------------------------------------
	// update Invoice function 
	
	public boolean AddItemsToInvoice(List< Pair<Integer, Integer>> addItems,int invoice_id) throws Throwable{
		Double total_price=0.0;
		for (Pair <Integer,Integer> temp : addItems) 
        { 
             total_price+=insert_invoice_item(temp.left, invoice_id, temp.right);
        } 
		ResultSet resultSet=DB.get_DB_Instance().getRecordFromTable(Invoice_table_name, invoice_id);
		List<Invoice> invoices=Mapper.ToInvoice(resultSet);
		total_price+=invoices.get(0).getTotal_price();
		return this.updateIvoiceItemTable(invoice_id, total_price);
	}
	
	// removed item ids 
	public boolean RemoveItemsFromInvoice(List<Integer> removedItemsIds,int invoice_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException{
		Double total_price=0.0;
		for(int i=0;i<removedItemsIds.size();i++){
			total_price+=(remove_invoice_item(removedItemsIds.get(i), invoice_id));
		}
		ResultSet resultSet=DB.get_DB_Instance().getRecordFromTable(Invoice_table_name, invoice_id);
		List<Invoice> invoices=Mapper.ToInvoice(resultSet);
		total_price=invoices.get(0).getTotal_price()-total_price;

		return this.updateIvoiceItemTable(invoice_id, total_price);
	}
	
	
	// update total_price and status after the insert in first time 
	private boolean updateIvoiceItemTable(int inv_id,double total_price) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ArrayList<String> updatedParams=new ArrayList<>(); ArrayList<String> updatedValues=new ArrayList<>();
		updatedParams.add("total_price"); updatedParams.add("status");
		updatedValues.add(String.valueOf(total_price)); updatedValues.add(String.valueOf(1));
		return DB.get_DB_Instance().updateRecordsOnTable(Invoice_table_name, updatedParams, updatedValues, Integer.toString(inv_id));
				
	}
	
	// end of update function 
	//---------------------------------------------------------------------------------
	
	// read functions 
	public List<Invoice> getAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException{
		List<Invoice> invoices=new ArrayList<>();
		ResultSet result=DB.get_DB_Instance().getAllFromTable(Invoice_table_name);
		invoices=Mapper.ToInvoice(result);
		return invoices;
	}
	
	public Map<Item,Integer> GetInvoiceItems(int InvoiceId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		String query="select * from "+Invoice_Items_table_name+" where inv_id ="+InvoiceId;
		return Mapper.ToItems(DB.get_DB_Instance().executeDbQuery(query));	
	}
	
	//------------------------------------------------------------------------------------
	// private methods 
	
	
	private double  insert_invoice_item(Integer item,int invoice_id,int quantity) throws Throwable, Throwable, Throwable, Throwable{
		int invoice_item_result=DB.get_DB_Instance().insertRecord(Invoice_Items_table_name,
				InvoiceController.generateItem_invoiceAttrs(), 
				this.genetrateInvoice_items_Values(invoice_id, item, quantity));
			if(invoice_item_result!=-1){
				ItemController itemController=new ItemController();
				return itemController.getItem(item).getPrice()* quantity;
			}else{
	            throw new SQLException("error insert items in the invoice");     

			}
	}
	
	
	private Double remove_invoice_item(int invoice_item_id, int invoice_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ItemController itemController=new ItemController();
		ResultSet result=DB.get_DB_Instance().getRecordFromTable(Invoice_Items_table_name, invoice_item_id);
		int item_id=0;
		int quantity=0;
		while(result.next()){
			item_id=result.getInt("item_id");
			quantity=result.getInt("quantity");
			break;
		}
		Item item=itemController.getItem(item_id);
		// delete from table 
		String query="DELETE FROM inv_items WHERE inv_id = "+invoice_id+" AND id = "+invoice_item_id;
		DB.get_DB_Instance().executeDbUpdateQuery(query);
		return item.getPrice()*quantity;
	}
	
	
	
	
	
	private String[] genetrateInvoiceValues(int customerId){
		String pattern = "yyyy-MM-dd HH:mm:ssZ";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		return new String[] {""+customerId,""+0.0,date};
	}
	
	
	private String[] genetrateInvoice_items_Values(int invoice_id,int item_id,int quantity){
		return new String[] {""+invoice_id,""+item_id,""+quantity};
	}
	
	// ----------------------------------------------------------------------------
		// static method for database attribures 
		
	static String[] generateItem_invoiceAttrs(){
		String[] atts={"inv_id","item_id","quantity"};
		return atts;
	}
	static String[] InvoiceAtts(){
		return  new String[]{"customer_id","total_price","date"};
	}
		
		/// end of static methods 
}
