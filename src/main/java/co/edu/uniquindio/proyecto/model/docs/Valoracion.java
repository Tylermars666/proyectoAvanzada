package co.edu.uniquindio.proyecto.model.docs;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("valoraciones")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Valoracion {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private ObjectId evento;
    private ObjectId usuario;
    private int puntuacion;
    private String comentario;
    private LocalDateTime fecha;

    @Builder
    public Valoracion(ObjectId evento, ObjectId usuario, int puntuacion, String comentario, LocalDateTime fecha) {

        this.evento = evento;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }
}
