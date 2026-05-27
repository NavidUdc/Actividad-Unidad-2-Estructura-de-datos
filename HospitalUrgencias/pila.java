public class pila {
    private int cuenta;
    private nodo cima;

    public pila() {
        cuenta = 0;
        cima = null;
    }

    public boolean esVacia() {
        return cuenta == 0;
    }

    // apilar / push
    public void apilar(Object dato) throws Exception {
        if (dato == null)
            throw new Exception("El dato no puede ser nulo");

        nodo nuevo = new nodo(dato);
        if (!esVacia()) {
            nuevo.setIzq(cima);
            cima.setDer(nuevo);
        }
        cima = nuevo;
        ++cuenta;
    }

    // desapilar / pop
    public Object desapilar() throws Exception {
        if (esVacia())
            throw new Exception("La pila está vacía");

        Object dato = cima.getDato();
        cima = cima.getIzq();
        if (cima != null) {
            cima.setDer(null);
        }
        --cuenta;
        return dato;
    }

    // peek / mostrar arriba
    public Object peek() throws Exception {
        if (esVacia())
            throw new Exception("La pila está vacía");
        return cima.getDato();
    }

    public int tamanio() {
        return cuenta;
    }

    // contiene
    public boolean contiene(Object dato) {
        nodo actual = cima;
        while (actual != null) {
            if (actual.getDato().equals(dato))
                return true;
            actual = actual.getIzq();
        }
        return false;
    }

    // buscar
    public int buscar(Object dato) throws Exception {
        nodo actual = cima;
        int posicion = 0;
        while (actual != null) {
            if (actual.getDato().equals(dato))
                return posicion;
            actual = actual.getIzq();
            posicion++;
        }
        throw new Exception("El elemento no está en la pila");
    }

    // linpiar
    public void limpiar() {
        cima = null;
        cuenta = 0;
    }

    // mostrar
    public void mostrar() throws Exception {
        if (esVacia())
            throw new Exception("La pila está vacía");

        nodo actual = cima;
        System.out.println("pila");
        while (actual != null) {
            System.out.println(" |  " + actual.getDato() + "  |");
            actual = actual.getIzq();
        }
    }
}