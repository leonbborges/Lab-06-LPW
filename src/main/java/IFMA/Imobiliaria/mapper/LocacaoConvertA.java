package IFMA.Imobiliaria.mapper;


import IFMA.Imobiliaria.dtos.LocacaoDto;
import IFMA.Imobiliaria.model.Locacao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
* CLASSE UTILIZADA PARA CONVERTER Locacao em LocacaoDto para depois devolver ao controller
* */

@Component
public class LocacaoConvertA {

    @Autowired
    private ModelMapper modelMapper;

    public LocacaoDto convert_DTO(Locacao locacao){
        return modelMapper.map(locacao, LocacaoDto.class);
    }

    public List<LocacaoDto> convert_Lista_para_DTO(List<Locacao> locacoes) {
        return locacoes.stream()
                .map(locacao -> convert_DTO(locacao))
                .collect(Collectors.toList());
    }

}
