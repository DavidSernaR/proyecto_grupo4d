package co.edu.uniquindio.automaticmail.servicios.implementacion;

import co.edu.uniquindio.automaticmail.modelo.dto.PlantillaDTO;
import co.edu.uniquindio.automaticmail.modelo.entidades.Plantilla;
import co.edu.uniquindio.automaticmail.modelo.entidades.Poliza;
import co.edu.uniquindio.automaticmail.modelo.entidades.Usuario;
import co.edu.uniquindio.automaticmail.repositorios.ClienteRepo;
import co.edu.uniquindio.automaticmail.repositorios.PlantillaRepo;
import co.edu.uniquindio.automaticmail.repositorios.PolizaRepo;
import co.edu.uniquindio.automaticmail.repositorios.UsuarioRepo;
import co.edu.uniquindio.automaticmail.servicios.interfaces.EmailInterface;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailInterfaceImpl implements EmailInterface {

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ClienteRepo clienteRepo;
    @Autowired
    private PolizaRepo polizaRepo;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PlantillaRepo plantillaRepo;

    @Override
    public void enviarCorreo(PlantillaDTO plantillaDTO) throws Exception {

        Poliza poliza = polizaRepo.buscarPolizaPorId(plantillaDTO.getIdPoliza());

        System.out.println(poliza.getUrl());

        String cloudinaryUrl = poliza.getUrl();
        String nombrePoliza = poliza.getNombre();
        byte[] pdfFile = downloadBytesFromUrl(cloudinaryUrl);
        File adjunto = convertByteArrayToFile(pdfFile, nombrePoliza+".pdf");

        if (pdfFile == null) {
            throw new Exception("Error al descargar el archivo desde Cloudinary");
        }

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(clienteRepo.buscarClientePorNumDocumento(plantillaDTO.getNumDocumentoCliente()).getCorreo());
            helper.setSubject(plantillaDTO.getAsunto());
            helper.setText(plantillaDTO.getContenido(), true);
            helper.setFrom("no_reply@dominio.com");
            helper.addAttachment(nombrePoliza + ".pdf", adjunto);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Plantilla plantilla = new Plantilla();
        plantilla.setAsunto(plantillaDTO.getAsunto());
        plantilla.setContenido(plantillaDTO.getContenido());
        plantilla.setCliente(clienteRepo.buscarClientePorNumDocumento(plantillaDTO.getNumDocumentoCliente()));
        plantilla.setPoliza(polizaRepo.buscarPolizaPorId(plantillaDTO.getIdPoliza()));
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuarioRepo.buscarUsuarioPorId(plantillaDTO.getIdUsuario()));
        plantilla.setUsuarios(usuarios);

        plantillaRepo.save(plantilla);

    }

    public static File convertByteArrayToFile(byte[] byteArray, String filePath) throws IOException {
        File file = new File(filePath);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(byteArray);
        }

        return file;
    }

    public static byte[] downloadBytesFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);

        try (InputStream inputStream = url.openStream(); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            return byteArrayOutputStream.toByteArray();
        }
    }

}
