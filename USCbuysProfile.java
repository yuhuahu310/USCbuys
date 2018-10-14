
import java.util.ArrayList;
import java.util.Iterator;

public class USCbuysProfile {
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for
	 * the profile.
	 */
	public USCbuysProfile(String name) {
		profileName = name;
		items = new ArrayList<String>();
	}

	/** This method returns the name associated with the profile. */ 
	public String getName() {
		return profileName;
	}

	/** 
	 * This method adds the named items to this profile's list of 
	 * items. It returns true if the item's name was not already
	 * in the list of items for this profile (and the name is added 
	 * to the list).  The method returns false if the given item name
	 * was already in the list of items for this profile (in which 
	 * case, the given item name is not added to the list of items 
	 * a second time.)
	 */
	public boolean addItem(String item) {
		if (!items.contains(item)) {
			items.add(item);
			return true;
		}
		return false;
	}

	/** 
	 * This method removes the named item from this profile's list
	 * of items.  It returns true if the item's name was in the 
	 * list of items for this profile (and the name was removed from
	 * the list). The method returns false if the given item name 
	 * was not in the list of items for this profile (in which case,
	 * the given item name could not be removed.)
	 */
	public boolean removeItem(String item) {
		if (items.contains(item)) {
			items.remove(item);
			return true;
		}
		return false;
	}

	/** 
	 * This method returns an iterator over the list of items 
	 * associated with the profile.
	 */ 
	public Iterator<String> getItems() {
		Iterator<String> it = items.iterator();
		return it;
	}
	
	public boolean hasItem(String name) {
		Iterator<String> iterator = getItems();
		while (iterator.hasNext()) {
			if (iterator.next().equals(name)) return true;
		}
		return false;
	}
	
	/** 
	 * This method returns a string representation of the profile.  
	 * This string is of the form: "name: list of items", 
	 * where name is set accordingly and the list of 
	 * items is a comma separated list of the names of all of the 
	 * items in this profile.
	 * 
	 * For example, in a profile with name "Alice" who has items 
	 * Essential Calculus and C++, this method would return the string:
	 * "Alice: Essential Calculus, C++"
	 */ 
	public String toString() {
		String itemOutput = "";
		for(int i=0;i<items.size();i++) {
			if (i != items.size()-1) {
				itemOutput += items.get(i)+", ";
			}
			else {
				itemOutput += items.get(i);
			}
		}
		String output = profileName + ": " + itemOutput;
		return output;
	}
	
	/** Private instance variables */
	private String profileName;
	private ArrayList<String> items;
}
