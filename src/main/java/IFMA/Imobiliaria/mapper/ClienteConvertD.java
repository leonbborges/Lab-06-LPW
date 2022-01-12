package IFMA.Imobiliaria.mapper;

/*
 * CLASSE UTILIZADA PARA CONVERTER ClienteInput em Cliente para depois enviar ao Service
 * */


import IFMA.Imobiliaria.dtos.ClienteInput;
import IFMA.Imobiliaria.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteConvertD {

    @Autowired
    private ModelMapper modelMapper;

    //recebe um objeto input e convert para um objeto do modelo de domínio
    public Cliente convert_model(ClienteInput clienteInput){
        return modelMapper.map(clienteInput, Cliente.class);
    }

    public void copy_paraClienteDomain(ClienteInput clienteInput, Cliente cliente){
        modelMapper.map(clienteInput, cliente);
    }
}
