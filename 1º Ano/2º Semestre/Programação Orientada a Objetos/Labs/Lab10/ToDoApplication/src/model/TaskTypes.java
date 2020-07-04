package Model;

/**
 *
 * @author Capinha
 */
public enum TaskTypes {
    INDIFFERENT, PROJECT, PRESENTATION, TEST, EXAM,
    LABORATORY_ACTIVITY;

    public static TaskTypes getValue(String value) {
        if (value != null) {
            switch (value) {
                case "Indiferente":
                    return INDIFFERENT;
                case "Projecto":
                    return PROJECT;
                case "Apresentação":
                    return PRESENTATION;
                case "Teste":
                    return TEST;
                case "Exame":
                    return EXAM;
                case "Actividade Laboratorial":
                    return LABORATORY_ACTIVITY;
            }
        }
        return PROJECT;
    }

    @Override
    public String toString() {
        switch (this) {
            case INDIFFERENT:
                    return "Indiferente";
            case PROJECT:
                return "Projecto";
            case PRESENTATION:
                return "Apresentação";
            case TEST:
                return "Teste";
            case EXAM:
                return "Exame";
            case LABORATORY_ACTIVITY:
                return "Actividade Laboratorial";
        }
        return "";
    }
}
