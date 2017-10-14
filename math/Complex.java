package library.math;

public class Complex {
	final double r;
	final double i;
	
	public Complex(double r, double i)
	{
		this.r = r;
		this.i = i;
	}
	
	public Complex add(Complex b)
	{
		return new Complex(r + b.r, i + b.i);
	}
	
	public Complex subtract(Complex b)
	{
		return new Complex(r - b.r, i - b.i);
	}
	
	public Complex multiply(Complex b)
	{
		double real = this.r * b.r - this.i * b.i;
		double imag = this.r * b.i + this.i * b.r;
		
		return new Complex(real, imag);
	}
	
	public Complex scale(double alpha)
	{
		return new Complex(this.r * alpha, this.i * alpha);
	}
}
