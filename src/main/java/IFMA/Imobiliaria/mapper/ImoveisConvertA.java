package IFMA.Imobiliaria.mapper;


import IFMA.Imobiliaria.dtos.ImoveisDto;
import IFMA.Imobiliaria.model.Imoveis;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
* CLASSE UTILIZADA PARA CONVERTER Imovel em ImovelDTO para depois devolver ao controller
* */

@Component
public class ImoveisConvertA {

    @Autowired
    private ModelMapper modelMapper;

    public ImoveisDto convert_DTO(Imoveis imovel){
        return modelMapper.map(imovel, ImoveisDto.class);
    }

    public List<ImoveisDto> convert_Lista_para_DTO(List<Imoveis> imoveis) {
        return imoveis.stream()
                .map(imovel -> convert_DTO(imovel))
                .collect(Collectors.toList());
    }

}
