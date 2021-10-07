package IFMA.Imobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_inquilino")
    private Cliente cliente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_imovel")
    private Imoveis imovel;

    @JsonIgnore
    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL)
    private Set<Alugueis> alugueis;
}
