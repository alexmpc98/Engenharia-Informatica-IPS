import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Museum {
    HashMap<WorkOfArt, Room> museumArtCollection;
    
    public Museum() {
        this.museumArtCollection = new HashMap<WorkOfArt, Room>();
    }

    public void addToMuseumCollection(WorkOfArt workOfArt, Room room) {
        if (museumArtCollection.get(workOfArt) == null) {
              museumArtCollection.put(workOfArt, room);
        }
    }

    public void removeFromMuseumCollection(WorkOfArt workOfArt) {
        museumArtCollection.remove(workOfArt);
    }
    
    public ArrayList<WorkOfArt> listArtInRoom(Room room) {
        String concatStr = "";
        ArrayList<WorkOfArt> workOfArt = new ArrayList<>();
        for (Map.Entry<WorkOfArt, Room> entry : museumArtCollection.entrySet()) { 
             if (entry.getValue().equals(room)) {
                 workOfArt.add(entry.getKey());
             }
        }
        return workOfArt;
    }
    
    public String printAllByRoom() {
        String concatStr = "";
        ArrayList<Room> rooms = new ArrayList<>();
        for (Map.Entry<WorkOfArt, Room> entry : museumArtCollection.entrySet()) { 
            concatStr += "\n"+ entry.getValue();
            concatStr += this.listArtInRoom(entry.getValue()).toString();                
        }
        return concatStr;
    }
    
    @Override
    public String toString() {
        String concatStr = "";
        for (Map.Entry<WorkOfArt, Room> entry : museumArtCollection.entrySet()) { 
            concatStr += "\n"+ "Work of Art: " + entry.getKey() + "," + 
                         "\n"+ "Room: " + entry.getValue();                
        }
        return concatStr;
    }
}
