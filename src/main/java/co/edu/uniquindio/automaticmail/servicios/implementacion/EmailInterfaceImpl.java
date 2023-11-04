package co.edu.uniquindio.automaticmail.servicios.implementacion;

import co.edu.uniquindio.automaticmail.modelo.dto.PlantillaDTO;
import co.edu.uniquindio.automaticmail.modelo.dto.PolizaDTO;
import co.edu.uniquindio.automaticmail.repositorios.ClienteRepo;
import co.edu.uniquindio.automaticmail.servicios.interfaces.EmailInterface;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Service
public class EmailInterfaceImpl implements EmailInterface {

    @Autowired
    private ClienteRepo clienteRepo;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void enviarCorreo(PlantillaDTO plantillaDTO, PolizaDTO polizaDTO) throws Exception {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dqhhcmdiw",
                "api_key", "125679164937565",
                "api_secret", "-iyxYvgMBlbm394NEEB1BjFIePM"
        ));

        String cloudinaryUrl = polizaDTO.getUrl();
        File pdfFile = downloadFileFromUrl(cloudinaryUrl, polizaDTO.getNombre());

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
            helper.addAttachment(polizaDTO.getNombre() + ".pdf", pdfFile);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File downloadFileFromUrl(String url, String nombreArchivo) {
        try {
            URL fileUrl = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(fileUrl.openStream());
            File pdfFile = new File(nombreArchivo + ".pdf");

            try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }

            return pdfFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
