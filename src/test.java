import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import controller.CustomerController;
import controller.InvoiceController;
import controller.ItemController;
import model.Invoice;
import model.Item;


public class test {

	public static void main(String[] args) throws Throwable {
		CustomerController customermanager=new CustomerController();
		
	}
}
//		controller.deleteInvoice(7);
//		controller.deleteInvoice(8);
//
//		
////		
//		ItemController itemcontroller=new ItemController();
//		itemcontroller.getItem(1);
//		Map<Integer, Integer> items=new HashMap<>();
//		items.put(1, 5);
//		items.put(2, 5);
//		items.put(3, 5);
//
//		InvoiceController invoiceController=new InvoiceController();
//		try {
//			invoiceController.addInvoice(24, items);
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		Map<Integer, Integer> items2=new HashMap<Integer,Integer>();
//		items2.put(1, 3);
//		items2.put(2, 3);
//		System.out.println(items2.toString());
//		controller.AddItemsToInvoice(, 11);

//		List<Integer> ids=new ArrayList<>();
//		ids.add(38);
//		controller.RemoveItemsFromInvoice(ids, 11);
//		controller.deleteInvoice(9);
//		InvoiceController invoiceController=new InvoiceController();
//		try {
//			List<Pair<Integer, Integer>> items=new ArrayList<>();
//			items.add(new Pair<Integer, Integer>(3, 3));
//			items.add(new Pair<Integer, Integer>(1, 3));
//			invoiceController.AddItemsToInvoice(items, 11);
//			List<Invoice> invoices=new ArrayList<Invoice>();
//			invoices=invoiceController.getAll();
//			for(int i=0;i<invoices.size();i++){
//				System.out.println(invoices.get(i).toString());
//				int invoice_id=invoices.get(i).getId();
//				System.out.println("with item : ");
//				for (Map.Entry<Item, Integer> entry : invoiceController.GetInvoiceItems(invoice_id).entrySet()) {
//					System.out.println(entry.getKey().toString()+ " with quantity "+ entry.getValue());
//				}
//			}
//
//			
////			
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}




// insert template 


//Item item1 =new Item(2,"egg",10.5);
//Item item2 =new Item(1,"milk",25.25);
//Map<Item, Integer> items=new HashMap<>();
//items.put(item1, 2);
//items.put(item2, 4);
//
//InvoiceController invoiceController=new InvoiceController();
//try {
//	invoiceController.addInvoice(24, items);
//} catch (Throwable e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}


// selelct all invoices with details 

//List<Invoice> invoices=new ArrayList<Invoice>();
//invoices=controller.getAll();
//for(int i=0;i<invoices.size();i++){
//	System.out.println(invoices.get(i).toString());
//	int invoice_id=invoices.get(i).getId();
//	System.out.println("with item : ");
//	for (Map.Entry<Item, Integer> entry : controller.GetInvoiceItems(invoice_id).entrySet()) {
//		System.out.println(entry.getKey().toString()+ " with quantity "+ entry.getValue());
//	}
//	
//	
//
//}


// remove items from invoice

//List<Integer> items=new ArrayList<>();
//items.add(1);
//items.add(2);
//int invoice_id=9;
//invoiceController.RemoveItemsFromInvoice(items, invoice_id);