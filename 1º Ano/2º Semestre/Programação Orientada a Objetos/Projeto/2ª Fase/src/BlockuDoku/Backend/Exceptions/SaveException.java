package BlockuDoku.Backend.Exceptions;

import BlockuDoku.Backend.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexandre e Sérgio
 */
public class SaveException extends Exception{
    private ErrorCode error;
    
    /**
     * Constructor que recebe um error code e inicializa o SaveException.
     * 
     * @param error ErroCode
     */
    public SaveException(ErrorCode error){
        super(error.toString());
    }
    
    /**
     * Este método retorna o código de erro.
     * 
     * @return Código de erro (ErrorCode)
     */
    public ErrorCode getError() {
        return this.error;
    }
}
