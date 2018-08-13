package assignment5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ShoppingCart {
    private String customerName;
    private String date;
    private Inventory[] itemsToBuy;
    private ArrayList<Item> shoppingCartItems;
    public ShoppingCart(String customerName, String date, 
            Inventory[] items)
    {
        this.shoppingCartItems = new ArrayList<Item>();
        this.itemsToBuy = new Inventory[items.length];
        this.itemsToBuy = items;
        this.customerName = customerName;
        this.date = date;

    }
    public Inventory searchInventory(String itemName)
    {
        Inventory test = new Inventory(itemName);
        Arrays.sort(itemsToBuy, new InventoryComparator());
        int index = Arrays.binarySearch(itemsToBuy, test, 
                new InventoryComparator());
		return null;

    }
    public void addItem(String itemName, int quantity)
    {
        try
        {
            Inventory itemRequired = searchInventory(itemName);
            if(quantity <= itemRequired.getQuantity()) {
				if(itemRequired.getQuantity() == 0)
					System.out.println("None of " + itemName + " is available.");
				else
				{

				    shoppingCartItems.add(new Item(itemName, itemRequired.getPrice(), quantity));
				    itemRequired.setQuantity(itemRequired.getQuantity() - quantity);
				    System.out.println("Item " + itemName + " successfuly added to inventory.");
				}
			} else {
                System.out.println("Can only purchase " + itemRequired.getQuantity() 
                        + " of " + itemName + ".");
                shoppingCartItems.add(new Item(itemName, itemRequired.getPrice(), quantity));
                itemRequired.setQuantity(0);//none left
            }
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            System.err.println("Message: " + ex.getMessage());
        }
    }
    public void removeItem(String itemName, int quantity)
    {
        try
        {
            Inventory itemRequired = searchInventory(itemName);//need this to add back
            //into the quantity
            Item itemToRemove = null;
            for (Iterator<Item> iterator = shoppingCartItems.iterator(); iterator.hasNext();) {
				Item item = iterator.next();
				if(item.getName().equalsIgnoreCase(itemName))
                {
                    itemToRemove = item;
                }
			}
            if(itemToRemove == null)
				return;
           
            if(quantity <= itemToRemove.getQuantity()) {
                shoppingCartItems.remove(itemToRemove);
                itemToRemove.setQuantity(itemToRemove.getQuantity() - quantity);
                if (itemToRemove.getQuantity() == 0)
					return;
                shoppingCartItems.add(itemToRemove);
                itemRequired.setQuantity(quantity);
                System.out.println(quantity + " removed from " + itemName + ".");
            } else {
                System.out.println("Can only remove " + itemToRemove.getQuantity()
                        + "of " + itemName + ".");
                shoppingCartItems.remove(itemToRemove);
                itemRequired.setQuantity(quantity);
            }
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            System.err.println("Message: " + ex.getMessage());
        }
    }
    public void viewCart()
    {
        System.out.println(date + " Name: " + customerName);
        for(int i = 0; i < shoppingCartItems.size(); i++)
        {
            System.out.println(shoppingCartItems.get(i).toString());
        }
    }
    public void sortByPrice()
    {
        shoppingCartItems.sort(new ShoppingCartComparator());
    }
   
}
class InventoryComparator implements Comparator<Inventory>
{
    @Override
    public int compare(Inventory a, Inventory b)
    {
        int returnValue = a.getItemName().compareToIgnoreCase(b.getItemName());
        return returnValue;
    }
}
class ShoppingCartComparator implements Comparator<Item>
{
    @Override
    public int compare(Item a, Item b)
    {
        return a.getPrice()*a.getQuantity() < b.getPrice()*b.getQuantity() ? -1 
                : a.getPrice()*a.getQuantity() == b.getPrice()*b.getQuantity() 
                    ? 0 : 1;
    }
    
}