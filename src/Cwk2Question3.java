import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Cwk2Question3 {
   Complex[] coefficients;
   Complex[] roots;

   private boolean readComplexCoefficients() {
      BufferedReader r;
      String line = null;
      try {
         r = new BufferedReader(new FileReader("input.txt"));
         line = r.readLine();
      } catch (FileNotFoundException e) {
         System.err.println("Input file not found.");
         return false;
      } catch (IOException e) {
         System.err.println("Error reading input file.");
         return false;
      }
      if (line != null) {
         String[] str_coefs = line.split(" ");
         coefficients = new Complex[str_coefs.length];
         roots = new Complex[str_coefs.length];
         for (int i = 0; i < str_coefs.length; i++) {
            try {
               Double re = Double.parseDouble(str_coefs[i].split(",")[0]);
               Double im = Double.parseDouble(str_coefs[i].split(",")[1]);
               coefficients[i] = new Complex(re, im);
            } catch(Exception e) {
               System.err.println("Error parsing input file.");
               return false;
            }
         }
      } else {
         System.err.println("Error reading input file: is the file empty?");
         return false;
      }
      return true;
   }

   private Complex[] doFFT(Complex[] A, int n) {
      if (n == 1) {
         return A;
      } else {
         Complex w0 = new Complex(1, 0);
         Complex wk = new Complex(Math.cos((2 * Math.PI) / n), Math.sin((2 * Math.PI) / n));

         Complex[] A0 = new Complex[n / 2];
         Complex[] A1 = new Complex[n / 2];
         for (int i = 0; i < n / 2; i++) {
            A0[i] = A[2 * i];
            A1[i] = A[2 * i + 1];
         }

         Complex[] y0 = doFFT(A0, n / 2);
         Complex[] y1 = doFFT(A1, n / 2);
         Complex[] y = new Complex[A.length];

         for (int k = 0; k < n / 2; k++) {
            y[k] = y0[k].plus(w0.times(y1[k]));
            y[k + n / 2] = y0[k].minus(w0.times(y1[k]));
            w0 = w0.times(wk);
         }
         return y;
      }
   }

   private Complex[] inverseFFT(Complex[] A, int n) {
      Complex[] conj = new Complex[A.length];
      for (int i = 0; i < A.length; i++) {
         conj[i] = A[i].conjugate();
      }
      conj = doFFT(conj, conj.length);
      for (int i = 0; i < A.length; i++) {
         conj[i] = conj[i].conjugate();
         conj[i] = conj[i].times(new Complex(1.0 / (float) (n), 0));
      }
      return conj;
   }

   private Complex[] roundAll(Complex[] in, int places) {
      Complex[] result = new Complex[in.length];
      for (int i = 0; i < in.length; i++) {
         result[i] = in[i].round(places);
      }
      return result;
   }

   private void printPretty(Complex[] c) {
      for (int i = 0; i < c.length; i++) {
         if (Math.floor(c[i].getReal()) == c[i].getReal() || Math.floor(c[i].getImag()) == c[i].getImag()) {
            if (Math.floor(c[i].getReal()) == c[i].getReal()) {
               System.out.print((int) (c[i].getReal()) + ",");
            } else {
               System.out.print(c[i].getReal() + ",");
            }
            if (Math.floor(c[i].getImag()) == c[i].getImag()) {
               System.out.print((int) (c[i].getImag()) + " ");
            } else {
               System.out.print(c[i].getImag() + " ");
            }
         } else {
            System.out.print(c[i].getReal() + "," + c[i].getImag() + " ");
         }
      }
   }

   public static void main(String[] args) {
      Cwk2Question3 ifft = new Cwk2Question3();
      ifft.run();
   }

   private void run() {
      if (readComplexCoefficients() == true) {
         Complex[] output = inverseFFT(coefficients, coefficients.length);
         printPretty(roundAll(output, 3));
      } else {
         System.out.println(":(");
      }
   }
}
