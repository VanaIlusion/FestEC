package com.wind.latte.recycler;

/**
 * Created by theWind on 2017/8/17.
 */

public class IAutoValue_RgbValue extends RgbValue {
    private final int red;
    private final int green;
    private final int blue;

    IAutoValue_RgbValue(
            int red,
            int green,
            int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public int red() {
        return red;
    }

    @Override
    public int green() {
        return green;
    }

    @Override
    public int blue() {
        return blue;
    }

    @Override
    public String toString() {
        return "RgbValue{"
                + "red=" + red + ", "
                + "green=" + green + ", "
                + "blue=" + blue
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof RgbValue) {
            RgbValue that = (RgbValue) o;
            return (this.red == that.red())
                    && (this.green == that.green())
                    && (this.blue == that.blue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.red;
        h *= 1000003;
        h ^= this.green;
        h *= 1000003;
        h ^= this.blue;
        return h;
    }

}
