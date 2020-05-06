import java.util.ArrayList;

public class Program {
    public void main(){
        
        //Nivel 1 *****************************************************
        ArrayList<Room> museumRooms = new ArrayList<>();
        museumRooms.add(new Room("Room 1",20.00));
        museumRooms.add(new Room("Room 2",30.00));
        museumRooms.add(new Room("Room 3",40.00));
        museumRooms.add(new Room("Room 4",50.00));
        museumRooms.add(new Room("Room 5",60.00));
        museumRooms.add(new Room("Room 6",70.00));
        museumRooms.add(new Room("Room 7",80.00));
        museumRooms.add(new Room("Room 8",15.00));
        museumRooms.add(new Room("Room 9",22.00));
        museumRooms.add(new Room("Room 10",23.00));
        
        for(Room room: museumRooms){
            System.out.println(room.toString());
        }
     
        
        //Nivel 2 *****************************************************
        ArrayList<WorkOfArt> arts = new ArrayList<>();
        arts.add(new WorkOfArt("Artist1","Title1"));
        arts.add(new WorkOfArt("Artist2","Title2"));
            
        for(WorkOfArt art: arts){
            System.out.println(art.toString());
        }
        
        
        //Nivel 3 *****************************************************
        ArrayList<WorkOfArt> newArts = new ArrayList<>();
        newArts.add(new Sculpture("Sculpturist","Title1","Ceramic"));
        newArts.add(new Painting("Painter","Title2", "Fauvism", "OIL", "ON_CANVAS"));
        
        for(WorkOfArt newArt: newArts){
            System.out.println(newArt.toString());
        }
        
        //Nivel 4 *****************************************************
        Museum museum = new Museum();
        int i = 0;
        for(WorkOfArt newArt: newArts){
            museum.addToMuseumCollection(newArt, museumRooms.get(i));
            i++;
        }
        //museum.removeFromMuseumCollection(newArts.get(0));
        System.out.println(museum.toString());
        
        //Nivel 5 *****************************************************
        ArrayList<WorkOfArt> museumArts = new ArrayList<>();
        museumArts.add(new Sculpture("NewSculpturist","NewTitle","Ceramic"));
        museumArts.add(new Painting("NewPainter","NewTitle", "Fauvism", "OIL", "ON_PAPER"));
        for(WorkOfArt museumArt: museumArts){
            museum.addToMuseumCollection(museumArt, museumRooms.get(3));
        }
        System.out.println("\n" + museum.listArtInRoom(museumRooms.get(3)));
        System.out.println(museum.printAllByRoom());
                     
    }
}
