package modelo;

public class Candidato {

    private String cedula;
    private String nombres;
    private String apellidos;
    private String lista;
    private String propuestas;
    private String fotografia;

    public Candidato() {
    }

    public Candidato(String cedula,
                     String nombres,
                     String apellidos,
                     String lista,
                     String propuestas,
                     String fotografia) {

        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.lista = lista;
        this.propuestas = propuestas;
        this.fotografia = fotografia;
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

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public String getPropuestas() {
        return propuestas;
    }

    public void setPropuestas(String propuestas) {
        this.propuestas = propuestas;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }
}