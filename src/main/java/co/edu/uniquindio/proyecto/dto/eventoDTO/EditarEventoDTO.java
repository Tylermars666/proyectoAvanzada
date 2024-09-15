package co.edu.uniquindio.proyecto.dto.eventoDTO;

import co.edu.uniquindio.proyecto.model.enums.TipoEvento;
import java.time.LocalDateTime;
import java.util.List;

public record EditarEventoDTO(
        String id,
        String nombre,
        String artista,
        String descripcion,
        LocalDateTime fecha,
        String direccion,
        String ciudad,
        TipoEvento tipoEvento,
        List<LocalidadDTO> localidades
) {}