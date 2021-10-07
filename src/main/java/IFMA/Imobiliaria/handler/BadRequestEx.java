package IFMA.Imobiliaria.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestEx extends  RuntimeException{
    public BadRequestEx(String mensagem){
        super(mensagem);
    }
}
