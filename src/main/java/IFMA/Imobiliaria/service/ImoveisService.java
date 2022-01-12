package IFMA.Imobiliaria.service;

import IFMA.Imobiliaria.handler.BadRequestEx;
import IFMA.Imobiliaria.model.Imoveis;
import IFMA.Imobiliaria.repository.Imoveisrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImoveisService {
    @Autowired
    private Imoveisrepository imoveisrepository;
    public Page<Imoveis> listAll(Pageable pageable){
        return imoveisrepository.findAll(pageable);

    }
    public Imoveis findByIdORTrowBadRequestException(Long id){
        return imoveisrepository.findById(id)
                .orElseThrow(()-> new BadRequestEx("Imovel ID not found"));
    }
    @Transactional
    public Imoveis save(Imoveis imovel) {
        return imoveisrepository.save(imovel);
    }

    @Transactional
    public void delete(Long id) {
        imoveisrepository.delete(findByIdORTrowBadRequestException(id));
    }


    @Transactional
    public void replace(Imoveis imoveis) {
        Imoveis saveCliente= findByIdORTrowBadRequestException(imoveis.getId());
        Imoveis imoveis1 = Imoveis.builder()
                .tipo_imovel(imoveis.getTipo_imovel())
                .endereco(imoveis.getEndereco())
                .banheiros(imoveis.getBanheiros())
                .cep(imoveis.getCep())
                .dormitorios(imoveis.getDormitorios())
                .metragem(imoveis.getMetragem())
                .obs(imoveis.getObs())
                .suites(imoveis.getSuites())
                .valor_aluguel_sug(imoveis.getValor_aluguel_sug())
                .build();

        imoveisrepository.save(imoveis1);
    }
}
