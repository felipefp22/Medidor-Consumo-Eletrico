package br.com.fiap.techchallenge.service;

import br.com.fiap.techchallenge.infra.login.dtosLogin.AuthenticationDTO;
import br.com.fiap.techchallenge.infra.login.dtosLogin.RegisterDTO;
import br.com.fiap.techchallenge.domain.entidade.Usuario;
import br.com.fiap.techchallenge.infra.login.service.TokenService;
import br.com.fiap.techchallenge.infra.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Transactional
    public String login(AuthenticationDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return token;
    }
    @Transactional
    public Usuario criarUsuario(RegisterDTO registerDTO) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());

        Usuario usuario = new Usuario(registerDTO.login(), encryptedPassword, registerDTO.role());

        return usuarioRepository.save(usuario);
    }

//    @Transactional
//    public void alterarSenha(Long id, String senha) {
//        try {
//            Usuario usuario = usuarioRepository.getReferenceById(id);
//            usuario.alterarSenha(senha);
//        } catch (EntityNotFoundException e) {
//            throw new ControllerNotFoundException("Usuario id: " + id + "não encontrado");
//        }
//    }
//
//    public void delete(Long id) {
//        try {
//            Optional<Usuario> usuario = usuarioRepository.findById(id);
//
//            usuarioRepository.delete(usuario.orElseThrow());
//
//        } catch (EmptyResultDataAccessException e) {
//            throw new EntityNotFoundException("Violação de Integridade da Base - ID: " + id);
//        } catch (DataIntegrityViolationException e) {
//            throw new DatabaseException("Violação de Integridade da Base");
//        }
//    }
}
