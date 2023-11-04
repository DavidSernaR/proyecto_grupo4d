package co.edu.uniquindio.automaticmail.servicios.implementacion;

import co.edu.uniquindio.automaticmail.modelo.dto.PolizaDTO;
import co.edu.uniquindio.automaticmail.modelo.entidades.Poliza;
import co.edu.uniquindio.automaticmail.repositorios.ClienteRepo;
import co.edu.uniquindio.automaticmail.repositorios.PolizaRepo;
import co.edu.uniquindio.automaticmail.servicios.interfaces.PolizaInterface;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class PolizaInterfaceImpl implements PolizaInterface {

    @Autowired
    private ClienteRepo clienteRepo;
    @Autowired
    private PolizaRepo polizaRepo;

    private final Cloudinary cloudinary;

    public PolizaInterfaceImpl(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dqhhcmdiw");
        config.put("api_key", "125679164937565");
        config.put("api_secret", "-iyxYvgMBlbm394NEEB1BjFIePM");
        cloudinary = new Cloudinary(config);
    }

    @Override
    public Map subirArchivo(File archivo, String directory, PolizaDTO polizaDTO) throws Exception {

        Map<String, Object> uploadResult = cloudinary.uploader().upload(archivo, ObjectUtils.asMap("folder", directory));

        String publicUrl = (String) uploadResult.get("url");

        polizaDTO.setUrl(publicUrl);

        Poliza poliza = new Poliza();
        poliza.setNombre(polizaDTO.getNombre());
        poliza.setUrl(polizaDTO.getUrl());
        poliza.setEstado(true);
        poliza.setCliente(clienteRepo.buscarClientePorNumDocumento(polizaDTO.getNumDocumentoCliente()));

        polizaRepo.save(poliza);

        return uploadResult;
    }

    @Override
    public Map eliminarArchivo(String publicId, int idPoliza) throws Exception {

        Optional<Poliza> buscarPoliza = polizaRepo.findById(idPoliza);

        if (buscarPoliza.isEmpty()) {
            throw new Exception("La poliza con id " + idPoliza + " no existe");
        }

        Poliza polizaEliminada = buscarPoliza.get();

        polizaEliminada.setEstado(false);

        polizaRepo.save(polizaEliminada);

        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    @Override
    public List<PolizaDTO> listarPolizasPorUsuarios(int idUsuario) {
        List<Poliza> listaPolizas = polizaRepo.listarPolizasActivasPorNumeroDocumentoCliente(idUsuario);
        List<PolizaDTO> listaPolizasDTO = new ArrayList<>();

        for (Poliza poliza : listaPolizas) {
            listaPolizasDTO.add(convertirADTO(poliza));
        }

        return listaPolizasDTO;
    }

    @Override
    public PolizaDTO getPolizaDTO(int idPoliza) throws Exception {

        Optional<Poliza> buscarPoliza = polizaRepo.findById(idPoliza);

        if (buscarPoliza.isEmpty()) {
            throw new Exception("La poliza con id " + idPoliza + " no existe");
        }

        return convertirADTO(buscarPoliza.get());
    }

    @Override
    public File convert(MultipartFile file) throws IOException {
        File archivo = File.createTempFile(file.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(archivo);
        fos.write(file.getBytes());
        fos.close();
        return archivo;
    }

    private PolizaDTO convertirADTO(Poliza poliza) {
        PolizaDTO polizaDTO = new PolizaDTO(
                poliza.getId(),
                poliza.getNombre(),
                poliza.getPublicId(),
                poliza.getUrl(),
                poliza.isEstado(),
                poliza.getCliente().getNumDocumento()
        );

        return polizaDTO;
    }
}
