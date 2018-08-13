package assignment4;
import java.math.BigInteger;

public class RationalNumber {

	int num; //numerator
	int den; //denominator
	
	public RationalNumber(int a, int b) {
		if(a != b)
			num = a;
		else
			System.out.println("Not a Rational number");
		if(b!=0)
			den = b;
		else
			System.out.println("Denominator can't be 0.");
		}

	public static RationalNumber multiply(RationalNumber r1, RationalNumber r2) {
		System.out.printf("(%d/%d) * (%d/%d) = ",r1.num,r1.den,r2.num,r2.den);
		return new RationalNumber(r1.num*r2.num,r1.den*r2.den);
	}

	public static RationalNumber divide(RationalNumber r1, RationalNumber r2) {
		System.out.printf("(%d/%d) / (%d/%d) = ",r1.num,r1.den,r2.num,r2.den);
		return new RationalNumber(r1.num*r2.den,r1.den*r2.num);
	}
	
	public static RationalNumber plus(RationalNumber r1, RationalNumber r2) {
		System.out.printf("(%d/%d) + (%d/%d) = ",r1.num,r1.den,r2.num,r2.den);
		RationalNumber a = new RationalNumber(r1.num*r2.den,r1.den*r2.den);
		RationalNumber b = new RationalNumber(r2.num*r1.den,r2.den*r1.den);
		return new RationalNumber((a.num + b.num),b.den);
	}

	public static RationalNumber minus(RationalNumber r1, RationalNumber r2) {
		System.out.printf("(%d/%d) - (%d/%d) = ",r1.num,r1.den,r2.num,r2.den);
		RationalNumber a = new RationalNumber(r1.num*r2.den,r1.den*r2.den);
		RationalNumber b = new RationalNumber(r2.num*r1.den,r2.den*r1.den);
		return new RationalNumber((a.num - b.num),b.den);
	}
	
	public RationalNumber lowestForm() {
		BigInteger gcd = (BigInteger.valueOf(num)).gcd(BigInteger.valueOf(den));//lowest form already
		if(gcd.intValue() == 1)//returns original rational number for next
			return this;
		return new RationalNumber(num/gcd.intValue(),den/gcd.intValue());
	}
	
	public String toString() {
		return  lowestForm().num+"/"+lowestForm().den+ " = "+String.format("%.2f",(double)num/den);
	}
	

	
	
}
