package IFMA.Imobiliaria.controller;


import IFMA.Imobiliaria.dtos.LocacaoDto;
import IFMA.Imobiliaria.dtos.LocacaoInput;
import IFMA.Imobiliaria.mapper.LocacaoConvertA;
import IFMA.Imobiliaria.mapper.LocacaoConvertD;
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
    private LocacaoConvertA locacaoConvertAssembler;

    @Autowired
    private LocacaoConvertD locacaoConvertD;

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
    public LocacaoDto save(@RequestBody @Valid LocacaoInput locacaoInput){
        return locacaoConvertAssembler
                .convert_DTO(locacaoService.save(
                        locacaoConvertD.convert_model(locacaoInput)
                ));

    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        locacaoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(path = "/{id}")
    public LocacaoDto replace (@PathVariable Long Id, @RequestBody @Valid LocacaoInput locacaoInput){
        Locacao locacaoAtual = locacaoService.findByIdORTrowBadRequestException(Id);

        locacaoConvertD.copiar_model(locacaoInput, locacaoAtual);

        return locacaoConvertAssembler
                .convert_DTO(locacaoService.save(locacaoAtual));
    }
}
