/**
 * Copyright (c) 2016 Georg Schmidt (georg.schmidt@gs-sys.de)
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 */
public class ExplEulerCalc {

    public static void main(String[] args) {
        double h = 1;
        double t0 = 0;
        double x0 = 0;

        RK4Element x0e = new RK4Element(x0,x0,t0, h);
        RK4Element x1e = nextExplEuler(x0e);
        RK4Element x2e = nextExplEuler(x1e);
        RK4Element x3e = nextExplEuler(x2e);
        RK4Element x4e = nextExplEuler(x3e);

        if(Global.defaultFunction && Math.abs(x1e.currValue - 0.9450d) > 0.01d)
        {
            throw new RuntimeException("ExplEulerCalc - Test failed");
        }

        System.out.println(x0e);
        System.out.println(x1e);
        System.out.println(x2e);
        System.out.println(x3e);
        System.out.println(x4e);

    }

    public static RK4Element nextExplEuler(RK4Element curr)
    {
        double x1 = curr.currValue + Global.function(curr.currValue,curr.currT);
        return new RK4Element(x1,curr.currValue, curr.currT + curr.currH, curr.currH);
    }
}
