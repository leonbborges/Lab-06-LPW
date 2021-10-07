package IFMA.Imobiliaria.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Cliente {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @EqualsAndHashCode.Include
   private Long id;
   @NotBlank
   private String nome_cliente;
   @CPF
   private String cpf;
   private String telefone;
   @Email
   private String email;
   @Temporal(TemporalType.DATE)
   private Date dt_nascimento;

   @JsonIgnore
   @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
   private Set<Locacao> locacao = new LinkedHashSet<>();
}
