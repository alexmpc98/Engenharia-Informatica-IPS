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
    private String hiddenWord = "abc";
    private String guessedWord = "___";
    private int numberOfTries = 0;;
    private InputReader reader = new InputReader();
    
     public void WordGuessingGame(){
        
    }
    
   
    public String getHiddenWord(){
        return hiddenWord;
    }
    
    public String getGuessedWord(){
        return guessedWord;
    }
    
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
    
    private void showGuessedWord(String guessedWord){
        System.out.println(guessedWord);
    }
    
    private void showWelcome(){
        System.out.println("Welcome to the Hangman game");
    }
   
    private void guess(){
        int count = 1;
        char input = '\0';
        do {
            input = reader.getChar("Introduza um caracter: ");
            for(int i=0; i<getHiddenWord().length();i++){
                char[] ch = getGuessedWord().toCharArray();
                if( input == getHiddenWord().charAt(i)){
                    ch[i] = input;
                    setGuessedWord(String.valueOf(ch));
                }
            }
            showGuessedWord(getGuessedWord());
            setNumberOfTries(count++);
            System.out.println(getGuessedWord() +"-"+ getHiddenWord());
        } while(!getGuessedWord().equalsIgnoreCase(getHiddenWord()));
    }
    
    private void showResult(){ 
        System.out.println("Tentativas: "+getNumberOfTries());
    }
    
    public void play(){
        showWelcome();
        guess();
        showResult();
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
