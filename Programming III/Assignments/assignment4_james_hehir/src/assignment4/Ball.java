package assignment4;
import java.lang.Math;

public enum Ball {
	BASEBALL(149, 73),
    BASKETBALL(624, 239),
    FOOTBALL(450 , 216),
    GOLF(46, 43),
    POOL(156, 60),
    SOFTBALL(180, 97),
    TENNIS(59, 64),
    VOLLEYBALL(260, 218);
  
    private final int weight; //in grams
    private final int diameter; //in millimeters
    Ball(int weight, int diameter) {
    this.weight = weight;
    this.diameter = diameter; 
}
    public int weight() { return weight; }
    public int diameter() { return diameter; } 
    
    public double radius() {
    	return (double) diameter/2; //gets radius for volume formula
    }
    
    public double getCircumference() {
    	 return Math.PI*diameter; //circumference formula
    	 }
    
    public double getVolume() {
    	return (4 * (Math.PI * Math.pow(radius(),3)))/3; //volume formula
    }
}