package org.example.serviciotecnico.Controller;

import org.example.serviciotecnico.Service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tecnico")
public class TecnicoServiceController {

    @Autowired
    TecnicoService tecnicoService;
}
