package br.com.security.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rh")
public class RhController {

    @GetMapping("/tecnico")
    @PreAuthorize("hasAnyRole('Tecnico_RH', 'Gerente_RH', 'ADMIN')")
    public ResponseEntity<String> tecnico(){
        return ResponseEntity.ok("Rota do tecnico");
    }

    @GetMapping("/gerente")
    @PreAuthorize("hasAnyRole('Gerente_RH', 'ADMIN')")
    public ResponseEntity<String> gerente(){
        return ResponseEntity.ok("Rota do gerente");
    }
}
