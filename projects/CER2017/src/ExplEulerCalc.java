/*
 * Copyright (c) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 */
public class ExplEulerCalc {

    public static void main(String[] args) {
        double h = 1;
        double t0 = 0;
        double x0 = 0;

        ValueElement x0e = new ValueElement(x0, t0, h);
        ValueElement x1e = nextExplEuler(x0e);
        ValueElement x2e = nextExplEuler(x1e);
        ValueElement x3e = nextExplEuler(x2e);
        ValueElement x4e = nextExplEuler(x3e);

        if (Global.defaultFunction && Math.abs(x1e.value - 0.9450d) > 0.01d) {
            throw new RuntimeException("ExplEulerCalc - Test failed");
        }

        System.out.println(x0e);
        System.out.println(x1e);
        System.out.println(x2e);
        System.out.println(x3e);
        System.out.println(x4e);

    }

    public static ValueElement nextExplEuler(ValueElement curr) {
        double x1 = curr.value + Global.function(curr.value, curr.t);
        return new ValueElement(x1, curr.t + curr.h, curr.h);
    }
}
