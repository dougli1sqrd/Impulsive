package org.douglass.impulsive.dimensions;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 11/23/13
 * Time: 1:34 AM
 */
public class Power implements Measure {

    private Dimension units;

    private double amount;

    public static final Power ZERO_POWER = new Power(0, "W");

    public Power(double amount, String units)    {
        this.amount = amount;
        this.units = new StringDimension(units);
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public Dimension getDimension() {
        return units;
    }

    @Override
    public Power add(Measure p)   {
        if(p.getDimension().equals(units))  {
            return new Power(p.getAmount()+amount, units.print());
        }
        throw new UnitException("Cannot add Measure with units of "+p.getDimension().print()+" to Measure with units of "+units.print());
    }

    @Override
    public Power multiply(double coefficient) {
        return new Power(coefficient*amount, units.print());
    }

    @Override
    public boolean isGreaterThan(Measure m) {
        return amount > m.getAmount();
    }

    @Override
    public boolean isLessThan(Measure m) {
        return amount < m.getAmount();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Power)) {
            return false;
        }
        Power p = (Power) obj;
        return p.getAmount() == amount && p.getDimension().equals(units);
    }

    @Override
    public String toString() {
        return amount+" "+units;
    }
}
