package IFMA.Imobiliaria.repository;

import IFMA.Imobiliaria.model.Alugueis;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface AlugueisRepository extends JpaRepository<Alugueis, Long> {

}
