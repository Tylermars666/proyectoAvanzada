package co.edu.uniquindio.proyecto.model.docs;

import co.edu.uniquindio.proyecto.model.enums.EstadoArtista;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("artistas")
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Artista {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String nombre;
    private String genero;
    private String email;
    private String telefono;
    private EstadoArtista estado;


    @Builder
    public Artista(String nombre, String genero, String email, String telefono, EstadoArtista estado) {

        this.nombre = nombre;
        this.genero = genero;
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;

    }



}
