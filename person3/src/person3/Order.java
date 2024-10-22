package person3;
import person1.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Order {
	private Map<String, Integer> productList = new HashMap<String, Integer>();
	private String id = new String();
	private long profit;
	private long totalSellingPrice = 0;
	private long totalPurchasePrice = 0;
	
	public Order() {
		
	}
	
	public Order(String id) {
		this.id = id;
	}
	
	public Order(String id, Map<String, Integer> productList) {
		this.id = id;
		this.productList = productList;
	}
	
	public String getID() {
		return this.id;
	}
	
	public long getProfit() {
		return this.profit;
	}
	
	public Map<String, Integer> getProductList() {
		return this.productList;
	}
	
	public long getSellingPrice() {
		return this.totalSellingPrice;
	}
	
	public long getPurchasePrice() {
		return this.totalPurchasePrice;
	}
	
	private void updateID(String id) {
		this.id = id;
	}
	
	private void updateSellingPrice(long p) {
		this.totalSellingPrice = p;
	}
	
	private void updatePurchasePrice(long p) {
		this.totalPurchasePrice = p;
	}
	
	private void addToCart(String proID, int quantty) {
		Product item = new Product(proID);
		if (this.productList.get(proID) != null) this.productList.put(proID, productList.get(proID) + quantty);
		else this.productList.put(proID, quantty);
		this.totalSellingPrice += item.getSellingPrice() * quantty;
		this.totalPurchasePrice += item.getPurchasePrice() *quantty;
	}
	
	private void removeFromCart(String proID) {
		int quantty = this.productList.get(proID);
		this.addToCart(proID, -quantty);
		this.productList.remove(proID);
	}
	
	private void reduceQuanttyFromCart(String proID, int quantty) {
		if (quantty < this.productList.get(proID))
		this.addToCart(proID, -quantty);
		else {
			this.productList.remove(proID);
		}
	}
	
	private void cancelOrder() {
		this.productList.clear();
		this.totalPurchasePrice = 0;
		this.totalSellingPrice = 0;
	}
	
	private void writeToCSV() {
		
	}
	
	public void getOrder() {
		long totalSellingPrice = 0;
		long totalPurchasePrice = 0;
		Scanner inp = new Scanner(System.in);
		String itemID = new String();
		int quantty;
		
		
		// Add item to cart by itemID
		boolean validInp = false;
		while(!validInp) {
			try {
				while(true) {
					/*
					 * 1. add to cart
					 * 2. remove from card
					 * 3. reduce quantity
					 * 4. end of add to cart
					 * 5. cancel order
					 * */
					int function = inp.nextInt();
					if (function == 1) {
						itemID = inp.nextLine();
						quantty = inp.nextInt();
						this.addToCart(itemID, quantty);
					}
					else if(function == 2) {
						itemID = inp.nextLine();
						this.removeFromCart(itemID);
					}
					else if (function == 3) {
						itemID = inp.nextLine();
					quantty = inp.nextInt();
						this.reduceQuanttyFromCart(itemID, quantty);
					}
					else if (function == 4) {
						break;
					} 
					else if (function == 5) {
						this.cancelOrder(); 
						return;
					}
				} 
			}
			catch (InputMismatchException e) {
				inp.next();
			}
		}
		
		if (productList.isEmpty()) return;
		
		/*Call the paymentProcess is here*/
		Payment payMent = new Payment(totalSellingPrice);
		if (payMent.processPayment()) {
			/*Update orderID as paymentID*/							// Contact Hoang
			
			this.updateID(payMent.getID());
			this.updatePurchasePrice(totalPurchasePrice);
			this.updateSellingPrice(totalSellingPrice);
			
			//Reduce quantity of item in product.csv after getorder ---is this part belong to Son?---
			for (Map.Entry<String, Integer> i : this.productList.entrySet()) {
				Product item = new Product(i.getKey());
				
				item.updateQuantity(-i.getValue()); 				// Contact Nguyet Anh 
			}
			this.saveToCSV();
		
			//Save the data to order.csv
		}
		
		public void saveToCSV() {
			String fileName = "order.csv";
			FileWriter outFile = null;
			try {
				outFile = new FileWriter(fileName, true);
				for (Map.Entry<String, Integer> item : this.productList.entrySet()) {
					outFile.append(String.join(",", this.getID(), item.getKey(), item.getValue().toString()));
					outFile.append("\n");
				}
				System.out.println("CSV file successfully written!");
				outFile.close();
			}
			catch (Exception e) {
				System.err.println("Error writing to file: " + e.getMessage());
	            e.printStackTrace(); 
			}
			finally {
				try {
					if (outFile != null) {
						outFile.flush();
						outFile.close();
					}
				} 
				catch (IOException e) {
					System.err.println("Error closing the file writer: " + e.getMessage());
	                e.printStackTrace();
				}
			}
		}
	}

