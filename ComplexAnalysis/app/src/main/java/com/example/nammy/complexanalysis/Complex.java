package com.example.nammy.complexanalysis;

public class Complex {
    public float real, imaginary;
    public Complex(float re, float im) {
        real = re;
        imaginary = im;
    }
    public Complex(double re, double im) {
        real = (float) re;
        imaginary = (float) im;
    }

    public Complex add(Complex a) {
        return new Complex(this.real + a.real, this.imaginary + a.imaginary);
    }
    public Complex multiply(Complex a) {
        return new Complex(this.real*a.real - this.imaginary*a.imaginary,this.real*a.imaginary+this.imaginary*a.real);
    }
    public Complex multiply(float a) {
        return new Complex(this.real * a, this.imaginary * a);
    }
    public Complex divide(Complex a) {
        float denom = a.real*a.real + a.imaginary*a.imaginary;
        if (denom == 0)
            return null;
        Complex numer = this.multiply(a.conjugate());
        return numer.multiply(1.0f / denom);
    }
    public Complex reciprocal() {
        Complex unit = new Complex(1,0);
        return unit.divide(this);
    }

    public Complex conjugate() {
        return new Complex(this.real, -1.0f * this.imaginary);
    }

    @Override
    public String toString() {
        return real + " + " + imaginary + "i";
    }

    public static float atan2(float y, float x) {
        // MAPPED TO 0 to 2Pi
        double tan = Math.atan2(y,x);
        double correct = 0.0;
        if (tan < 0)
            correct = tan + 2 * Math.PI;
        else
            correct = tan;
        return (float) correct;
    }
    public static float Arg(Complex z) {
        if (z.real == 0.0f && z.imaginary == 0.0f) {
            return 0;
        }
        return atan2(z.imaginary, z.real);
    }

    public static float Mod(Complex z) {
        return (float) Math.sqrt(z.real*z.real + z.imaginary * z.imaginary);
    }

    @Override
    public boolean equals(Object obj) {
        if (((Complex) obj).real == this.real && ((Complex) obj).imaginary == this.imaginary)
            return true;
        else
            return false;

    }

}