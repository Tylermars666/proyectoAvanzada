package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.eventoDTO.*;
import co.edu.uniquindio.proyecto.model.enums.EstadoEvento;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventoService {

    String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception;

    String editarEvento(EditarEventoDTO editarEventoDTO) throws Exception;

    String eliminarEvento(String id) throws Exception;

    InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception;

    List<ResumenEventoDTO> listarEventos() throws Exception;

    List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroDTO) throws Exception;

    void cambiarEstadoEvento(String id, EstadoEvento nuevoEstado) throws Exception;

    List<ResumenEventoDTO> buscarEventosPorNombre(String nombre);

    void agregarImagenEvento(String idEvento, MultipartFile imagen) throws Exception;

    void agregarImagenLocalidad(String idEvento, String nombreLocalidad, MultipartFile imagen) throws Exception;

    byte[] generarReporteVentasHTML(String idEvento) throws Exception;

    byte[] generarReporteVentasPDF(String idEvento) throws Exception;

    byte[] generarReporteVentasXML(String idEvento) throws Exception;





    //TODO

}
