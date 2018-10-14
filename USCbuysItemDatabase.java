
import java.util.HashMap;

public class USCbuysItemDatabase {
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
	public USCbuysItemDatabase() {
		itemInventory = new HashMap<String, USCbuysItem>();
	}
	
	
	/** 
	 * This method adds the given item to the database. If the 
	 * name associated with the item is the same as an existing 
	 * name in the database, the existing item is replaced by 
	 * the new item passed in.
	 */
	public void addItem(USCbuysItem item) {
		if (itemInventory.containsKey(item.getName())) {
			itemInventory.remove(item.getName());
		}
		itemInventory.put(item.getName(), item);
	}

	
	/** 
	 * This method returns the item associated with the given name 
	 * in the database.  If there is no item in the database with 
	 * the given name, the method returns null.
	 */
	public USCbuysItem getItem(String name) {
		if (itemInventory.containsKey(name)) {
			return itemInventory.get(name);
		}
		return null;
	}
	
	
	/** 
	 * This method removes the item associated with the given name
	 * from the database. It also returns the name of its owner.
	 * 
	 * If there is no item in the database with the given name, then
	 * the database is unchanged after calling this method.
	 */
	public String deleteItem(String name) {
		String ownerName = itemInventory.get(name).getOwner();
		itemInventory.remove(name);
		return ownerName;
	}

	
	/** 
	 * This method returns true if there is an item in the database 
	 * that has the given name. It returns false otherwise.
	 */
	public boolean containsItem(String name) {
		if (itemInventory.containsKey(name)) return true;
		return false;
	}

	/** Private instance variables */
	private HashMap<String,USCbuysItem> itemInventory;
}
