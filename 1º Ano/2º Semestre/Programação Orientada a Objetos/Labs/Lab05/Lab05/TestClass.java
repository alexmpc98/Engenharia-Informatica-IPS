
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestClass
{

    public TestClass()
    {
        Ecra ecra = new Ecra();
        assertEquals(ecra.checkValues(1), "Iniciar novo jogo");
        assertEquals(ecra.checkValues(2), "Abrir jogo");
        assertEquals(ecra.checkValues(3), "Mostrar pontuações pessoais");
        assertEquals(ecra.checkValues(4), "Ranking (TOP 10)");
        assertEquals(ecra.checkValues(8), "Opção Invalida");
  
    }

}
