import java.util.ArrayList;

public class Program {
    public static void run() {   
        SocialNetwork socialNetwork = new SocialNetwork();
        
        System.out.println("/** ------------------- Nível 1 ------------------- **/");
        
        Teacher teacher = new Teacher("ana.marques", "123");
        Student student = new Student("rita.martins", "123", "Licenciatura em Engenharia Informática");
        
        teacher.addCourseUnit("Programação Orientada por Objetos");
        teacher.addCourseUnit("Introdução à Programação");
        
        teacher.getInfo(teacher.getUsername()); 
        student.getInfo(student.getUsername()); 
        
        System.out.println("/** ----------------------------------------------- **/");  
        
        System.out.println("\n"); 
        
        System.out.println("/** ------------------- Nível 2 ------------------- **/"); 
        
        ArrayList<Post> posts = new ArrayList<Post>();
        posts.add(new EventPost("Football Game", "Setubal", "11/04/20 12:31"));
        posts.add(new MessagePost("Hello World"));
        posts.add(new EventPost("Badminton Game", "Lisboa", "11/04/20 12:31"));
        posts.add(new MessagePost("Are You There?!"));
        
        posts.get(0).setAuthor(teacher);
        posts.get(1).setAuthor(teacher);
        posts.get(2).setAuthor(teacher);
        posts.get(3).setAuthor(teacher);
        
        for (Post post : posts) {
            post.show();
        }
        
        System.out.println("/** ----------------------------------------------- **/"); 
        
        System.out.println("\n"); 
        
        System.out.println("/** ------------------- Nível 3 ------------------- **/"); 
        
        socialNetwork.addUser(teacher);
        
        socialNetwork.loginSession(teacher.getUsername(), teacher.getPassword());
        
        socialNetwork.listUsers();
        
        System.out.println("/** ----------------------------------------------- **/");
        
        System.out.println("\n"); 
        
        System.out.println("/** ------------------- Nível 4 ------------------- **/"); 
        
        socialNetwork.publishPost(posts.get(0));
        socialNetwork.publishPost(posts.get(1));
        socialNetwork.publishPost(posts.get(2));
        socialNetwork.publishPost(posts.get(3));
        
        socialNetwork.showFeed();
        
        System.out.println("/** ----------------------------------------------- **/");
            
        System.out.println("\n"); 
        
        System.out.println("/** ------------------- Nível 5 ------------------- **/"); 
        
        socialNetwork.searchFor("Football Game");
        
        socialNetwork.showNotifications();
        
        System.out.println("/** ----------------------------------------------- **/");
        
        System.out.println("\n"); 
    }
}
