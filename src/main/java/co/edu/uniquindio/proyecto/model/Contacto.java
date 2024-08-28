package co.edu.uniquindio.proyecto.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contacto {

    private String email;
    private String telefono;
    private String web;
}
