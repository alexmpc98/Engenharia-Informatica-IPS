package pt.pa.adts;

public class MainMenu implements Comparable<MainMenu> {
    private String menuItem;
    private Integer calories;

    public MainMenu(String menuItem, Integer calories) {
        this.menuItem = menuItem;
        this.calories = calories;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public Integer getCalories() {
        return calories;
    }

    @Override
    public int compareTo(MainMenu o) {
        if (this.menuItem.equals(o.menuItem)) return 0;
        else if (this.calories.equals(o.calories) && this.menuItem.length() == o.menuItem.length())
            return -1;
        else if (this.calories.equals(o.calories))
            return this.menuItem.length() - o.menuItem.length();
        else return this.calories - o.calories;
    }

    @Override
    public String toString() {
        return menuItem;
    }
}
