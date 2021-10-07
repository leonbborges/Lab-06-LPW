package IFMA.Imobiliaria.controller;


import IFMA.Imobiliaria.model.Locacao;
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
    public ResponseEntity<Locacao> save(@RequestBody @Valid Locacao locacao){
        return new ResponseEntity<>(locacaoService.save(locacao), HttpStatus.CREATED);

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
