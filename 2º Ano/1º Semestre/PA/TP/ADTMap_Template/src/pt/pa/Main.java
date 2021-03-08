package pt.pa;

import pt.pa.adts.MainMenu;
import pt.pa.adts.Map;
import pt.pa.adts.MapBST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int[] numbers = {5, 1, 4, 3, 7, 4, 8, 9, 1, 4, 6, 4, 7, 6, 9, 5, 3, 6, 8, 4, 6, 9};

        //Map<Integer, Integer> uniqueCount = new MapList<>();
        Map<Integer, Integer> uniqueCount = new MapBST<>();

        for (int num : numbers) {
            if (uniqueCount.containsKey(num)) {
                int curCount = uniqueCount.get(num);
                uniqueCount.put(num, curCount + 1);
            } else {
                uniqueCount.put(num, 1);
            }
        }

        System.out.println(uniqueCount);

        //TODO: 1. show only unique numbers
        List<Integer> uniqueList = new ArrayList<>(uniqueCount.keys());
        for (int key : uniqueList) {
            System.out.println(key);
        }

        //TODO: 2. show only (sorted) unique numbers and how many times they occur
        //Collections.sort(uniqueList);
        for (int key : uniqueList) {
            System.out.println(key + " -> " + uniqueCount.get(key));
        }

        // Remover chave 5
        uniqueCount.remove(5);
        System.out.println(uniqueCount);
        /*
        MapBST of size = 8:
        │           ┌── {key=9, value=3
        │       ┌── {key=8, value=2
        │   ┌── {key=7, value=2
        │   │
        └── {key=6, value=4
            │   ┌── {key=4, value=5
            │   │   └── {key=3, value=2
            └── {key=1, value=2
         */

        ArrayList<MainMenu> menu = new ArrayList<>();
        menu.add(new MainMenu("Bacon & Cheese Hamburger", 790));
        menu.add(new MainMenu("Chicken Salad with Grilled Chicken", 350));
        menu.add(new MainMenu("French Fries (small)", 320));
        menu.add(new MainMenu("Onion Rings (small)", 320));

        Map<MainMenu, Integer> menuBST = new MapBST<>();
        for (MainMenu item : menu) {
            menuBST.put(item, item.getCalories());
        }

        System.out.println(menuBST);
    }

}