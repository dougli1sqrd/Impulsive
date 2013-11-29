package org.douglass.impulsive.dimensions;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 11/23/13
 * Time: 1:15 AM
 */
public class StringDimension implements Dimension{

    private String unitRepresentation;

    public StringDimension(String representation)   {
        this.unitRepresentation = representation;
    }

    @Override
    public String print() {
        return unitRepresentation;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof StringDimension))   {
            return false;
        }

        StringDimension d = (StringDimension)obj;
        if(d.unitRepresentation == unitRepresentation)  {
            return true;
        }
        return false;
    }
}
