/**
 * Copyright (c) 2016 Georg Schmidt (georg.schmidt@gs-sys.de)
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 */
public class HeunCalc {

    public static void main(String[] args) {
        double h = 1;
        double t0 = 0;
        double x0 = 0;

        ValueElement x0e = new ValueElement(x0,t0, h);
        ValueElement x1e = nextHeun(x0e);
        ValueElement x2e = nextHeun(x1e);
        ValueElement x3e = nextHeun(x2e);
        ValueElement x4e = nextHeun(x3e);

        if(Global.defaultFunction && Math.abs(x1e.value - 0.6609d) > 0.01d)
        {
            throw new RuntimeException("HeunCalc - Test failed");
        }

        System.out.println(x0e);
        System.out.println(x1e);
        System.out.println(x2e);
        System.out.println(x3e);
        System.out.println(x4e);

    }

    public static ValueElement nextHeun(ValueElement curr)
    {
        double s1 = Global.function(curr.value,curr.t);
        double s2 = Global.function(curr.value + curr.h * s1,curr.t + curr.h);

        System.out.println("s1 = " + s1 + " s2 = " + s2);

        double x1 = curr.value + curr.h / 2d *(s1 + s2);
        return new ValueElement(x1, curr.t + curr.h, curr.h);
    }
}
