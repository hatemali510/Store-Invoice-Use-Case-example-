package ViewModels;

public class InvoiceViewModel {
	
	
	private String customerName;
	private String price;
	private String date;
	
	
	public InvoiceViewModel(String customerName, String price, String date) {
		this.customerName = customerName;
		this.price = price;
		this.date = date;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
