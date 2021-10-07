package IFMA.Imobiliaria.repository;


import IFMA.Imobiliaria.model.Imoveis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Imoveisrepository extends JpaRepository<Imoveis, Long> {

}
