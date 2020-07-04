package Model;

/**
 *
 * @author Capinha
 */
public enum PriorityLevel {
    HIGH, MEDIUM, LOW;

    public static PriorityLevel getValue(String value) {
        switch (value) {
            case "Alta":
                return HIGH;
            case "Média":
                return MEDIUM;
            case "Baixa":
                return LOW;
        }
        return LOW;
    }

    public String getColor() {
        switch (this) {
            case HIGH:
                return "#d22e2e";
            case MEDIUM:
                return "#FFC300";
            case LOW:
                return "#298D26";
        }
        return "";
    }

    @Override
    public String toString() {
        switch (this) {
            case HIGH:
                return "Alta";
            case MEDIUM:
                return "Média";
            case LOW:
                return "Baixa";
        }
        return "";
    }

}
