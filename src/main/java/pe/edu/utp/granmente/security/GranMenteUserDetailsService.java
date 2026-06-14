package pe.edu.utp.granmente.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.utp.granmente.entity.Usuario;
import pe.edu.utp.granmente.repository.UsuarioRepository;

@Service
public class GranMenteUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public GranMenteUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));
        return new User(usuario.getEmail(), usuario.getPasswordHash(), usuario.isActivo(), true, true, true,
                java.util.List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name())));
    }
}
