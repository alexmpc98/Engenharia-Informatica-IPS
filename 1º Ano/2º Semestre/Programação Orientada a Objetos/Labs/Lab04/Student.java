public class Student extends User
{
    String course;
    
    public Student(String username, String password, String course){
        super(username,password);
        this.course = course;
    }
    
    @Override
    public void getInfo(){
        String str = "";
        str += "Estudante: \n";
        str += "       Username: " + getUsername() + "\n";
        str += "       Autenticado: " + booleanToString(isAuthenticated()) + "\n";
        str += "       Estudante do curso: " + this.course + "\n";
        System.out.println(str);
    }
}
