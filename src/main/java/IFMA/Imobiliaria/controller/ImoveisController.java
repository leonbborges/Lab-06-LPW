package IFMA.Imobiliaria.controller;

import IFMA.Imobiliaria.dtos.ImoveisDto;
import IFMA.Imobiliaria.dtos.ImovelInput;
import IFMA.Imobiliaria.mapper.ImoveisConvertA;
import IFMA.Imobiliaria.mapper.ImoveisConvertD;
import IFMA.Imobiliaria.model.Imoveis;
import IFMA.Imobiliaria.service.ImoveisService;
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
@RequestMapping("/imoveis")
@Log4j2
@RequiredArgsConstructor
public class ImoveisController {
    @Autowired
    private final ImoveisService imoveisService;
    @Autowired
    private ImoveisConvertA imoveisConvertAssembler;

    @Autowired
    private ImoveisConvertD imoveisConvertDISAssembler;

    @GetMapping
    public ResponseEntity<Page<Imoveis>> list(@RequestParam(required = false) String nome,
                                              @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 5)
                                                      Pageable pageable){
        return ResponseEntity.ok(imoveisService.listAll(pageable));
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity <Imoveis> findById(@PathVariable Long id){
        return ResponseEntity.ok(imoveisService.findByIdORTrowBadRequestException(id));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImoveisDto save (@RequestBody @Valid ImovelInput imovelInput) {
        return imoveisConvertAssembler
                .convert_DTO(imoveisService.save(
                        imoveisConvertDISAssembler.convert_model(imovelInput)
                ));
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        imoveisService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity <Void> replace(@RequestBody @Valid Imoveis imoveis){
        imoveisService.replace(imoveis);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
