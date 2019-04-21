package utils;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ViewModels.InvoiceViewModel;
import controller.CustomerController;
import controller.InvoiceController;
import model.Invoice;

public class MyInvoiceTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 592031238634382201L;
	private String[] columnNames = {"customer name","price","date"}; 
	private ArrayList<InvoiceViewModel> tableData;
	private String TableName="invoice";
	InvoiceController invoiceManger=new InvoiceController();
	CustomerController customerManager=new CustomerController();
	
	public MyInvoiceTableModel() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException {
		tableData=TableData();
	}
	public ArrayList<InvoiceViewModel> TableData() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException{
		ArrayList<InvoiceViewModel> TemptableData=new ArrayList<>();
		List<Invoice> invoices=invoiceManger.getAll();
		for(int i=0;i<invoices.size();i++){
			String customerName=customerManager.getCustomer(invoices.get(i).getCustomer_id()).getName();
			TemptableData.add(
					new InvoiceViewModel(
						customerName,
						invoices.get(i).getTotal_price().toString(), 
						invoices.get(i).getDate())
			);
		}
		return TemptableData;
	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
		
	}

	@Override
	public int getRowCount() {
		
		int size; 
	      if (tableData == null) { 
	         size = 0; 
	      } 
	      else { 
	         size = tableData.size(); 
	      } 
	      return size; 
	}
	public String getColumnName(int col) { 
	      return columnNames[col]; 
	} 
	@Override
	public Object getValueAt(int row, int col) {
		
		Object temp = null; 
	      if (col == 0) { 
	         temp = tableData.get(row).getCustomerName(); 
	      } 
	      else if (col == 1) { 
	         temp = tableData.get(row).getPrice(); 
	      } 
	      else if (col == 2) { 
	         temp = tableData.get(row).getDate(); 
	      } 
	      return temp; 
	}
	
	public Class getColumnClass(int col) { 
	     return String.class; 
	} 

}
