package br.com.fiap.techchallenge.controller;

import br.com.fiap.techchallenge.infra.login.dtosLogin.AuthenticationDTO;
import br.com.fiap.techchallenge.infra.login.dtosLogin.LoginResponseDTO;
import br.com.fiap.techchallenge.infra.login.dtosLogin.RegisterDTO;
import br.com.fiap.techchallenge.infra.repository.UsuarioRepository;
import br.com.fiap.techchallenge.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {

        String token = usuarioService.login(data);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){

        if(usuarioRepository.findByLogin(registerDTO.login()) != null) return ResponseEntity.badRequest().build();

        System.out.println(usuarioService.criarUsuario(registerDTO));

        return ResponseEntity.ok().build();

    }

//    @PostMapping
//    public ResponseEntity<Void> cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO, UriComponentsBuilder uriBuilder){
//        if(usuarioService.)
//        Usuario usuario = usuarioService.criarUsuario(usuarioDTO);
//        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
//        return ResponseEntity.created(uri).build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> alterarSenha(@PathVariable Long id,
//                                             @RequestBody @Valid AlteracaoSenhaDTO alteracaoSenhaDTO){
//        usuarioService.alterarSenha(id, alteracaoSenhaDTO.senha());
//        return ResponseEntity.ok().build();
//    }

}
