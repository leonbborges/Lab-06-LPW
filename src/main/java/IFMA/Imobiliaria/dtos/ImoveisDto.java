package IFMA.Imobiliaria.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImoveisDto {
    private Long id;
    private String tipo_Imovel;
    private String endereco;
    private String cep;
    private Integer dormitorios;
    private Integer banheiros;
    private Integer suites;
    private Integer metragem;
    private Double valor_aluguel_suge;
    private String obs;
}
