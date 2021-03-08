package pt.pa.model;

import java.util.Objects;

public class Local {
    private String name;

    @Override
    public String toString() {
        return "Local{" +
                name +
                '}';
    }

    public String getName() {
        return name;
    }

    public Local(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Local local = (Local) o;
        return Objects.equals(name, local.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
