import java.util.Scanner;
public class menuSIstema{

    static lista listaGeneral = new lista();
    static cola colaPendientes = new cola();
    static pila historialProcesados = new pila();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opc = 0;
        do {
            mostrarMenu();
            System.out.print("Opción: ");
            opc = sc.nextInt();
            sc.nextLine();
            System.out.println();
            try {
                switch (opc) {
                    case 1  : registrar(); break;
                    case 2  : verTodos(); break;
                    case 3  : verPendientes(); break;
                    case 4  : procesarSiguiente(); break;
                    case 5  : verHistorial(); break;
                    case 6  : buscarPorCodigo(); break;
                    case 7  : cancelarPendiente(); break;
                    case 8  : deshacerProcesamiento(); break;
                    case 9  : verCantidad(); break;
                    case 10 : System.out.println("Saliendo..."); break;
                    default : System.out.println("Opción inválida."); break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        } while (opc != 10);
    }

    // ── menú ──────────────────────────────────────────────────

    static void mostrarMenu() {
        System.out.println("===== SISTEMA DE URGENCIAS =====");
        System.out.println(" 1. Registrar paciente"); 
        System.out.println(" 2. Ver todos los pacientes");
        System.out.println(" 3. Ver pacientes pendientes");
        System.out.println(" 4. Procesar siguiente paciente");
        System.out.println(" 5. Ver historial de procesados");
        System.out.println(" 6. Buscar paciente por código triage");
        System.out.println(" 7. Cancelar paciente pendiente");
        System.out.println(" 8. Deshacer último procesamiento");
        System.out.println(" 9. Ver cantidad de elementos");
        System.out.println("10. Salir");
        System.out.println("================================");
    }

    //  1

    static void registrar() throws Exception {
        System.out.print("Código triage: ");
        int codigo = sc.nextInt(); sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Identificación: ");
        String identificacion = sc.nextLine();
        System.out.print("Contacto: ");
        String contacto = sc.nextLine();

        PacienteUrgencia p = new PacienteUrgencia(codigo, nombre, identificacion, contacto);
        listaGeneral.agregar(p);
        colaPendientes.encolar(p);
        System.out.println("Paciente registrado correctamente.");
    }

    //  2

    static void verTodos() throws Exception {
        System.out.println(" Todos los pacientes registrados ");
        if (listaGeneral.esVacia()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        listaGeneral.mostrarAdelante();
    }

    //  3

    static void verPendientes() throws Exception {
        System.out.println(" Pacientes pendientes ");
        if (colaPendientes.esVacia()) {
            System.out.println("No hay pacientes pendientes.");
            return;
        }
        colaPendientes.mostrar();
    }

    // 4

    static void procesarSiguiente() throws Exception {
        if (colaPendientes.esVacia()) {
            System.out.println("No hay pacientes pendientes por procesar.");
            return;
        }
        PacienteUrgencia procesado = (PacienteUrgencia) colaPendientes.desencolar();
        historialProcesados.apilar(procesado);
        System.out.println("Paciente procesado: " + procesado);
    }

    //  5

    static void verHistorial() throws Exception {
        System.out.println(" Historial de procesados ");
        if (historialProcesados.esVacia()) {
            System.out.println("El historial está vacío.");
            return;
        }
        historialProcesados.mostrar();
    }

    // 6

    static void buscarPorCodigo() throws Exception {
        System.out.print("Código triage a buscar: ");
        int codigo = sc.nextInt(); sc.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < listaGeneral.cuentaElementos(); i++) {
            PacienteUrgencia p = (PacienteUrgencia) listaGeneral.buscarDato(i);
            if (p.getCodigo_triage() == codigo) {
                System.out.println("Paciente encontrado: " + p);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró un paciente con código triage " + codigo);
        }
    }

    // 7

    static void cancelarPendiente() throws Exception {
        if (colaPendientes.esVacia()) {
            System.out.println("No hay pacientes pendientes.");
            return;
        }
        System.out.print("Código triage a cancelar: ");
        int codigo = sc.nextInt(); sc.nextLine();

        cola auxiliar = new cola();
        boolean cancelado = false;

        // usando auxiliar
        while (!colaPendientes.esVacia()) {
            PacienteUrgencia p = (PacienteUrgencia) colaPendientes.desencolar();
            if (p.getCodigo_triage() == codigo && !cancelado) {
                cancelado = true; // se "salta" este elemento
                System.out.println("Paciente cancelado: " + p);
            } else {
                auxiliar.encolar(p);              
            }
        }

        // Restaurar la cola original sin el cancelado
        while (!auxiliar.esVacia()) {
            colaPendientes.encolar(auxiliar.desencolar());
        }

        if (!cancelado) {
            System.out.println("No se encontró un paciente pendiente con ese código.");
        }
    }

    // 8

    static void deshacerProcesamiento() throws Exception {
        if (historialProcesados.esVacia()) {
            System.out.println("El historial está vacío. No hay nada que deshacer.");
            return;
        }
        PacienteUrgencia ultimo = (PacienteUrgencia) historialProcesados.desapilar();
        colaPendientes.encolar(ultimo);
        System.out.println("Se regresó a pendientes: " + ultimo);
    }

    // 9

    static void verCantidad() {
        System.out.println("Total registrados  : " + listaGeneral.cuentaElementos());
        System.out.println("Pendientes         : " + colaPendientes.tamano());
        System.out.println("Procesados         : " + historialProcesados.tamanio());
    }
}