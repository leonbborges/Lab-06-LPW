package IFMA.Imobiliaria.controller.dtos;

import IFMA.Imobiliaria.controller.dtos.ClienteIdInput;
import IFMA.Imobiliaria.controller.dtos.ImovelIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;


@Getter
@Setter
public class LocacaoDto {

    @PositiveOrZero
    private Integer ativo;
    private Integer dia_vencimento;
    private Double perc_multa;
    private Double valor_alugue;

    @NotBlank
    private String obs;

    @Temporal(TemporalType.DATE)
    private Date data_fim;
    @Temporal(TemporalType.DATE)
    private Date data_inicio;

    private ImovelIdInput imovel;
    private ClienteIdInput cliente;

}
