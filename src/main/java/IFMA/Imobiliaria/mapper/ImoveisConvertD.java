package IFMA.Imobiliaria.mapper;

/*
 * CLASSE UTILIZADA PARA CONVERTER ImovelInput em Imoveis para depois enviar ao Service
 * */


import IFMA.Imobiliaria.dtos.ImovelInput;
import IFMA.Imobiliaria.model.Imoveis;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImoveisConvertD {

    @Autowired
    private ModelMapper modelMapper;

    //recebe um objeto input e convert para um objeto do modelo de domínio
    public Imoveis convert_model(ImovelInput imovelInput){
        return modelMapper.map(imovelInput, Imoveis.class);
    }

    public void copy_paraClienteDomain(ImovelInput imovelInput, Imoveis imovel){
        modelMapper.map(imovelInput, imovel);
    }
}
