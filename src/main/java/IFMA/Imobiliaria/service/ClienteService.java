package IFMA.Imobiliaria.service;

import IFMA.Imobiliaria.handler.BadRequestEx;
import IFMA.Imobiliaria.model.Cliente;
import IFMA.Imobiliaria.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> listAll(Pageable pageable){
        return clienteRepository.findAll(pageable);

    }
    public Cliente findByIdORTrowBadRequestException(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(()-> new BadRequestEx("Cliente ID not found"));

    }

    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        clienteRepository.delete(findByIdORTrowBadRequestException(id));
    }

    public void replace(Cliente cliente) {
        Cliente saveCliente= findByIdORTrowBadRequestException(cliente.getId());
        Cliente cliente1 = Cliente.builder()
                .nome_cliente(cliente.getNome_cliente())
                .cpf(cliente.getCpf())
                .dt_nascimento(cliente.getDt_nascimento())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .build();

        clienteRepository.save(cliente1);
    }
}
