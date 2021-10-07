package IFMA.Imobiliaria.service;


import IFMA.Imobiliaria.handler.BadRequestEx;
import IFMA.Imobiliaria.model.Cliente;
import IFMA.Imobiliaria.model.Imoveis;
import IFMA.Imobiliaria.model.Locacao;
import IFMA.Imobiliaria.repository.LocacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocacaoService {
    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ImoveisService imovelService;

    public Page<Locacao> listAll(Pageable pageable){
        return locacaoRepository.findAll(pageable);

    }
    public Locacao findByIdORTrowBadRequestException(Long id){
        return locacaoRepository.findById(id)
                .orElseThrow(()-> new BadRequestEx("Locacao ID not found"));
    }
    public Locacao  save(Locacao  locacao) {
        Long imovelId = locacao.getImovel().getId().;
        Long clienteId = locacao.getCliente().getId();

        Cliente cliente = clienteService.findByIdORTrowBadRequestException(clienteId);
        Imoveis imovel = imovelService.findByIdORTrowBadRequestException(imovelId);

        locacao.setImovel(imovel);
        locacao.setCliente(cliente);

        return  locacaoRepository.save(locacao).builder().alugueis(locacao.getAlugueis())
                .ativo(locacao.getAtivo())
                .data_fim(locacao.getData_fim())
                .data_inicio(locacao.getData_inicio())
                .dia_vencimento(locacao.getDia_vencimento())
                .obs(locacao.getObs())
                .perc_multa(locacao.getPerc_multa())
                .valor_alugue(locacao.getValor_alugue())
                .build();
}
    public void delete(Long id) {
        locacaoRepository.delete(findByIdORTrowBadRequestException(id));
    }

    public void replace (Locacao locacao){
        Long imovelId = locacao.getImovel().getId();
        Long clienteId = locacao.getCliente().getId();

        Cliente cliente = clienteService.findByIdORTrowBadRequestException(clienteId);
        Imoveis imovel = imovelService.findByIdORTrowBadRequestException(imovelId);

        locacao.setImovel(imovel);
        locacao.setCliente(cliente);

        Locacao saveLocacao = findByIdORTrowBadRequestException(locacao.getId());
        Locacao locacao1 = Locacao.builder().alugueis(locacao.getAlugueis())
                .ativo(locacao.getAtivo())
                .data_fim(locacao.getData_fim())
                .data_inicio(locacao.getData_inicio())
                .dia_vencimento(locacao.getDia_vencimento())
                .obs(locacao.getObs())
                .perc_multa(locacao.getPerc_multa())
                .valor_alugue(locacao.getValor_alugue())
                .build();
        locacaoRepository.save(locacao1);
    }
}
