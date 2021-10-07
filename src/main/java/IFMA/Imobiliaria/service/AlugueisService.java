package IFMA.Imobiliaria.service;

import IFMA.Imobiliaria.handler.BadRequestEx;
import IFMA.Imobiliaria.model.Alugueis;
import IFMA.Imobiliaria.repository.AlugueisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlugueisService {
    @Autowired
    private AlugueisRepository alugueisRepository;

    public Page<Alugueis> listAll(Pageable pageable){
        return alugueisRepository.findAll(pageable);

    }
    public Alugueis findByIdORTrowBadRequestException(Long id){
        return alugueisRepository.findById(id)
                .orElseThrow(()-> new BadRequestEx("aluguel ID not found"));
    }
    public Alugueis save(Alugueis alugueis){
        return  alugueisRepository.save(alugueis).builder()
                .locacao(alugueis.getLocacao())
                .dt_vencimento(alugueis.getDt_vencimento())
                .obs(alugueis.getObs())
                .valor_pago(alugueis.getValor_pago())
                .build();
    }
    public void delete(Long id) {alugueisRepository.delete(findByIdORTrowBadRequestException(id)); }

    public void replace(Alugueis alugueis){
        Alugueis saveAluguel = findByIdORTrowBadRequestException(alugueis.getId());
        Alugueis alugueis1 = Alugueis.builder()
                .locacao(alugueis.getLocacao())
                .dt_vencimento(alugueis.getDt_vencimento())
                .obs(alugueis.getObs())
                .valor_pago(alugueis.getValor_pago())
                .build();
        alugueisRepository.save(alugueis1);
    }
}
