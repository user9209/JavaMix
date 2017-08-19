/**
 * Copyright (c) 2016 Georg Schmidt (georg.schmidt@gs-sys.de)
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 */
public class RK4Calc {

    public static void main(String[] args) {
        double h = 1;
        double t0 = 0;
        double x0 = 0;

        ValueElement x0e = new ValueElement(x0, t0, h);
        ValueElement x1e = nextRK4(x0e);
        ValueElement x2e = nextRK4(x1e);
        ValueElement x3e = nextRK4(x2e);
        ValueElement x4e = nextRK4(x3e);

        if (Global.defaultFunction && Math.abs(x1e.value - 0.7064d) > 0.01d) {
            throw new RuntimeException("RK4Calc - Test failed");
        }

        System.out.println(x0e);
        System.out.println(x1e);
        System.out.println(x2e);
        System.out.println(x3e);
        System.out.println(x4e);
    }

    public static ValueElement nextRK4(ValueElement curr) {
        double s1 = Global.function(curr.value, curr.t);
        double s2 = Global.function(curr.value + 0.5 * curr.h * s1, curr.t + 0.5 * curr.h);
        double s3 = Global.function(curr.value + 0.5 * curr.h * s2, curr.t + 0.5 * curr.h);
        double s4 = Global.function(curr.value + curr.h * s3, curr.t + curr.h);

        System.out.println("s1 = " + s1 + " s2 = " + s2 + " s3 = " + s3 + " s4 = " + s4);

        double x1 = curr.value + curr.h / 6d * (s1 + 2 * s2 + 2 * s3 + s4);
        return new ValueElement(x1, curr.t + curr.h, curr.h);
    }
}
