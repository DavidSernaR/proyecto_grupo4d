package co.edu.uniquindio.automaticmail.servicios.interfaces;

import co.edu.uniquindio.automaticmail.modelo.dto.ClienteDTO;

import java.util.List;

public interface ClienteInterface {
    public int registrarCliente(ClienteDTO clienteDTO) throws Exception;
    public int actualizarCliente(int numDocumento, ClienteDTO clienteDTO) throws Exception;
    public boolean eliminarCliente(int numDocumento) throws Exception;
    public List<ClienteDTO> listarClientesPorUsuario(int idUsuario);
    public ClienteDTO getClienteDTO(int numDocumento) throws Exception;
}
