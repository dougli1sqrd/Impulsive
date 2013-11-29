package org.douglass.impulsive.dimensions;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 11/28/13
 * Time: 12:16 PM
 */
public class UnitException extends RuntimeException {

    public UnitException() {
        super();
    }

    public UnitException(String message) {
        super(message);
    }

    public UnitException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnitException(Throwable cause) {
        super(cause);
    }
}
