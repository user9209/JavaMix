/*
 * Copyright (c) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 */
public class ValueElement {
    double t;
    double value;
    double h;

    public ValueElement(double value, double t, double h) {
        this.t = t;
        this.value = value;
        this.h = h;
    }

    @Override
    public String toString() {
        return "@ " + t + " = " + value + " with h = " + h;
    }
}
