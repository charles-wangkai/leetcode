class Solution {
  public String complexNumberMultiply(String num1, String num2) {
    return Complex.multiply(new Complex(num1), new Complex(num2)).toString();
  }
}

class Complex {
  int real;
  int imaginary;

  Complex(int real, int imaginary) {
    this.real = real;
    this.imaginary = imaginary;
  }

  Complex(String s) {
    String[] fields = s.split("\\+");
    real = Integer.parseInt(fields[0]);
    imaginary = Integer.parseInt(fields[1].substring(0, fields[1].length() - 1));
  }

  @Override
  public String toString() {
    return String.format("%d+%di", real, imaginary);
  }

  static Complex multiply(Complex c1, Complex c2) {
    return new Complex(
        c1.real * c2.real - c1.imaginary * c2.imaginary,
        c1.real * c2.imaginary + c1.imaginary * c2.real);
  }
}
