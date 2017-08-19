/**
 * Copyright (c) 2016 Georg Schmidt (georg.schmidt@gs-sys.de)
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
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
    public String toString()
    {
        return "@ " + t + " = " + value + " with h = " + h;
    }
}
