public class cola {
    private int cuenta;
    private nodo frente;
    private nodo  fin;

    public cola (){
        cuenta =0;
        frente = null;
        fin = null;

    }

    public boolean esVacia(){
        return cuenta == 0;
    }
        
    // eliminar al frente
    public Object desencolar () throws Exception{
        if (esVacia()) {
            throw new Exception("la cola esta vacia");
        }
        Object dato = frente.getDato();
        frente = frente.getDer();
        if (frente != null) {
            frente.setIzq(null);
        }else{
            fin = null;
        }

        --cuenta;
        return dato;
    }

    // agregar al final 
    public void encolar (Object dato)throws Exception{
        if (dato == null) {
            throw new Exception(" El dato no puede ser nulo");

        }
        nodo nuevo = new nodo(dato);

        if (esVacia()) {
            frente = nuevo;
            fin = nuevo;
        }else{
            fin.setDer(nuevo);
            nuevo.setIzq(fin);
            fin = nuevo;
        }
        ++ cuenta;
    }

    // consultar al frente

    public Object peek() throws Exception{
        if (esVacia()) {
            throw new Exception("La cola esta vacia");

        }
        return frente.getDato();
    }

    // conmsultar sio esta
    public boolean contiene(Object dato) {
        nodo actual = frente;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return true;
            actual = actual.getDer();
        }
        return false;
    }

    //limpiar cola
    public void limpiar() {
        frente = null;
        fin = null;
        cuenta = 0;
    }

    // informacion

    public int tamano(){
        return cuenta;
    }

    public void mostrar() throws Exception {
        if (esVacia()) throw new Exception("La cola está vacía");

        nodo actual = frente;
        System.out.print("[");
        while (actual != null) {
            System.out.print(actual.getDato());
            if (actual.getDer() != null) System.out.print(", ");
            actual = actual.getDer();
        }
        System.out.println("]");
    }
}