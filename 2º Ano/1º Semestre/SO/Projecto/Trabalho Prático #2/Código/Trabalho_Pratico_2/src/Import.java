import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Import {
    
    public static int[][] importFile(BufferedReader in) throws IOException{
        String line;
        int index = -1;
        int nCities;
        int[][] distances = null;
        int col = 0;
        ArrayList<String> columns;
        
        while((line = in.readLine()) != null){
            if(index == -1){
                nCities = Integer.parseInt(line);
                distances = new int[nCities][nCities];
                index++;
                continue;
            }
            
            columns = new ArrayList<>(Arrays.asList(line.split(" ")));
            for(String e : columns){
                try{
                    distances[index][col] = Integer.parseInt(e);
                    col++;
                } catch(NumberFormatException ex){}
            }
            col = 0;
            index++;
        }
        
        return distances;
    }
}
