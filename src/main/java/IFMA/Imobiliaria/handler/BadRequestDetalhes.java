package IFMA.Imobiliaria.handler;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestDetalhes {
    private String titulo;
    private Integer status;
    private String detalhes;
    private String mensagem_do_desenvolvedor;
    private LocalDateTime timestamp;

}
