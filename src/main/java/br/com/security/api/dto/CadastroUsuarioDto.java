package br.com.security.api.dto;

import br.com.security.domain.entity.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUsuarioDto {
    private Usuario usuario;
    private List<String> permissoes;

}
