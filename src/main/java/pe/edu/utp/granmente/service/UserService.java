package pe.edu.utp.granmente.service;

import pe.edu.utp.granmente.dto.auth.RegisterForm;
import pe.edu.utp.granmente.entity.Rol;
import pe.edu.utp.granmente.entity.Usuario;

public interface UserService {
    Usuario register(RegisterForm form, Rol rol);
    Usuario getCurrentUser(String email);
    boolean confirmIdentity(String email, String nombre, String grado, String password);
    void markLogin(String email);
}
