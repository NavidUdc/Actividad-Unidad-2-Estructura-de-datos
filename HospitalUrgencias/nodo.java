class nodo{
    private Object dato;
    private nodo der;
    private nodo izq;


    public nodo(){

    }

    public nodo (Object dato) {
        this.dato=dato;
    }
    public void setDato(Object dato) {
        this.dato = dato;
    }

    public void setDer(nodo vecino) {
        this.der = vecino;
    }

    public void setIzq(nodo vecino) {
        this.izq = vecino;
    }

    public Object getDato() {
        return dato;
    }

    public nodo getDer() {
        return der;
    }

    public nodo getIzq() {
        return izq;
    }
}