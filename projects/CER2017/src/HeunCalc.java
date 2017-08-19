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

        RK4Element x0e = new RK4Element(x0,x0,t0, h);
        RK4Element x1e = nextHeun(x0e);
        RK4Element x2e = nextHeun(x1e);
        RK4Element x3e = nextHeun(x2e);
        RK4Element x4e = nextHeun(x3e);

        if(Global.defaultFunction && Math.abs(x1e.currValue - 0.6609d) > 0.01d)
        {
            throw new RuntimeException("HeunCalc - Test failed");
        }

        System.out.println(x0e);
        System.out.println(x1e);
        System.out.println(x2e);
        System.out.println(x3e);
        System.out.println(x4e);

    }

    public static RK4Element nextHeun(RK4Element curr)
    {
        double s1 = Global.function(curr.currValue,curr.currT);
        double s2 = Global.function(curr.currValue + curr.currH * s1,curr.currT + curr.currH);

        System.out.println("s1 = " + s1 + " s2 = " + s2);

        double x1 = curr.currValue + curr.currH / 2d *(s1 + s2);
        return new RK4Element(x1,curr.currValue, curr.currT + curr.currH, curr.currH);
    }
}
