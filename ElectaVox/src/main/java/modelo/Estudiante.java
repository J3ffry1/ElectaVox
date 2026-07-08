package modelo;

public class Estudiante {

    private String cedula;
    private String nombres;
    private String apellidos;
    private String correo;
    private String curso;
private String genero;

    public Estudiante() {
    }

    public Estudiante(String cedula,
                  String nombres,
                  String apellidos,
                  String correo,
                  String curso,
                  String genero) {

    this.cedula = cedula;
    this.nombres = nombres;
    this.apellidos = apellidos;
    this.correo = correo;
    this.curso = curso;
    this.genero = genero;
}
    

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCurso() {
    return curso;
}

public void setCurso(String curso) {
    this.curso = curso;
}

public String getGenero() {
    return genero;
}

public void setGenero(String genero) {
    this.genero = genero;
}
}
