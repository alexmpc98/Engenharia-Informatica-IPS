import java.util.*;
public class SocialNetwork
{
    ArrayList<User> users;
    ArrayList<Post> posts;
    User authenticatedUser;
    
    public SocialNetwork()
    {
        this.users = new ArrayList<User>();
        this.posts = new ArrayList<Post>();
        this.authenticatedUser = null;
    }
    
    public void addUser(User user){
        String message = "Você foi adicionado aos utilizadores da rede social!";
        for(int i=0; i<this.users.size();i++){
            if(user.username == this.users.get(i).username)
                message = "Utilizador já existe!";
        }
        this.users.add(user);
        System.out.println(message);
    }
    
    public void loginSession(String username, String password){
        for(int i=0;i<this.users.size();i++) {
            if(this.users.get(i).username == username){
                if(this.users.get(i).authenticated == false){
                    if(this.users.get(i).password == password){
                        System.out.println("Bem Vindo " + username + "!\n");
                        this.authenticatedUser = this.users.get(i);
                        this.authenticatedUser.setAuthenticated(true);
                        break;
                        //return;
                    }
                } else {
                    System.out.println("O utilizador já se encontra autenticado!");
                }
            } else {
                   System.out.println("O utilizador não existe!");  
            }
        }
     
    }
    
    public void logoutSession(){
        if(this.authenticatedUser != null)
            this.authenticatedUser = null;
    }
    
    public void showHeader(){
        String str = "";
        str += "*************************************************\n";
        str += "*            ACADEMIC SOCIAL NETWORK            *\n";
        str += "*************************************************";
        System.out.println(str);
    }
    
    public void listUsers(){
        showHeader();
        String str = "";
        str += "*                     USERS                     *\n";
        str += "*************************************************\n";
        System.out.println(str);
        for(int i=0; i<this.users.size();i++){
            this.users.get(i).getInfo(this.users.get(i).username);
        }
    }
    
    public void publishPost(Post post){
        String message = "Postagem não publicada!";
        if(this.authenticatedUser != null){
            post.setAuthor(authenticatedUser);
            posts.add(post);
            message = "Postagem publicada com sucesso!";
            return;
        }
        System.out.println(message);
    }
    
    public void showFeed(){
        showHeader();
        String str = "";
        str += "*                     FEED                      *\n";
        str += "************************************************* \n";
        System.out.println(str);
        for(int i=0; i<this.posts.size();i++){
            if(this.posts.get(i).visible == true)
                this.posts.get(i).show();
        }
    }
    
    public void searchFor(String text){
        posts.stream().filter(post -> post.search(text) == true).forEach(post -> {System.out.println("The term '" + text + "' was found in: \n"); post.show();});
    }
    
    public void showNotifications(){
        showHeader();
        String str = "";
        str += "*                 NOTIFICATIONS                 *\n";
        str += "*************************************************\n";
        System.out.println(str);
        posts.stream().filter( post -> post instanceof EventPost ).map(post -> (EventPost)post).forEach(post -> post.showNotification());
    }
}
