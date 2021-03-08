package patterns.factory;

import patterns.mvc.model.SocialNetwork;

/**
 * Concrete implementation of the ...
 *
 * Concrete products returned by this factory.
 *
 */
public class ExportFactory {
    public static Strategy create(String type) {
        switch(type.toLowerCase()) {
            case "json":
                return new StrategyJson();
            case "bin":
                return new StrategySerialization();
            default:
                throw new UnsupportedOperationException("Type not supported: " + type);
        }
    }
}
