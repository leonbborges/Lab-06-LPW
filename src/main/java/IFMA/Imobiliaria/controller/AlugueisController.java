package IFMA.Imobiliaria.controller;

import IFMA.Imobiliaria.model.Alugueis;
import IFMA.Imobiliaria.model.Locacao;
import IFMA.Imobiliaria.service.AlugueisService;
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
@RequestMapping("/alugueis")
@Log4j2
@RequiredArgsConstructor
public class AlugueisController {
    @Autowired
    private  final AlugueisService alugueisService;

    @GetMapping
    public ResponseEntity<Page<Alugueis>> list(@RequestParam(required = false) String nome,
                                               @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 5)
                                                       Pageable pageable){
        return ResponseEntity.ok(alugueisService.listAll(pageable));
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity <Alugueis> findById(@PathVariable Long id){
        return ResponseEntity.ok( alugueisService.findByIdORTrowBadRequestException(id));
    }
    @PostMapping
    public ResponseEntity<Alugueis> save(@RequestBody @Valid Alugueis alugueis){
        return new ResponseEntity<>(alugueisService.save(alugueis), HttpStatus.CREATED);

    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        alugueisService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity <Void> replace(@RequestBody @Valid Alugueis alugueis){
        alugueisService.replace(alugueis);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
