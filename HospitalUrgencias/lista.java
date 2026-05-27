public class lista {
    private int cuenta;
    private nodo primero;
    private nodo ultimo;

    public boolean esVacia() {
        return cuenta == 0;
    }

    // --- METODOS PARA AGREGAR
    public void agregar(Object dato) throws Exception {
        if (dato == null) throw new Exception("El dato no puede estar vacío");

        nodo obj = new nodo(dato);
        if (esVacia()) {
            primero = obj;
            ultimo = obj; 
        } else {
            ultimo.setDer(obj);
            obj.setIzq(ultimo);
            ultimo = obj;
        }
        ++cuenta;
    }

    public void agregarAlInicio(Object dato) throws Exception {
        if (dato == null) throw new Exception("El dato no puede estar vacío");

        nodo obj = new nodo(dato);
        if (esVacia()) {
            primero = obj;
            ultimo = obj;
        } else {
            obj.setDer(primero);
            primero.setIzq(obj);
            primero = obj;
        }
        ++cuenta;
    }

    public void agregarEnPosicion(int indice, Object dato) throws Exception {
        if (dato == null) throw new Exception("El dato no puede estar vacío");
        if (indice < 0 || indice > cuenta)
            throw new Exception("Índice " + indice + " fuera del rango 0-" + cuenta);

        if (indice == 0) {
            agregarAlInicio(dato);
        } else if (indice == cuenta) {
            agregar(dato);
        } else {
            nodo siguiente = buscar(indice);       
            nodo anterior = siguiente.getIzq();
            nodo obj = new nodo(dato);

            anterior.setDer(obj);
            obj.setIzq(anterior);
            obj.setDer(siguiente);
            siguiente.setIzq(obj);
            ++cuenta;
        }
    }

    // METOSOD PARA ELIMINAR

    public Object eliminarPrimero() throws Exception {
        if (esVacia()) throw new Exception("La lista está vacía");

        Object dato = primero.getDato();
        if (cuenta == 1) {
            primero = null;
            ultimo = null;
        } else {
            primero = primero.getDer();
            primero.setIzq(null);
        }
        --cuenta;
        return dato;
    }

    public Object eliminarUltimo() throws Exception {
        if (esVacia()) throw new Exception("La lista está vacía");

        Object dato = ultimo.getDato();
        if (cuenta == 1) {
            primero = null;
            ultimo = null;
        } else {
            ultimo = ultimo.getIzq();
            ultimo.setDer(null);
        }
        --cuenta;
        return dato;
    }

    public Object eliminarEnPosicion(int indice) throws Exception {
        if (indice == 0) return eliminarPrimero();
        if (indice == cuenta - 1) return eliminarUltimo();

        nodo obj = buscar(indice);
        obj.getIzq().setDer(obj.getDer());
        obj.getDer().setIzq(obj.getIzq());
        --cuenta;
        return obj.getDato();
    }

    // METODOS PARA BUJSCAR

    private nodo buscar(int indice) throws Exception {
        if (esVacia()) throw new Exception("La lista está vacía");
        if (indice < 0 || indice >= cuenta)
            throw new Exception("Índice " + indice + " fuera del rango 0-" + (cuenta - 1));

        nodo actual;
        // Optimización: recorrer desde el extremo más cercano
        if (indice <= cuenta / 2) {
            actual = primero;
            for (int i = 0; i < indice; i++) actual = actual.getDer();
        } else {
            actual = ultimo;
            for (int i = cuenta - 1; i > indice; i--) actual = actual.getIzq();
        }
        return actual;
    }

    private int buscar(Object dato) throws Exception {
        if (esVacia()) throw new Exception("La lista está vacía");

        nodo actual = primero;
        for (int i = 0; i < cuenta; i++) {
            if (actual.getDato().equals(dato)) return i;
            actual = actual.getDer();
        }
        throw new Exception("El elemento no está en la lista");
    }

    public Object buscarDato(int indice) throws Exception {
        return buscar(indice).getDato();
    }

    public int buscarDato(Object dato) throws Exception {
        return buscar(dato);
    }

    //  METODOS PARA INFORMACION

    public boolean contiene(Object dato) {
        try {
            buscar(dato);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int cuentaElementos() {
        return cuenta;
    }

    public void limpiar() {
        primero = null;
        ultimo = null;
        cuenta = 0;
    }

    public void mostrarAdelante() throws Exception {
        if (esVacia()) throw new Exception("La lista está vacía");

        nodo actual = primero;
        System.out.print("[");
        while (actual != null) {
            System.out.print(actual.getDato());
            if (actual.getDer() != null) {
                System.out.print(" <-> ");
            }
            actual = actual.getDer();
        }
        System.out.println("]");
    }

    public void mostrarAtras() throws Exception {
        if (esVacia()) throw new Exception("La lista está vacía");

        nodo actual = ultimo;
        System.out.print("[");
        while (actual != null) {
            System.out.print(actual.getDato());
            if (actual.getIzq() != null){
                System.out.print(" <-> ");
            }
            actual = actual.getIzq();
        }
        System.out.println("]");
    }
}