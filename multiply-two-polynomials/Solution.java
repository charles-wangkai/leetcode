// https://cp-algorithms.com/algebra/fft.html
// https://leetcode.com/problems/multiply-two-polynomials/solutions/6742784/java-simple-fft-solution-o-n-m-log-n-m-328ms/

import java.util.stream.IntStream;

class Solution {
  public long[] multiply(int[] poly1, int[] poly2) {
    int n = 1;
    while (n < poly1.length + poly2.length) {
      n <<= 1;
    }

    Complex[] a =
        IntStream.range(0, n)
            .mapToObj(i -> new Complex((i < poly1.length) ? poly1[i] : 0, 0))
            .toArray(Complex[]::new);
    Complex[] b =
        IntStream.range(0, n)
            .mapToObj(i -> new Complex((i < poly2.length) ? poly2[i] : 0, 0))
            .toArray(Complex[]::new);

    fft(a, false);
    fft(b, false);
    for (int i = 0; i < a.length; ++i) {
      a[i] = a[i].multiply(b[i]);
    }
    fft(a, true);

    return IntStream.range(0, poly1.length + poly2.length - 1)
        .mapToLong(i -> Math.round(a[i].real))
        .toArray();
  }

  void fft(Complex[] a, boolean inverse) {
    int n = a.length;

    for (int i = 1, j = 0; i < n; ++i) {
      int bit = n >> 1;
      while ((j & bit) != 0) {
        j ^= bit;
        bit >>= 1;
      }
      j ^= bit;

      if (i < j) {
        Complex temp = a[i];
        a[i] = a[j];
        a[j] = temp;
      }
    }

    for (int len = 2; len <= n; len <<= 1) {
      double ang = 2 * Math.PI / len * (inverse ? -1 : 1);
      Complex wlen = new Complex(Math.cos(ang), Math.sin(ang));
      for (int i = 0; i < n; i += len) {
        Complex w = new Complex(1, 0);
        for (int j = 0; j < len / 2; ++j) {
          Complex u = a[i + j];
          Complex v = a[i + j + len / 2].multiply(w);
          a[i + j] = u.add(v);
          a[i + j + len / 2] = u.subtract(v);
          w = w.multiply(wlen);
        }
      }
    }

    if (inverse) {
      for (int i = 0; i < a.length; ++i) {
        a[i] = a[i].divide(a.length);
      }
    }
  }
}

class Complex {
  double real;
  double imag;

  Complex(double real, double imag) {
    this.real = real;
    this.imag = imag;
  }

  Complex add(Complex other) {
    return new Complex(real + other.real, imag + other.imag);
  }

  Complex subtract(Complex other) {
    return new Complex(real - other.real, imag - other.imag);
  }

  Complex multiply(Complex other) {
    return new Complex(
        real * other.real - imag * other.imag, real * other.imag + imag * other.real);
  }

  Complex divide(double x) {
    return new Complex(real / x, imag / x);
  }
}
