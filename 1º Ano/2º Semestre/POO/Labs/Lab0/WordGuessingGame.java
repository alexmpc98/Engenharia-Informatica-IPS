/**
 * Escreva a descrição da classe WordGuessingGame aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class WordGuessingGame
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private int x;
    public String hiddenWord;
    public String guessedWord;
    public int numberOfTries;
    public InputReader reader;
    /**
     * COnstrutor para objetos da classe WordGuessingGame
     */
    public WordGuessingGame()
    {
        // inicializa variáveis de instância
        hiddenWord = "abc";
        guessedWord = "___";
        numberOfTries = 0;
        reader = new InputReader;
        
    }
    public String getHiddenWord(){
    return hiddenWord;
    }
    public String getGuessedWord(){
    return guessedWord;}
    
    public int getNumberOfTries(){
        return numberOfTries;
    }
    public String setHiddenWord(String hiddenWord){
    this.hiddenWord = hiddenWord;
    return this.hiddenWord;
    }
    public String setGuessedWord(String guessedWord){
    this.guessedWord = guessedWord;
    return this.guessedWord;
    }
    public int setNumberOfTries(int numberOfTries){
    this.numberOfTries = numberOfTries;
    return this.numberOfTries;
    }
    public void showGuessedWord(String guessedWord){
    System.out.println(guessedWord);
    }
    private void showWelcome(){
    System.out.println("Welcome to the Hangman game");
    }

    /**
     * Exemplo de método - substitua este comentário pelo seu próprio
     * 
     * @param  y   exemplo de um parâmetro de método
     * @return     a soma de x com y 
     */
    public int sampleMethod(int y)
    {
        // ponha seu código aqui
        return x + y;
    }
}
