package patterns.Statistics;

/**
 * Class of the pattern Strategy and Factory for the statistics
 */
public class StatisticsFactory {
    /**
     * Method to create a Strategy by a type
     * @param type type of strategy wanted
     * @return Strategy created
     */
    public static StrategyStatistics create(String type) {
        switch (type) {
            case "AddedUsers":
                return new StrategyStatisticsAddedUsers();
            case "IncludedUsers":
                return new StrategyStatisticsIncludedUsersByAdded();
            case "UserWithMostRelations":
                return new StrategyStatisticsUserWithMostDirectRelations();
            case "AverageNumberOfRelations":
                return new StrategyStatisticsAvgNumberOfInterests();
            case "Top5UsersWithMostRelations":
                return new StrategyStatisticsTop5UsersWithMostRelations();
            case "Top5UsersWithMostIncludedUsers":
                return new StrategyStatisticsTop5UsersWithMostIncludedUsers();
            default:
                throw new UnsupportedOperationException("Type not supported: " + type);
        }
    }
}
