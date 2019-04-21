package model;

import java.sql.Date;

public class Invoice {
	private int id;
	private int customer_id;
	private Double total_price;
	private String date;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public Double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String toString(){
		String valid;
		if(status==1){
			valid="paid";
		}else{
			valid="not paid ";
		}
		return "invoice number = "+id+" , customer number = "+customer_id +" , price ="+ total_price +" , date ="+ date+ " , status ="+valid;  
		
	}

}
