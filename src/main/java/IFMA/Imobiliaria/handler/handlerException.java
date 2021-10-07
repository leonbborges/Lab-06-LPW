package IFMA.Imobiliaria.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;

@ControllerAdvice
public class handlerException {
    @ExceptionHandler({BadRequestEx.class})
    public ResponseEntity<BadRequestDetalhes> handlerBadRequestException(BadRequestEx brd){
        return new ResponseEntity<>(
                BadRequestDetalhes.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Bad request Exception, check o documento")
                        .detalhes(brd.getMessage())
                        .mensagem_do_desenvolvedor(brd.getClass().getName())
                        .build(),HttpStatus.BAD_REQUEST);

    }
}
