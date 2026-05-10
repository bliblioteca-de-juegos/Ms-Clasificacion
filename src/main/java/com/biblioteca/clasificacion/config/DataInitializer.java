package com.biblioteca.clasificacion.config;

import com.biblioteca.clasificacion.model.Clasificacion;
import com.biblioteca.clasificacion.repository.ClasificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ClasificacionRepository clasificacionRepository;

    @Override
    public void run(String... args) {
        if (clasificacionRepository.count() > 0) {
            return;
        }

        clasificacionRepository.save(new Clasificacion(null, "PEGI 3", "ESRB E", 3,
                "Todo publico", "Contenido apto para la mayoria de usuarios."));
        clasificacionRepository.save(new Clasificacion(null, "PEGI 7", "ESRB E10+", 7,
                "Mayores de 7", "Puede incluir escenas o sonidos que asusten a ninos pequenos."));
        clasificacionRepository.save(new Clasificacion(null, "PEGI 12", "ESRB T", 12,
                "Adolescentes", "Puede incluir violencia moderada o lenguaje leve."));
        clasificacionRepository.save(new Clasificacion(null, "PEGI 16", "ESRB T/M", 16,
                "Mayores de 16", "Puede incluir violencia mas realista o temas maduros."));
        clasificacionRepository.save(new Clasificacion(null, "PEGI 18", "ESRB M/AO", 18,
                "Adultos", "Contenido para adultos, violencia intensa o temas sensibles."));
    }
}
