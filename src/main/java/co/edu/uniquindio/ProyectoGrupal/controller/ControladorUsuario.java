package co.edu.uniquindio.ProyectoGrupal.controller;


import co.edu.uniquindio.ProyectoGrupal.dtos.CrearUsuarioDTO;
import co.edu.uniquindio.ProyectoGrupal.dtos.ResponseDTO;
import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import co.edu.uniquindio.ProyectoGrupal.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class ControladorUsuario {


    private final UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<ResponseDTO<String>> registrarUsuario(@RequestBody CrearUsuarioDTO usuario){
        usuarioService.registrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(false, "Usuario registrado"));
    }

    @GetMapping("/detalle/{email}")
    public ResponseEntity<ResponseDTO<Usuario>> obtenerUsuario(@PathVariable String email){
        Usuario usuario = usuarioService.obtenerUsuario(email);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(false, usuario));
    }

}
