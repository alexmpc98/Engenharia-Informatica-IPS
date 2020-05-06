import java.util.Scanner;

/**
 * InputReader implementa a entrada de dados através do teclado.
 *
 * @author POO
 * @version 1 (26/02/2018)
 */
public class InputReader {

    private Scanner reader;

    public InputReader() {
        reader = new Scanner(System.in);
    }

    public char getChar(String question) {
        if (question == null) {
            question = "";
        }

        System.out.print(" > " + question);

        char answer = reader.next().charAt(0);
        reader.nextLine();

        return answer;
    }
}