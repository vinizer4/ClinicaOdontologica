package io.github.vinizer4;

import io.github.vinizer4.rest.controller.ConsultaController;
import io.github.vinizer4.rest.controller.DentistaController;
import io.github.vinizer4.rest.controller.PacienteController;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class OdontoTest {

    @Autowired
    PacienteController pacienteController;
    @Autowired
    DentistaController dentistaController;
    @Autowired
    ConsultaController consultaController;


@Test
    void  contextLoads(){

    Assertions.assertThat(pacienteController).isNotNull();
    Assertions.assertThat(dentistaController).isNotNull();
    Assertions.assertThat(consultaController).isNotNull();

}



    
}
