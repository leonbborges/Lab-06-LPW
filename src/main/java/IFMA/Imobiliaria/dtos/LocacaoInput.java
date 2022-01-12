package IFMA.Imobiliaria.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class LocacaoInput {
    @NotNull
    private int ativo;

    private int dia_vencimento;
    private Double perc_multa;

    @NotNull
    @PositiveOrZero
    private BigDecimal valor_alugue;

    private String obs;
    @Temporal(TemporalType.DATE)
    private Date data_fim;

    @Temporal(TemporalType.DATE)
    private Date data_inicio;

    @Valid
    @NotNull
    private ImovelIdInput imovel;

    @Valid
    @NotNull
    private ClienteIdInput cliente;
}
