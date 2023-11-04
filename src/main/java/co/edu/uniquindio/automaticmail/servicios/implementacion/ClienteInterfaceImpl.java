package co.edu.uniquindio.automaticmail.servicios.implementacion;

import co.edu.uniquindio.automaticmail.modelo.dto.ClienteDTO;
import co.edu.uniquindio.automaticmail.modelo.entidades.Cliente;
import co.edu.uniquindio.automaticmail.repositorios.ClienteRepo;
import co.edu.uniquindio.automaticmail.repositorios.UsuarioRepo;
import co.edu.uniquindio.automaticmail.servicios.interfaces.ClienteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteInterfaceImpl implements ClienteInterface {

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ClienteRepo clienteRepo;

    @Override
    public int registrarCliente(ClienteDTO clienteDTO) throws Exception {

        Optional<Cliente> banderaNumDocumento = clienteRepo.findById(clienteDTO.getNumDocumento());

        if (banderaNumDocumento != null){
            throw new Exception("El cliente con id " + clienteDTO.getNumDocumento() + " ya existe");
        }

        Cliente cliente = new Cliente();
        cliente.setNumDocumento(clienteDTO.getNumDocumento());
        cliente.setNombres(clienteDTO.getNombres());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setCelular(clienteDTO.getCelular());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setEstado(true);
        cliente.setUsuario(usuarioRepo.buscarUsuarioPorId(clienteDTO.getIdUsuario()));

        clienteRepo.save(cliente);

        return cliente.getNumDocumento();
    }

    @Override
    public int actualizarCliente(int numDocumento, ClienteDTO clienteDTO) throws Exception {

        Optional<Cliente> buscarCliente = clienteRepo.findById(numDocumento);

        if (buscarCliente.isEmpty()) {
            throw new Exception("El cliente con id " + numDocumento + " no existe");
        }

        Cliente clienteActualizado = buscarCliente.get();

        clienteActualizado.setNumDocumento(clienteDTO.getNumDocumento());
        clienteActualizado.setNombres(clienteDTO.getNombres());
        clienteActualizado.setApellidos(clienteDTO.getApellidos());
        clienteActualizado.setCelular(clienteDTO.getCelular());
        clienteActualizado.setCorreo(clienteDTO.getCorreo());

        clienteRepo.save(clienteActualizado);

        return clienteActualizado.getNumDocumento();
    }

    @Override
    public boolean eliminarCliente(int numDocumento) throws Exception {

        Optional<Cliente> buscarCliente = clienteRepo.findById(numDocumento);

        if (buscarCliente.isEmpty()) {
            throw new Exception("El cliente con id " + numDocumento + " no existe");
        }

        Cliente clienteEliminado = buscarCliente.get();

        clienteEliminado.setEstado(false);

        clienteRepo.save(clienteEliminado);

        return clienteEliminado.isEstado();
    }

    @Override
    public List<ClienteDTO> listarClientesPorUsuario(int idUsuario) {
        List<Cliente> listaClientes = clienteRepo.listarClientesActivos(idUsuario);
        List<ClienteDTO> listaClientesDTO = new ArrayList<>();

        for (Cliente cliente : listaClientes) {
            listaClientesDTO.add(convertirADTO(cliente));
        }

        return listaClientesDTO;
    }

    @Override
    public ClienteDTO getClienteDTO(int numDocumento) throws Exception {

        Optional<Cliente> buscarCliente = clienteRepo.findById(numDocumento);

        if (buscarCliente.isEmpty()) {
            throw new Exception("El cliente con id " + numDocumento + " no existe");
        }

        return convertirADTO(buscarCliente.get());
    }

    private ClienteDTO convertirADTO(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO(
                cliente.getNumDocumento(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getCelular(),
                cliente.getCorreo(),
                cliente.isEstado(),
                cliente.getUsuario().getId()
        );

        return clienteDTO;
    }
}
