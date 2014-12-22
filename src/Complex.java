import java.math.BigDecimal;

public class Complex {
	private double re = 0.0;
	private double im = 0.0;

	// create a new object
	public Complex(double real, double imag) {
		re = real;
		im = imag;
	}

	// return string representation Complex object
	public String toString() {
		if (im == 0)
			return re + "";
		if (re == 0)
			return im + "i";
		if (im < 0)
			return re + " - " + (-im) + "i";
		return re + " + " + im + "i";
	}

	@Override
	public boolean equals(Object o) {
		if (o.getClass().equals(this.getClass())) {
			Complex c = (Complex) o;
			if (c.getReal() == re && c.getImag() == im) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public double getReal() {
		return re;
	}

	public double getImag() {
		return im;
	}

	// return new Complex object plus b
	public Complex plus(Complex b) {
		Complex a = this;
		double real = a.re + b.re;
		double imag = a.im + b.im;
		return new Complex(real, imag);
	}

	// return new Complex object minus b
	public Complex minus(Complex b) {
		Complex a = this;
		double real = a.re - b.re;
		double imag = a.im - b.im;
		return new Complex(real, imag);
	}

	// return new Complex object times b
	public Complex times(Complex b) {
		Complex a = this;
		double real = a.re * b.re - a.im * b.im;
		double imag = a.re * b.im + a.im * b.re;
		return new Complex(real, imag);
	}

	// return new Complex object minus b
	public Complex conjugate() {
		return new Complex(re, -im);
	}

	public static Complex[] toComplexArray(Double[] reals) {
		Complex[] result = new Complex[reals.length];
		for (int i = 0; i < reals.length; i++) {
			result[i] = new Complex(reals[i], 0);
		}
		return result;
	}

	private double round(double unrounded, int precision) {
		BigDecimal bd = new BigDecimal(unrounded);
		BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
		return rounded.doubleValue();
	}

	public Complex round(int places) {
		return new Complex(round(re, places), round(im, places));
	}

	public static void main(String[] args) {
		Complex a = new Complex(7.0, 4.0);
		Complex b = new Complex(-2.0, 6.0);

		System.out.println("b + a = " + b.plus(a));
		System.out.println("a - b = " + a.minus(b));
		System.out.println("a * b = " + a.times(b));
		System.out.println("Conj[a] = " + a.conjugate());
		System.out.println("Conj[b] = " + b.conjugate());
	}
}