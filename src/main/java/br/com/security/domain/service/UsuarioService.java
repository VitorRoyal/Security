package br.com.security.domain.service;

import br.com.security.domain.entity.Grupo;
import br.com.security.domain.entity.Usuario;
import br.com.security.domain.entity.UsuarioGrupo;
import br.com.security.domain.repository.GrupoRepository;
import br.com.security.domain.repository.UsuarioGrupoRepository;
import br.com.security.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario, List<String> grupos){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
            Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
            if (possivelGrupo.isPresent()) {
                Grupo grupo = possivelGrupo.get();
                return new UsuarioGrupo(usuario, grupo);
            }
            return null;
        })
                .filter(grupo -> grupo != null)
                .collect(Collectors.toList());
        usuarioGrupoRepository.saveAll(listaUsuarioGrupo);
        return usuario;
    }

    public Usuario obterUsuarioComPermissao(String login) {
        Optional<Usuario> possivelUsuario = usuarioRepository.findByLogin(login);
        if (possivelUsuario.isEmpty()) {
            return null;
        }
        Usuario usuario = possivelUsuario.get();
        List<String> permissoes = usuarioGrupoRepository.findPermissoesByUsuario(usuario);
        usuario.setPermissoes(permissoes);
        return usuario;
    }
}
