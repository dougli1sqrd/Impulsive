package org.douglass.impulsive.dimensions;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 11/28/13
 * Time: 11:41 AM
 */
public interface Measure {

    double getAmount();

    Dimension getDimension();

    Measure add(Measure m);

    Measure multiply(double coefficient);

    String toString();
}
