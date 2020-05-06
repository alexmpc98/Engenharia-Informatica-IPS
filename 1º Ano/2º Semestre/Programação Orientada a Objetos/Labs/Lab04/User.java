public abstract class User
{
   String username,password;
   boolean authenticated;
    
   public User(String username, String password){
       this.username = username;
       this.password = password;
       this.authenticated = false;
   }
   
   public abstract void getInfo();
   
   public String booleanToString(boolean value){
       return value == true ? "Sim" : "Não";
   }
   
   public String getUsername(){
       return this.username;
   }
   
   public String getPassword(){
       return this.password;
   }
   
   public boolean isAuthenticated(){
       return this.authenticated;
   }
   
   public void setAuthenticated(boolean value){
       this.authenticated = value;
   }
}

