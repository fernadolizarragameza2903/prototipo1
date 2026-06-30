package pe.edu.utp.granmente.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterForm {
    @NotBlank
    private String nombre;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String grado;
    @Size(min = 5)
    private String password;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
