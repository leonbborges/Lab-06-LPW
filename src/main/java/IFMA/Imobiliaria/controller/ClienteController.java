package IFMA.Imobiliaria.controller;

import IFMA.Imobiliaria.model.Cliente;
import IFMA.Imobiliaria.service.ClienteService;
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
import java.util.List;

@RestController
@RequestMapping("/clientes")
@Log4j2
@RequiredArgsConstructor
public class ClienteController {
    @Autowired
    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<Cliente>> list(@RequestParam(required = false) String nome,
                                              @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 5)
                                                      Pageable pageable){
        return ResponseEntity.ok(clienteService.listAll(pageable));
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity <Cliente> findById(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.findByIdORTrowBadRequestException(id));
    }
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid Cliente cliente){
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);

    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity <Void> replace(@RequestBody @Valid Cliente cliente){
        clienteService.replace(cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
