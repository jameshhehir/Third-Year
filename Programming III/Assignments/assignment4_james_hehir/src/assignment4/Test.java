package assignment4;


public class Test {

	public static void main(String[] args) {

		 Ball[] values = Ball.values();
		for (Ball b : values) {
			System.out.printf("%s %d g %d mm %n",
			 b , b.diameter(), b.weight());
		}

			 System.out.printf("%nThe circumference of a %s ball is %.2f mm",
			 Ball.GOLF , Ball.GOLF.getCircumference()); 
			 
			 System.out.printf("%nThe volume of a %s is %.2f mm^3 %n%n",
			 Ball.BASEBALL , Ball.BASEBALL.getVolume()); 
			 
			 RationalNumber r = new RationalNumber(15,40);
				RationalNumber r1 = new RationalNumber((int)Ball.BASEBALL.weight(),(int)Ball.BASKETBALL.weight());
				RationalNumber r2 = new RationalNumber((int)Ball.FOOTBALL.weight(),(int)Ball.GOLF.weight());
				RationalNumber r3 = new RationalNumber((int)Ball.POOL.diameter(),(int)Ball.SOFTBALL.diameter());
				RationalNumber r4 = new RationalNumber((int)Ball.TENNIS.diameter(),(int)Ball.VOLLEYBALL.diameter());
				
				System.out.println(r.num+"/"+r.den+" = "+ r.toString());
				System.out.println(RationalNumber.plus(r1,r2).toString());
				System.out.println(RationalNumber.minus(r2,r3).toString());
				System.out.println(RationalNumber.multiply(r3,r4).toString());
				System.out.println(RationalNumber.divide(r4,r1).toString());
				
			 
	}
	 
		




}


