package assignment5;

import java.io.Serializable;

public class Item implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemName;
    private float price;
    private int quantity;
    public Item(String itemName, float price, int quantity)
    {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName()
    {
        return itemName;
    }
    public float getPrice()
    {
        return price;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int newQuantity)
    {
        quantity = newQuantity;
    }
    @Override
    public String toString()
    {
        return quantity + "\t" + itemName + "\t" + "€" + price*quantity;
    }
}