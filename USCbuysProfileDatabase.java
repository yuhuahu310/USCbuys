

import java.util.HashMap;
import java.util.Iterator;

public class USCbuysProfileDatabase {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
	public USCbuysProfileDatabase() {
		profileInventory = new HashMap<String,USCbuysProfile>();
	}
	
	
	/** 
	 * This method returns the profile associated with the given name 
	 * in the database. If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	public USCbuysProfile getProfile(String name) {
		if (profileInventory.containsKey(name)) {
			return profileInventory.get(name);
		}
		return null;
	}
	
	/** 
	 * This method adds the given profile to the database.  If the 
	 * name associated with the profile is the same as an existing 
	 * name in the database, the existing profile is replaced by 
	 * the new profile passed in.
	 */
	public void addProfile(USCbuysProfile profile) {
		if (profileInventory.containsKey(profile.getName())) {
			profileInventory.remove(profile.getName());
		}
		profileInventory.put(profile.getName(), profile);
	}
	
	/** 
	 * This method removes the profile associated with the given name
	 * from the database. It also returns an iterator over the list of items 
	 * associated with the profile.
	 * 
	 * If there is no profile in the database with the given name, then
	 * the database is unchanged after calling this method.
	 */
	public Iterator<String> deleteProfile(String name) {
			Iterator<String> it = profileInventory.get(name).getItems();
			profileInventory.remove(name);
			return it;
	}
	
	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name.  It returns false otherwise.
	 */
	public boolean containsProfile(String name) {
		if (profileInventory.containsKey(name)) return true;
		return false;
	}
	
	/** Private instance variables */
	private HashMap<String,USCbuysProfile> profileInventory;
	
}
