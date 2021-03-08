package com.brunomnsilva;

import com.brunomnsilva.dao.DaoException;
import com.brunomnsilva.domain.*;

import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {

        //BookDao dao = new BookDaoVolatileList();
        //BookDao dao = new BookDaoVolatileMap();
        //BookDao dao = new BookDaoSerialization();
        //BookDao dao = new BookDaoTextFiles();
        
        //BookDao dao = BookDaoFactory.create("valatileList");
        BookDao dao = BookDaoFactory.create("volatileMap");
        //BookDao dao = BookDaoFactory.create("serialization");
        //BookDao dao = BookDaoFactory.create("textfiles");

        /* only runs if there is no persisted data inside the dao */
        if(dao.count() == 0) {
            System.out.println("Empty storage. Populating initial data...");
            populate(dao);
        } else {
            System.out.println("Existing data in storage was detected!");
        }

        // execute Command Line Interface
        CLI(dao);
    }

    public static void CLI(BookDao dao) {
        boolean quit = false;
        Scanner keyboard = new Scanner(System.in);
        String[] input;

        while(!quit) {
            try {
                System.out.println();
                System.out.println("Available commands: GET, ADD, DEL, LIST, SEARCH, RANGE, COUNT, QUIT");
                System.out.print("Type command > ");
                String command = keyboard.nextLine().toLowerCase();

                switch(command) {
                    case "get":
                        input = ask(keyboard, new String[]{"ISBN"});
                        Book bookQuery = dao.get(input[0]);
                        System.out.println(bookQuery);
                        break;
                    case "add":
                        input = ask(keyboard, new String[]{"ISBN", "Author", "Title", "Year"});
                        Book book = Book.createFromStrings(input[0],input[1],input[2],input[3]);
                        dao.save(book);
                        break;
                    case "del":
                        input = ask(keyboard, new String[]{"ISBN"});
                        Book deleted = dao.delete(input[0]);
                        System.out.println("Removed: " + deleted);
                        break;
                    case "list":
                        Collection<Book> all = dao.getAll();
                        all.forEach(b -> System.out.println(b));
                        break;
                    case "search":
                        input = ask(keyboard, new String[]{"Author query"});
                        Collection<Book> results = dao.getAllFromAuthorSearch(input[0]);
                        results.forEach(b -> System.out.println(b));
                        break;
                    case "range":
                        input = ask(keyboard, new String[]{"Year Start", "Year End"});
                        Collection<Book> result = dao.getAllFromYearRange(parseInt(input[0]), parseInt(input[1]));
                        result.forEach(b -> System.out.println(b));
                        break;
                    case "count":
                        System.out.println("Total records: " + dao.count());
                        break;
                    case "quit":
                        quit = true;
                        break;
                    default:
                        System.out.println("[Invalid command]");
                }
            } catch (DaoException e) {
                System.out.println("[" + e.getMessage() + "]");
            }
        }
    }

    public static String[] ask(Scanner scanner, String[] questions) {
        String[] answers = new String[questions.length];
        for(int i=0; i< questions.length; i++) {
            System.out.print(questions[i] + "? ");
            answers[i] = scanner.nextLine();
        }
        return answers;
    }

    public static void populate(BookDao dao) {
        Book[] books = {
                new Book("L5C-7W7-H4M", "Ira W. Santos", "et arcu imperdiet ullamcorper.", 2010),
                new Book("M4I-7S1-W8K", "Melvin T. Bryant", "elit, pharetra", 2012),
                new Book("F1V-0Q3-R1W", "Paul J. Lancaster", "netus et malesuada fames", 2005),
                new Book("Y3I-5S5-S9K", "Rahim K. Adams", "nec enim. Nunc ut erat. Sed", 2007),
                new Book("O3L-8E5-N5B", "Imani L. Holden", "Sed nulla ante, iaculis nec,", 1984),
                new Book("D1D-6T5-E2S", "Baxter P. England", "odio. Aliquam vulputate", 1984),
                new Book("S9E-3L2-C9P", "Sybil V. Trevino", "nonummy ut, molestie in, tempus", 2004),
                new Book("T9D-5K5-D0L", "Gretchen R. Bush", "lorem lorem, luctus ut, pellentesque eget,", 1975),
                new Book("X1E-9W7-S1H", "Arsenio L. Rosales", "turpis egestas. Fusce", 1986),
                new Book("E7J-2J5-C0P", "Rahim N. Gibson", "sapien molestie orci tincidunt", 2018),
                new Book("C0K-7B2-A9M", "Lois F. Stanton", "tempus scelerisque, lorem ipsum sodales", 1987),
                new Book("W7I-3O1-I5L", "Caryn X. Potts", "eu lacus. Quisque", 2019),
                new Book("X6Q-4R0-E2I", "Madeline P. Bailey", "sollicitudin a, malesuada id,", 2005),
                new Book("V2N-4X9-C0L", "Rogan O. James", "Praesent interdum ligula eu", 1988),
                new Book("Y5N-4M2-C5E", "Carolyn L. Page", "varius et,", 2007),
                new Book("W0Q-3Z7-L3B", "Ella J. Abbott", "lectus quis massa. Mauris", 2016),
                new Book("K4K-4G1-M7W", "Olivia H. Hinton", "enim. Mauris quis turpis vitae purus", 1989),
                new Book("L5Y-7T0-O6G", "Olga Y. Guy", "diam lorem, auctor quis, tristique", 2012),
                new Book("H6K-2E2-M8D", "Rafael N. Goff", "ultricies adipiscing, enim mi tempor", 2000),
                new Book("H0R-7V4-S6H", "Hayden O. Adams", "sit amet ultricies sem magna nec", 2016),
                new Book("L5F-5W8-I7D", "Inga J. Cantu", "ornare, elit elit fermentum risus,", 1978),
                new Book("R2M-5Y7-N9M", "Aline G. Rose", "enim, sit amet ornare", 1981),
                new Book("C8O-3Z2-D3U", "Hunter G. Hardy", "metus. Vivamus", 1977),
                new Book("U8Y-4R9-A3N", "Ryan Q. Olson", "nulla. Integer", 1995),
                new Book("M1Y-7L8-S7Y", "Amena G. Holland", "lobortis quam a felis ullamcorper", 2007),
                new Book("S7G-7P0-Y6X", "Cameron M. Morin", "interdum enim non nisi. Aenean eget", 1981),
                new Book("R9P-9R0-H4F", "Silas L. Carpenter", "tempor, est", 1973),
                new Book("X2I-1Z4-C2I", "Camille B. Garrett", "rhoncus. Nullam velit dui, semper et,", 1987),
                new Book("O5U-5D3-S2P", "Grace T. Duran", "in faucibus orci luctus et", 1983),
                new Book("E4O-9I1-E3V", "Finn M. Stephens", "scelerisque scelerisque dui. Suspendisse ac", 1977),
                new Book("D5N-2W0-H3B", "Judith E. Holloway", "non ante bibendum ullamcorper.", 2003),
                new Book("E5Z-4L6-V5A", "Beverly I. Caldwell", "et netus", 1986),
                new Book("J9M-2F3-W4Q", "Arden J. Mays", "sit amet orci. Ut sagittis lobortis", 1990),
                new Book("C5I-6L6-O7X", "Tashya I. Berry", "Aliquam gravida", 2019),
                new Book("N6A-5T2-E5O", "Blaze G. Hays", "nec, malesuada ut, sem.", 1973),
                new Book("P7I-7S4-P6L", "Rhoda M. Middleton", "at arcu. Vestibulum ante ipsum", 2009),
                new Book("B1E-0C5-D0T", "Jerry G. Byrd", "In mi pede,", 1977),
                new Book("H3X-3M3-B4T", "Jason K. Gould", "ipsum. Suspendisse non leo. Vivamus", 2012),
                new Book("E4E-3E7-N4M", "Vivien J. Bishop", "lorem ac risus. Morbi metus. Vivamus", 2019),
                new Book("S1G-1D3-A0J", "Orson V. Wolf", "dictum eu,", 1987),
                new Book("S2D-4Y2-L4M", "Bradley R. Shaffer", "Integer id magna", 1999),
                new Book("Z2A-0N0-F2V", "Kane R. Hodge", "commodo hendrerit.", 1973),
                new Book("R6N-5T8-O7N", "Jaquelyn V. Thomas", "ac, fermentum", 1986),
                new Book("S0Q-9S0-O0T", "Doris L. Cook", "lobortis quam a", 1971),
                new Book("Z3Z-6O1-B4Y", "Armand M. Bradford", "lectus convallis est, vitae", 2009),
                new Book("Q2Y-9G2-L0X", "Kylan S. Phelps", "mi felis, adipiscing", 2018),
                new Book("U8A-4U9-K2B", "Nolan Y. Bryan", "euismod ac, fermentum vel,", 1971),
                new Book("Y7A-7L4-Y0S", "Maxwell Q. Dale", "imperdiet dictum", 1988),
                new Book("K7N-6C3-H1L", "Shoshana B. Vance", "Etiam gravida molestie", 1991),
                new Book("U9M-9H8-L0P", "Whoopi I. Ray", "erat. Vivamus nisi. Mauris", 1976),
                new Book("K1S-8Y8-R4K", "Lila O. Frye", "ornare, elit elit", 2010),
                new Book("V4Q-6B6-E2U", "Myra P. Gates", "pede et risus. Quisque libero lacus,", 2000),
                new Book("T8R-5W9-S7Y", "Alden Z. Hancock", "augue, eu tempor erat", 2016),
                new Book("J9P-2B9-Q0B", "Garrison G. Gonzales", "non, luctus sit amet, faucibus", 1970),
                new Book("A4F-0U3-U1I", "Wilma A. Coffey", "metus eu erat semper", 2000),
                new Book("F9B-3X9-J0R", "Darius K. Kerr", "semper egestas, urna", 2001),
                new Book("Z6S-8L2-P5T", "Donna Q. Mcdowell", "Lorem ipsum dolor sit amet, consectetuer", 1985),
                new Book("L7F-1R3-B8H", "Gareth Y. Hansen", "Mauris eu turpis.", 1972),
                new Book("Y6C-3Z8-L3N", "Alana X. Collier", "dui, nec tempus mauris", 1980),
                new Book("A8P-6N9-H5F", "Gage R. Butler", "nec, imperdiet nec, leo.", 1973),
                new Book("X6A-5H9-T7I", "Rana T. Calhoun", "purus mauris a nunc.", 2017),
                new Book("T8N-7A0-F0A", "Nadine H. Brady", "et risus. Quisque libero lacus, varius", 1998),
                new Book("N2O-9U4-P7P", "Sarah P. Mills", "amet lorem semper auctor. Mauris", 1978),
                new Book("R5B-6P7-R3U", "Maia H. Sheppard", "Donec sollicitudin adipiscing ligula. Aenean", 2009),
                new Book("G5K-0L1-R0D", "Eve O. Herman", "Proin eget odio. Aliquam", 1977),
                new Book("T3A-4L2-K6M", "Lacota Z. Workman", "Maecenas iaculis aliquet", 2017),
                new Book("N7E-2I6-M2Z", "Ryder I. Calderon", "placerat velit. Quisque", 1999),
                new Book("X6O-6E4-J3P", "Sebastian I. Martinez", "vitae, orci. Phasellus dapibus quam quis", 2005),
                new Book("B8Y-4P2-D7H", "Ethan S. Mendez", "magna nec quam. Curabitur", 1993),
                new Book("B5P-9H2-V3H", "Raja O. Aguirre", "eu lacus. Quisque imperdiet, erat nonummy", 2000),
                new Book("D5Z-7Y3-D6X", "Slade N. Alford", "at sem molestie sodales. Mauris", 2016),
                new Book("M5S-6H0-R4T", "Kyra A. Williams", "mus. Aenean eget magna. Suspendisse", 1986),
                new Book("Q8P-7U9-R8E", "Idona F. Rowland", "quam a felis", 1976),
                new Book("C6J-3V6-F1O", "Steel M. Mcdowell", "cursus et, magna. Praesent", 1995),
                new Book("S4B-1X0-C2Q", "Isaiah E. Rodriguez", "Cras dictum ultricies ligula. Nullam", 1974),
                new Book("I6U-6S8-A1D", "Channing W. Dunn", "Nam nulla", 2018),
                new Book("A1M-3V4-R3O", "Len S. Dodson", "sem, consequat", 1982),
                new Book("M4Y-0S1-M2W", "Carolyn E. Langley", "augue ut lacus. Nulla tincidunt, neque", 1981),
                new Book("B3Q-6X3-P2B", "Hadassah K. Morgan", "ac metus vitae", 2008),
                new Book("D9A-2P3-W5J", "Troy M. Lee", "dolor. Fusce mi lorem, vehicula et,", 1996),
                new Book("L7W-3N5-R2U", "Burke J. Blankenship", "cursus in, hendrerit consectetuer,", 1977),
                new Book("A4K-9H6-Q3L", "Beau L. Sexton", "ac mattis", 1977),
                new Book("G7H-1L6-G1Q", "Tanner B. Conway", "ultricies adipiscing, enim mi tempor", 1991),
                new Book("Z2Y-8J8-H8L", "Leonard G. Bauer", "euismod ac,", 1975),
                new Book("R6Q-1A0-I2M", "Aubrey E. Price", "venenatis vel,", 1971),
                new Book("S3R-9D8-D6Y", "Allistair S. Lee", "id nunc interdum feugiat.", 1990),
                new Book("A6G-8H2-E9P", "Leroy N. Harris", "magnis dis parturient montes, nascetur ridiculus", 2012),
                new Book("W0R-8X7-L3R", "Leonard L. Buck", "cursus. Integer", 2003),
                new Book("X8D-3Z9-Z1X", "Rylee V. Mcleod", "lacus. Aliquam rutrum", 1998),
                new Book("V4S-1Y6-R8X", "Hedda A. Hodges", "consectetuer euismod", 2007),
                new Book("C7Y-8J6-O1H", "Hilel L. Alford", "dignissim tempor arcu. Vestibulum ut eros", 1975),
                new Book("N7A-2O8-D7B", "Nadine O. Rivera", "odio sagittis semper.", 1973),
                new Book("X9J-0N4-H2X", "Sylvester W. Dorsey", "massa. Vestibulum accumsan neque", 2002),
                new Book("T6D-9A4-N9J", "Selma M. Snow", "sodales nisi magna sed dui.", 2018),
                new Book("C0U-0F8-I5B", "Harper U. Mccoy", "dictum eu, placerat eget, venenatis", 1990),
                new Book("S8H-0Z6-Z1M", "Ian P. Simon", "egestas rhoncus.", 2020),
                new Book("V9R-6N1-G4E", "Grady L. Nichols", "in lobortis tellus justo", 1976),
                new Book("Z4S-1W7-Q3V", "Bernard D. Skinner", "non justo. Proin non massa non", 1996),
                new Book("Q6Y-9A0-P2X", "Haviva N. Farrell", "ligula. Donec luctus aliquet odio.", 1974),
                new Book("Z6X-4Q9-Z1S", "Thor N. Wheeler", "eu, ultrices sit amet,", 2019)
        };

        for(Book b : books) {
            try {
                dao.save(b);
            } catch (DaoException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
