package IFMA.Imobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.LinkedHashSet;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Imoveis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    private String cep;
    private String tipo_imovel;
    private String endereco;
    private String obs;

    @PositiveOrZero
    private Integer dormitorios;
    private Integer banheiros;
    private Integer suites;
    private Integer metragem;
    private Double valor_aluguel_sug;


    @JsonIgnore
    @OneToMany(mappedBy = "imovel", cascade = CascadeType.ALL)
    private Set<Locacao> locacao = new LinkedHashSet<>();

}
