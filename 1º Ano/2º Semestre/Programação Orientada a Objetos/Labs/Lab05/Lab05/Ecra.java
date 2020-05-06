import java.util.Scanner;

public class Ecra
{
    public Ecra()
    {     
        
    }
    public void initialMenu(){
        int escolha = 0;
        int dificulty = 0;
            do{
                Scanner sca = new Scanner(System.in);
                System.out.println("|**********************|");
                System.out.println("|     BlockuDoku       |");
                System.out.println("|**********************|");
                System.out.println("|                      |");
                System.out.println("| 1- Iniciar novo jogo |");
                System.out.println("|                      |");
                System.out.println("| 0- Sair do jogo      |");  
                System.out.println("|                      |");
                System.out.println("|**********************|");
                escolha = sca.nextInt();
                
                switch(escolha){
                    case 1:
                     do{
                        System.out.println("|*********************************|");
                        System.out.println("|     Escolha o modo de jogo      |");
                        System.out.println("|*********************************|");
                        System.out.println("|                                 |");
                        System.out.println("| 1 - Básico                      |");
                        System.out.println("|                                 |");
                        System.out.println("| 2 - Avançado                    |");
                        System.out.println("|                                 |");
                        System.out.println("| 0 - Voltar para o menu inicial  |");
                        System.out.println("|                                 |");
                        System.out.println("|*********************************|");
                        dificulty = sca.nextInt();
                        
                        switch(dificulty){
                            case 1:
                                System.out.println("Bem vindo ao BlockoDoku - Modo Básico");
                                break;
                            case 2:
                                System.out.println("Bem vindo ao BlockoDoku - Modo Avançado");
                                break;
                            
                            default:
                                System.out.println("Opção Invalida");
                                break;
                            
                           
                        }
                     } while (dificulty != 0);
                     
                    default:
                        System.out.println("Opção Invalida");
                        break;
                }
            } while (escolha != 0);
    }
}
