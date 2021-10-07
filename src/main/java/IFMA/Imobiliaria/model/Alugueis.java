package IFMA.Imobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alugueis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dt_vencimento;

    private Double valor_pago;
    private String obs;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_locacao")
    private Locacao locacao;

}
