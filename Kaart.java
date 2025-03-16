package ProjektVideoPoker;

import java.util.Objects;

public class Kaart {
    private String mast;
    private String number;

    public String getMast() {
        return mast;
    }

    public String getNumber() {
        return number;
    }

    public Kaart(String mast, String number) {
        this.mast = mast;
        this.number = number;

    }
    public String toString() {
        return mast + number;
    }

    public boolean equals(Object o) {  // Võrdleme kaarte, et vältida duplikaate
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kaart kaart = (Kaart) o;
        return mast.equals(kaart.mast) && number.equals(kaart.number);
    }
    public int hashCode() {  // Hashcode meetod on vajalik listis unikaalsuse tagamiseks
        return Objects.hash(mast, number);
    }
}
