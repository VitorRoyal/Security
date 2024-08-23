package br.com.security.api.controller;

import br.com.security.api.dto.CadastroUsuarioDto;
import br.com.security.domain.entity.Usuario;
import br.com.security.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> salvar(@RequestBody CadastroUsuarioDto cadastroUsuarioDto) {
        Usuario usuarioSalvo = usuarioService.salvar(cadastroUsuarioDto.getUsuario(), cadastroUsuarioDto.getPermissoes());
        return ResponseEntity.ok(usuarioSalvo);
    }
}
