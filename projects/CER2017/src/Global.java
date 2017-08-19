/**
 * Copyright (c) 2016 Georg Schmidt (georg.schmidt@gs-sys.de)
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 */
public class Global {

    public static final boolean defaultFunction = true;

    public static double function(double x, double t) {
        return Math.cos(4d / 3 * t + 1d / 3) + 0.5 * x;
    }
}
