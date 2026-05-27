class PacienteUrgencia{

    private int codigo_triage;
    private String nombre;
    private String identificacion;
    private String contacto;

    PacienteUrgencia(int codigo_triage, String nombre, String identificacion, String contacto){
        this.codigo_triage= codigo_triage;
        this.contacto=contacto;
        this.identificacion=identificacion;
        this.nombre=nombre;

    }

    public int getCodigo_triage() {
        return codigo_triage;
    }

    public void setCodigo_triage(int codigo_triage) {
        this.codigo_triage = codigo_triage;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "PacienteUrgencia{" +
                "codigo_triage=" + codigo_triage +
                ", nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }

    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        PacienteUrgencia that = (PacienteUrgencia) object;
        return codigo_triage == that.codigo_triage && java.util.Objects.equals(nombre, that.nombre) && java.util.Objects.equals(identificacion, that.identificacion) && java.util.Objects.equals(contacto, that.contacto);
    }

    
}