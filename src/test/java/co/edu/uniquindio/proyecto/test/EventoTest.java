package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.model.docs.Evento;
import co.edu.uniquindio.proyecto.model.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.model.enums.TipoEvento;
import co.edu.uniquindio.proyecto.model.vo.Imagen;
import co.edu.uniquindio.proyecto.model.vo.Localidad;
import co.edu.uniquindio.proyecto.repositories.EventoRepo;
import jdk.jfr.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EventoTest {

    @Autowired
    private EventoRepo eventoRepo;

    @Test
    public void registrarEventoTest(){

        List<Imagen> imagenes = new ArrayList<>();
        imagenes.add(new Imagen("imagen.com", "Portada Rock in Torta","La media torta de la universidad del Quindío"));
        List<Localidad> localidades = new ArrayList<>();
        localidades.add(new Localidad("Unica",10000,500,300,true,"La única localidad que existe","imagen2.com"));


        Evento evento = Evento.builder()
                .nombre("Rock in Torta")
                .direccion("Cra 15, Uniquindio")
                .ciudad("Armenia")
                .descripcion("Un evento de rock dentro de la universidad del Quindío")
                .tipoEvento(TipoEvento.CONCIERTO)
                .imagenes(imagenes)
                .fecha(LocalDateTime.now())
                .localidades(localidades)
                .estadoEvento(EstadoEvento.ACTIVO)
                .build();

        Evento eventoRegistro = eventoRepo.save(evento);

        assertNotNull(eventoRegistro);



    }

}

