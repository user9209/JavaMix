/*
 * Copyright (c) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 */
public class Global {

    public static final boolean defaultFunction = true;

    public static double function(double x, double t) {
        return Math.cos(4d / 3 * t + 1d / 3) + 0.5 * x;
    }
}
