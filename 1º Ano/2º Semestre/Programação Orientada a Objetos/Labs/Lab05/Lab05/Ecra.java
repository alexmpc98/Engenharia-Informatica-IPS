import java.util.Scanner;

public class Ecra
{
    public Ecra()
    {     
        
    }
    public void displayApplicationMenu(){ 
        Scanner sca = new Scanner(System.in);
        System.out.println("|*********************************|");
        System.out.println("|           BlockuDoku            |");
        System.out.println("|*********************************|");
        System.out.println("|                                 |");
        System.out.println("| 1 - Iniciar novo jogo           |");
        System.out.println("| 2 – Abrir jogo                  |");
        System.out.println("| 3 – Mostrar pontuações pessoais |");
        System.out.println("| 4 – Ranking (TOP 10)            |");
        System.out.println("| 0- Sair do jogo                 |");  
        System.out.println("|                                 |");
        System.out.println("|*********************************|");          
    }
     
    public void run () {
        int escolha = 0;
        String result;
        Scanner sca = new Scanner(System.in); 
        do{
            displayApplicationMenu();
            escolha = sca.nextInt();
            result = checkValues(escolha);
            if (result != "Opção Invalida") {
                System.out.println(result); 
                break;
            }
        } while (escolha != 0);
    }
    
    public String checkValues(int escolha){
            String out = "Opção Invalida";
            switch(escolha){
                    case 1:
                         out = "Iniciar novo jogo";
                         break;
                    case 2:
                         out = "Abrir jogo";
                         break;
                    case 3:
                         out = "Mostrar pontuações pessoais";
                         break;
                    case 4:
                         out = "Ranking (TOP 10)";
                         break;
                    case 0:
                         break; 
                    default:
                        out = "Opção Invalida";
                        break;
            }
            return out;
    }
}
