
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import acm.program.Program;

public class USCbuys extends Program 
					implements USCbuysConstants {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// for export as a jar file
	public static void main (String[] args) {
		new USCbuys().start(args);
	}
	
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		//Initialize canvas
		canvas = new USCbuysCanvas();
		add(canvas);
		
		//Initialize profile and item database
		profileDatabase = new USCbuysProfileDatabase();
		itemDatabase = new USCbuysItemDatabase();
		
		//Initialize current profile and currently displayed item name
		currentProfile = null;
		currentItemName = null;
		
		//Initialize NORTH
		add(new JLabel("user_name "),NORTH);
		userNameField = new JTextField(TEXT_FIELD_SIZE);
		add(userNameField,NORTH);
		addButton = new JButton("Add");
		add(addButton,NORTH);
		deleteButton = new JButton("Delete");
		add(deleteButton,NORTH);
		profileLookupButton = new JButton("Lookup");
		add(profileLookupButton,NORTH);
		purchaseButton = new JButton("Purchase");
		add(purchaseButton,NORTH);
		
		//Initialize WEST
		add(new JLabel("item_name "),WEST);
		deleteItemField = new JTextField(TEXT_FIELD_SIZE);
		add(deleteItemField,WEST);
		deleteItemButton = new JButton("Delete");
		add(deleteItemButton,WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		add(new JLabel("item_name "),WEST);
		addItemField = new JTextField(TEXT_FIELD_SIZE);
		add(addItemField,WEST);
		add(new JLabel("item_price "),WEST);
		addItemPriceField = new JTextField(TEXT_FIELD_SIZE);
		add(addItemPriceField,WEST);
		addItemButton = new JButton("Add");
		add(addItemButton,WEST);
		
		//Initialize SOUTH
		add(new JLabel("item_name "),SOUTH);
		itemNameField = new JTextField(TEXT_FIELD_SIZE);
		add(itemNameField,SOUTH);
		itemLookupButton = new JButton("Lookup");
		add(itemLookupButton,SOUTH);
		returnButton = new JButton("Return to Current Profile");
		add(returnButton,SOUTH);
		
		//Add action listener
		addActionListeners();
		itemNameField.addActionListener(this);
		deleteItemField.addActionListener(this);
    }
	
	/**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used
     */
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == addButton) {
    		isLookedUp = false;
			if (!userNameField.getText().equals("")) {
				if (profileDatabase.containsProfile(userNameField.getText())) {
					currentProfile = profileDatabase.getProfile(userNameField.getText());
					canvas.displayProfile(currentProfile);
					canvas.showMessage("A profile with the name "+userNameField.getText()+
							" already exists");
				}
				else {
					USCbuysProfile newProfile = new USCbuysProfile(userNameField.getText());
					profileDatabase.addProfile(newProfile);
					currentProfile = newProfile;
					canvas.displayProfile(currentProfile);
					canvas.showMessage("New profile created");
				}
			}
		}
    	if (e.getSource() == deleteButton) {
    		isLookedUp = false;
			if (!userNameField.getText().equals("")) {
				if (profileDatabase.containsProfile(userNameField.getText())) {
					Iterator<String> it = 
						profileDatabase.deleteProfile(userNameField.getText());
					while (it.hasNext()) {
						String itemName = it.next();
						if (itemDatabase.containsItem(itemName)) {
							itemDatabase.deleteItem(itemName);
						}
					}
					canvas.removeAll();
					canvas.showMessage("Profile of "+userNameField.getText()+" deleted");
				}
				else {
					canvas.removeAll();
					canvas.showMessage("A Profile with the name "+userNameField.getText()+
							" does not exist");
				}
				currentProfile = null;
			}
		}
    	if (e.getSource() == profileLookupButton) {
    		isLookedUp = false;
			if (!userNameField.getText().equals("")) {
				if (profileDatabase.containsProfile(userNameField.getText())) {
					currentProfile = profileDatabase.getProfile(userNameField.getText());
					canvas.displayProfile(currentProfile);
					canvas.showMessage("Displaying "+userNameField.getText());
				}
				else {
					currentProfile = null;
					canvas.removeAll();
					canvas.showMessage("A Profile with the name "+userNameField.getText()+
							" does not exist");
				}
			}
		}
    	if (e.getSource() == addItemButton) {
    		isLookedUp = false;
			if (!addItemField.getText().equals("") &&
					!addItemPriceField.getText().equals("")) {
				if (currentProfile == null) {
					canvas.showMessage("Please select a profile before adding items");
				}
				else {
					if (!itemDatabase.containsItem(addItemField.getText())) {
						double price = Double.parseDouble(addItemPriceField.getText());
						USCbuysItem newItem = new USCbuysItem(addItemField.getText(),
								price, currentProfile.getName());
						currentProfile.addItem(addItemField.getText());
						itemDatabase.addItem(newItem);
						canvas.displayProfile(currentProfile);
						canvas.showMessage(addItemField.getText()+" with price "+
								price+" is added by "+currentProfile.getName()+" and now is available for sale");
					}
					else {
						canvas.showMessage(addItemField.getText()+" already exists");
					}
				}
			}
		}
    	if (e.getSource() == deleteItemField | e.getSource() == deleteItemButton) {
    		isLookedUp = false;
			if (!deleteItemField.getText().equals("")) {
				if (currentProfile == null) {
					canvas.removeAll();
					canvas.showMessage("Please select a profile before deleting items");
				}
				else {
					if (itemDatabase.containsItem(deleteItemField.getText())) {
						if (currentProfile.hasItem(deleteItemField.getText())) {
							currentProfile.removeItem(deleteItemField.getText());
							itemDatabase.deleteItem(deleteItemField.getText());
							canvas.displayProfile(currentProfile);
							canvas.showMessage(deleteItemField.getText()+" is deleted by "+currentProfile.getName()
									+" and now is unavailable for sale");
						}
						else {
							canvas.removeAll();
							canvas.showMessage(deleteItemField.getText()+" doesn't exist in "+
									currentProfile.getName()+"'s directory");
						}
					}
					else {
						canvas.removeAll();
						canvas.showMessage(deleteItemField.getText()+" doesn't exist");
					}
				}
			}
		}
    	if (e.getSource() == itemLookupButton) {
			if (!itemNameField.getText().equals("")) {
				if (itemDatabase.containsItem(itemNameField.getText())) {
					isLookedUp = true;
					currentItemName = itemNameField.getText();
					canvas.displayItem(itemDatabase.getItem(itemNameField.getText()));
					canvas.showMessage("Displaying "+itemNameField.getText());
				}
				else {
					currentItemName = null;
					canvas.removeAll();
					canvas.showMessage(itemNameField.getText()+" doesn't exist");
				}
			}
		}
    	if (e.getSource() == purchaseButton) {
			if (!userNameField.getText().equals("") &&
					isLookedUp) {
				if (!profileDatabase.containsProfile(userNameField.getText())) {
					canvas.showMessage("invalid user name");
				}
				else {
					if (currentItemName != null) {
						profileDatabase.getProfile(itemDatabase.getItem(currentItemName).
					    		getOwner()).removeItem(currentItemName);
						itemDatabase.deleteItem(currentItemName);
						canvas.removeAll();
						canvas.showMessage(currentItemName+" is purchased by "+userNameField.getText()+
								" and is now unavailable for sale");
						currentItemName = null;
					}
					else {
						canvas.removeAll();
						canvas.showMessage("The item you are purchasing doesn't exist");
					}
				}
			}
		}
    	if (e.getSource() == returnButton) {
			if (currentProfile == null) {
				canvas.removeAll();
				canvas.showMessage("Current Profile is null");
			}
			else {
				canvas.displayProfile(currentProfile);
			}
		}
	}
	
	
	/** Private instance variable */
    private JTextField userNameField;
    private JTextField itemNameField;
    private JTextField deleteItemField;
    private JTextField addItemField;
    private JTextField addItemPriceField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton profileLookupButton;
    private JButton itemLookupButton;
    private JButton returnButton;
    private JButton deleteItemButton;
    private JButton addItemButton;
    private JButton purchaseButton;
    private boolean isLookedUp = false;
    
    private USCbuysProfile currentProfile;
    private String currentItemName;
    private USCbuysProfileDatabase profileDatabase;
    private USCbuysItemDatabase itemDatabase;
    private USCbuysCanvas canvas;
}
