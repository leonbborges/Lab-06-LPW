package IFMA.Imobiliaria.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClienteDto {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Date dt_nascimento;

}
