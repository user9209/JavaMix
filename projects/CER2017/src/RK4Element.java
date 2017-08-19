/**
 * Copyright (c) 2016 Georg Schmidt (georg.schmidt@gs-sys.de)
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 */
public class RK4Element {
    double currT;
    double currValue;
    double currH;
    double formValue;

    public RK4Element(double currValue, double formValue, double currT,  double currH) {
        this.currT = currT;
        this.currValue = currValue;
        this.currH = currH;
        this.formValue = formValue;
    }

    @Override
    public String toString()
    {
        return "@ " + currT + " = " + currValue + " with h = " + currH + " after " + formValue;
    }
}
