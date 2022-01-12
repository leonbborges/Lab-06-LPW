package IFMA.Imobiliaria.mapper;


import IFMA.Imobiliaria.dtos.ClienteDto;
import IFMA.Imobiliaria.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
* CLASSE UTILIZADA PARA CONVERTER Cliente em ClienteDto para depois devolver ao controller
* */

@Component
public class ClienteConvertA {

    @Autowired
    private ModelMapper modelMapper;

    public ClienteDto convert_DTO(Cliente cliente){
        return modelMapper.map(cliente, ClienteDto.class);
    }

    public List<ClienteDto> convert_Lista_para_DTO(List<Cliente> clientes) {
        return clientes.stream()
                .map(cliente -> convert_DTO(cliente))
                .collect(Collectors.toList());
    }

}
