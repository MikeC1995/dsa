public class Cwk2Question1 {
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