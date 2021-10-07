package IFMA.Imobiliaria.controller.dtos;


import IFMA.Imobiliaria.model.Cliente;
import IFMA.Imobiliaria.model.Imoveis;
import IFMA.Imobiliaria.model.Locacao;
import IFMA.Imobiliaria.repository.ClienteRepository;
import IFMA.Imobiliaria.repository.Imoveisrepository;
import IFMA.Imobiliaria.service.ClienteService;
import IFMA.Imobiliaria.service.ImoveisService;
import IFMA.Imobiliaria.service.LocacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/locacao")
@Log4j2
@RequiredArgsConstructor
public class LocacaoController {
    @Autowired
    private final LocacaoService locacaoService;

    @Autowired
    private Imoveisrepository imoveisrepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ImoveisService imoveisService;

    @GetMapping
    public ResponseEntity<Page<Locacao>> list(@RequestParam(required = false) String nome,
                                              @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 5)
                                                      Pageable pageable){
        return ResponseEntity.ok( locacaoService.listAll(pageable));
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity <Locacao> findById(@PathVariable Long id){
        return ResponseEntity.ok( locacaoService.findByIdORTrowBadRequestException(id));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Locacao save(@RequestBody @Valid LocacaoDto locacaoDto){
        System.out.println("ID: " + locacaoDto.getCliente().getId());
        System.out.println("ID: " + locacaoDto.getImovel().getId());

        Locacao locacao = new Locacao();
        locacao.setAtivo(locacaoDto.getAtivo());
        locacao.setObs(locacaoDto.getObs());
        Cliente cliente = clienteService.findByIdORTrowBadRequestException(locacaoDto.getCliente().getId());
        Imoveis imovel = imoveisService.findByIdORTrowBadRequestException(locacaoDto.getCliente().getId());

        locacao.setCliente(cliente);
        locacao.setImovel(imovel);

        return locacaoService.save(locacao);

    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        locacaoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity <Void> replace(@RequestBody @Valid Locacao locacao){
        locacaoService.replace(locacao);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
