package IFMA.Imobiliaria.mapper;

/*
 * CLASSE UTILIZADA PARA CONVERTER ImovelInput em Imoveis para depois enviar ao Service
 * */


import IFMA.Imobiliaria.dtos.LocacaoInput;
import IFMA.Imobiliaria.model.Cliente;
import IFMA.Imobiliaria.model.Imoveis;
import IFMA.Imobiliaria.model.Locacao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocacaoConvertD {

    @Autowired
    private ModelMapper modelMapper;

    //recebe um objeto input e convert para um objeto do modelo de domínio
    public Locacao convert_model(LocacaoInput locacaoInput){
        return modelMapper.map(locacaoInput, Locacao.class);
    }

    public void copiar_model(LocacaoInput locacaoInput, Locacao locacao){
        locacao.setCliente(new Cliente());
        locacao.setImovel(new Imoveis());

        modelMapper.map(locacaoInput, locacao);
    }
}
