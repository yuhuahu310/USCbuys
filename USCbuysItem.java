
public class USCbuysItem {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for
	 * the item.
	 */
	public USCbuysItem(String name, double price, String owner) {
		itemName = name;
		itemPrice = price;
		itemOwner = owner;
	}

	/** This method returns the name associated with the item. */ 
	public String getName() {
		return itemName;
	}
	
	/** This method sets the name associated with the item. */ 
	public void setName(String name) {
		itemName = name;
	}

	/** This method returns the price associated with the item. */ 
	public double getPrice() {
		return itemPrice;
	}
	
	/** This method sets the price associated with the item. */ 
	public void setPrice(double price) {
		itemPrice = price;
	}
	
	/** This method returns the owner name associated with the item. */ 
	public String getOwner() {
		return itemOwner;
	}
	
	/** This method sets the owner name associated with the item. */ 
	public void setOwner(String owner) {
		itemOwner = owner;
	}
	
	/** 
	 * This method returns a string representation of the item.  
	 * This string is of the form: "itemName: price, owner", 
	 * where name is set accordingly, and price and owner are separated
	 * by a comma.
	 * 
	 * For example, in a item with name "C++" that has price 
	 * 25 dollars and belongs to Alice, this method would return the string:
	 * "C++: 25, Alice"
	 */ 
	public String toString() {
		String output = itemName+": "+itemPrice+", "+itemOwner;
		return output;
	}
	
	// returns a string representation of the item price
	public String priceToString() {
		String output = ""+itemPrice;
		return output;
	}
	
	/** Private instance variables */
	private String itemName;
	private String itemOwner;
	private double itemPrice;
}
