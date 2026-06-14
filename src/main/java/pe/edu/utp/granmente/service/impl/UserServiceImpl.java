package pe.edu.utp.granmente.service.impl;

import java.time.LocalDateTime;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.granmente.dto.auth.RegisterForm;
import pe.edu.utp.granmente.entity.Rol;
import pe.edu.utp.granmente.entity.Usuario;
import pe.edu.utp.granmente.exception.ResourceNotFoundException;
import pe.edu.utp.granmente.repository.UsuarioRepository;
import pe.edu.utp.granmente.service.ProgressService;
import pe.edu.utp.granmente.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProgressService progressService;

    public UserServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, ProgressService progressService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.progressService = progressService;
    }

    @Override
    public Usuario register(RegisterForm form, Rol rol) {
        if (usuarioRepository.existsByEmailIgnoreCase(form.getEmail())) {
            throw new IllegalArgumentException("El correo ya esta registrado.");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(form.getNombre());
        usuario.setEmail(form.getEmail().trim().toLowerCase());
        usuario.setGrado(form.getGrado());
        usuario.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        usuario.setRol(rol);
        Usuario saved = usuarioRepository.save(usuario);
        if (rol == Rol.ESTUDIANTE) {
            progressService.ensureProgress(saved);
        }
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getCurrentUser(String email) {
        return usuarioRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean confirmIdentity(String email, String nombre, String grado, String password) {
        Usuario usuario = getCurrentUser(email);
        return usuario.getNombre().equalsIgnoreCase(nombre.trim())
                && usuario.getGrado().equalsIgnoreCase(grado.trim())
                && passwordEncoder.matches(password, usuario.getPasswordHash());
    }

    @Override
    public void markLogin(String email) {
        Usuario usuario = getCurrentUser(email);
        usuario.setUltimoLogin(LocalDateTime.now());
    }
}
