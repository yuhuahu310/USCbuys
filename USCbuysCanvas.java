import java.awt.Color;
import java.util.Iterator;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;

public class USCbuysCanvas extends GCanvas 
					implements USCbuysConstants{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public USCbuysCanvas() {

	}

	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas. Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		if (appMessage != null) remove(appMessage);
		appMessage = new GLabel(msg);
		appMessage.setFont(MESSAGE_FONT);
		add (appMessage,(APPLICATION_WIDTH-appMessage.getWidth())/2.0,
				APPLICATION_HEIGHT-BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, and a list of the user's items
	 * set available for sale
	 */
	public void displayProfile(USCbuysProfile profile) {
		removeAll();
		
		// Name
		GLabel name = new GLabel(profile.getName());
		name.setColor(Color.BLUE);
		name.setFont(PROFILE_NAME_FONT);
		add(name,LEFT_MARGIN,TOP_MARGIN+name.getAscent());
		
		// Items
		GLabel items = new GLabel("Items Available For Sale:");
		items.setFont(PROFILE_ITEM_LABEL_FONT);
		add(items,APPLICATION_WIDTH/2.0,TOP_MARGIN+name.getAscent()+IMAGE_MARGIN);
		Iterator<String> iterator = profile.getItems();
		int count = 1;
		while (iterator.hasNext()) {
			GLabel itemName = new GLabel(iterator.next());
			itemName.setFont(PROFILE_ITEMLIST_FONT);
			add(itemName,APPLICATION_WIDTH/2.0,TOP_MARGIN+name.getAscent()+IMAGE_MARGIN+
					count*(ITEM_MARGIN+itemName.getAscent()));
			count++;
		}
	}
	
	// Displays the item that is being looked up
	public void displayItem(USCbuysItem item) {
		removeAll();
		
		//Name
		GLabel itemA = new GLabel(item.getName());
		itemA.setColor(Color.BLUE);
		itemA.setFont(PROFILE_NAME_FONT);
		add(itemA, LEFT_MARGIN, TOP_MARGIN + itemA.getAscent());
		
		//Price
		GLabel price = new GLabel(item.priceToString());
		price.setFont(PROFILE_NAME_FONT);
		add(price,LEFT_MARGIN, TOP_MARGIN + itemA.getAscent()+IMAGE_MARGIN+
					IMAGE_HEIGHT+STATUS_MARGIN);
	}

	/** Private instance variables */
	private GLabel appMessage;
	
}
