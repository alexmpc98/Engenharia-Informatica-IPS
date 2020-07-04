import java.util.ArrayList;

public class Teacher extends User
{
    ArrayList<String> courseUnits;
    
    public Teacher(String username, String password){
        super(username,password);
        this.courseUnits = new ArrayList<String>();      
    }
    
    public void addCourseUnit(String courseUnit){
        this.courseUnits.add(courseUnit);
    }
    
    @Override
    public void getInfo(String username){
        String str = "";
        str += "Professor/a: \n";
        str += "       Username: " + getUsername() + "\n";
        str += "       Autenticado: " + booleanToString(isAuthenticated()) + "\n";
        str += "Professor/a de: \n";
        for(int i=0; i<this.courseUnits.size(); i++){
              str += "       - " + this.courseUnits.get(i) + "\n";
        }
        System.out.println(str);
    }
}
