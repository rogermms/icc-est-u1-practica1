import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
        List<Estudiante> estudiantes = new ArrayList<>(List.of(
    new Estudiante("E014", "Ana",    20, 8.5, "Computacion"),
    new Estudiante("E003", "Luis",   19, 7.8, "Electronica"),
    new Estudiante("E018", "Maria",  21, 9.2, "Computacion"),
    new Estudiante("E007", "Pedro",  20, 8.5, "Mecatronica"),
    new Estudiante("E001", "Sofia",  18, 9.5, "Computacion"),
    new Estudiante("E012", "Diego",  22, 6.8, "Electronica"),
    new Estudiante("E005", "Ana",    19, 8.5, "Computacion"),
    new Estudiante("E020", "Carlos", 21, 7.8, "Mecatronica"),
    new Estudiante("E009", "Lucia",  20, 9.2, "Electronica"),
    new Estudiante("E016", "Mateo",  18, 7.0, "Computacion"),
    new Estudiante("E002", "Elena",  22, 8.0, "Mecatronica"),
    new Estudiante("E011", "Pablo",  21, 9.5, "Electronica"),
    new Estudiante("E006", "Sara",   19, 6.8, "Computacion"),
    new Estudiante("E019", "Juan",   20, 8.0, "Mecatronica"),
    new Estudiante("E004", "Diana",  18, 9.2, "Electronica"),
    new Estudiante("E015", "Bruno",  22, 7.0, "Computacion"),
    new Estudiante("E008", "Carla",  21, 8.5, "Mecatronica"),
    new Estudiante("E017", "Andres", 19, 9.5, "Electronica"),
    new Estudiante("E010", "Ana",    20, 8.5, "Computacion"),
    new Estudiante("E013", "Luis",   19, 7.8, "Electronica")
    ));
    
    List<String> codigosIniciales = estudiantes.stream().map(Estudiante::getCodigo).toList();


    // --- C1: Código ascendente (Orden natural) ---
        List<Estudiante> c1 = new ArrayList<>(estudiantes);
        Collections.sort(c1);
        System.out.println("C1: Código ascendente");
        for (Estudiante est : c1) {
            System.out.println("  " + est);
        }

        // --- C2: Nombre ascendente; código ascendente (Lambda de 2 parámetros) ---
        List<Estudiante> c2 = new ArrayList<>(estudiantes);
        c2.sort((e1, e2) -> {
            int compNombre = e1.getNombre().compareTo(e2.getNombre());
            if (compNombre != 0) return compNombre;
            return e1.getCodigo().compareTo(e2.getCodigo());
        });
        System.out.println("C2: Nombre ascendente; código ascendente");
        for (Estudiante est : c2) {
            System.out.println("  " + est);
        }

        // --- C3: Nota ascendente, sin desempate (comparingDouble y referencia a método) ---
        List<Estudiante> c3 = new ArrayList<>(estudiantes);
        c3.sort(Comparator.comparingDouble(Estudiante::getNota));
        System.out.println("C3: Nota ascendente, sin desempate");
        for (Estudiante est : c3) {
            System.out.println("  " + est);
        }

        // --- C4: Nota descendente; nombre ascendente (reversed y thenComparing) ---
        List<Estudiante> c4 = new ArrayList<>(estudiantes);
        c4.sort(Comparator.comparingDouble(Estudiante::getNota).reversed()
                          .thenComparing(Estudiante::getNombre));
        System.out.println("C4: Nota descendente; nombre ascendente");
        for (Estudiante est : c4) {
            System.out.println("  " + est);
        }

        // --- C5: Edad ascendente; nombre descendente ---
        List<Estudiante> c5 = new ArrayList<>(estudiantes);
        c5.sort(Comparator.comparingInt(Estudiante::getEdad)
                          .thenComparing(Comparator.comparing(Estudiante::getNombre).reversed()));
        System.out.println("C5: Edad ascendente; nombre descendente");
        for (Estudiante est : c5) {
            System.out.println("  " + est);
        }

        // --- C6: Carrera asc; nota desc; nombre asc; código asc ---
        List<Estudiante> c6 = new ArrayList<>(estudiantes);
        c6.sort(Comparator.comparing(Estudiante::getCarrera)
                          .thenComparing(Comparator.comparingDouble(Estudiante::getNota).reversed())
                          .thenComparing(Estudiante::getNombre)
                          .thenComparing(Estudiante::getCodigo));
        System.out.println("C6: Carrera asc; nota desc; nombre asc; código asc");
        for (Estudiante est : c6) {
            System.out.println("  " + est);
        }

        // --- C7: Nota ajustada descendente; nombre ascendente; código ascendente ---
        
        // C7
        // Nota ajustada descendente; nombre ascendente; código ascendente
        List<Estudiante> c7 = new ArrayList<>(estudiantes);
        c7.sort((e1, e2) -> {
            double nota1 = e1.getCarrera().equals("Computacion") ? Math.min(10.0, e1.getNota() + 0.5) : e1.getNota();
            double nota2 = e2.getCarrera().equals("Computacion") ? Math.min(10.0, e2.getNota() + 0.5) : e2.getNota();
            
            int compNota = Double.compare(nota2, nota1); // Descendente
            if (compNota != 0) return compNota;
            
            int compNombre = e1.getNombre().compareTo(e2.getNombre());
            if (compNombre != 0) return compNombre;
            
            return e1.getCodigo().compareTo(e2.getCodigo());
        });
        System.out.println("Ejemplo C7");
        System.out.println("Nota ajustada descendente, nombre ascendente, código ascendente");
        for (Estudiante est : c7) {
            double notaAjustada = est.getCarrera().equals("Computacion") 
                          ? Math.min(10.0, est.getNota() + 0.5) 
                          : est.getNota();
            System.out.println(" Estudiante [codigo=" + est.getCodigo()
                    + ", nombre=" + est.getNombre()
                    + ", edad=" + est.getEdad()
                    + ", notaOriginal=" + est.getNota()
                    + ", notaAjustada=" + notaAjustada
                    + ", carrera=" + est.getCarrera() + "]");

        }
        System.out.println("----------------------------------------------------------------------------------------");

        // === VALIDACIONES Y PRUEBAS PARA EVIDENCIAS ===
        System.out.println("=== SECUENCIAS DE CODIGOS ===");
        System.out.println("C1: " + c1.stream().map(Estudiante::getCodigo).toList());
        System.out.println("C2: " + c2.stream().map(Estudiante::getCodigo).toList());
        System.out.println("C3: " + c3.stream().map(Estudiante::getCodigo).toList());
        System.out.println("C4: " + c4.stream().map(Estudiante::getCodigo).toList());
        System.out.println("C5: " + c5.stream().map(Estudiante::getCodigo).toList());
        System.out.println("C6: " + c6.stream().map(Estudiante::getCodigo).toList());
        System.out.println("C7: " + c7.stream().map(Estudiante::getCodigo).toList());

        // 1. Conservación de los 20 registros sin pérdidas ni duplicados
        Set<String> setOriginal = new HashSet<>(codigosIniciales);
        List<List<Estudiante>> todas = List.of(c1, c2, c3, c4, c5, c6, c7);
        for (int i = 0; i < todas.size(); i++) {
            List<String> cods = todas.get(i).stream().map(Estudiante::getCodigo).toList();
            boolean conserva = cods.size() == 20 && new HashSet<>(cods).equals(setOriginal);
            System.out.println("C" + (i + 1) + " conserva 20 codigos: " + conserva);
        }

        // 2. Verificación de orden entre elementos consecutivos
        System.out.println("Orden valido C1: " + estaOrdenada(c1, Comparator.naturalOrder()));
        System.out.println("Orden valido C2: " + estaOrdenada(c2, Comparator.comparing(Estudiante::getNombre).thenComparing(Estudiante::getCodigo)));
        System.out.println("Orden valido C3: " + estaOrdenada(c3, Comparator.comparingDouble(Estudiante::getNota)));
        System.out.println("Orden valido C4: " + estaOrdenada(c4, Comparator.comparingDouble(Estudiante::getNota).reversed().thenComparing(Estudiante::getNombre)));
        System.out.println("Orden valido C5: " + estaOrdenada(c5, Comparator.comparingInt(Estudiante::getEdad).thenComparing(Comparator.comparing(Estudiante::getNombre).reversed())));
        System.out.println("Orden valido C6: " + estaOrdenada(c6, Comparator.comparing(Estudiante::getCarrera).thenComparing(Comparator.comparingDouble(Estudiante::getNota).reversed()).thenComparing(Estudiante::getNombre).thenComparing(Estudiante::getCodigo)));
        System.out.println("Orden valido C7: " + estaOrdenada(c7, (e1, e2) -> {
            double n1 = e1.getCarrera().equals("Computacion") ? Math.min(10.0, e1.getNota() + 0.5) : e1.getNota();
            double n2 = e2.getCarrera().equals("Computacion") ? Math.min(10.0, e2.getNota() + 0.5) : e2.getNota();
            int cn = Double.compare(n2, n1);
            if (cn != 0) return cn;
            int cNom = e1.getNombre().compareTo(e2.getNombre());
            return cNom != 0 ? cNom : e1.getCodigo().compareTo(e2.getCodigo());
        }));

        // 3. Estabilidad en C3 (nota 8.5)
        List<String> nota85 = c3.stream()
                .filter(e -> Double.compare(e.getNota(), 8.5) == 0)
                .map(Estudiante::getCodigo)
                .toList();
        System.out.println("Estabilidad C3 (nota 8.5): " + nota85);

        // 4. Lista original intacta
        List<String> codigosFinales = estudiantes.stream().map(Estudiante::getCodigo).toList();
        System.out.println("Original intacta: " + codigosIniciales.equals(codigosFinales));

        // 5. Casos borde: lista vacía, 1 elemento y empate completo en C4
        List<Estudiante> vacia = new ArrayList<>();
        Collections.sort(vacia);
        List<Estudiante> uno = new ArrayList<>(List.of(estudiantes.get(0)));
        uno.sort(Comparator.comparingDouble(Estudiante::getNota).reversed().thenComparing(Estudiante::getNombre));
        List<Estudiante> tresAnas = new ArrayList<>(List.of(estudiantes.get(0), estudiantes.get(6), estudiantes.get(18)));
        tresAnas.sort(Comparator.comparingDouble(Estudiante::getNota).reversed().thenComparing(Estudiante::getNombre));

        System.out.println("Lista vacia (tamano): " + vacia.size());
        System.out.println("Lista 1 elemento: " + uno.stream().map(Estudiante::getCodigo).toList());
        System.out.println("C4 sobre [E014, E005, E010]: " + tresAnas.stream().map(Estudiante::getCodigo).toList());

        // 6. Dos casos propios
        List<String> edad19C5 = c5.stream()
                .filter(e -> e.getEdad() == 19)
                .map(e -> e.getCodigo() + "(" + e.getNombre() + ")")
                .toList();
        System.out.println("Caso propio 1 (edad 19 en C5): " + edad19C5);

        System.out.println("Caso propio 2 (top 4 de C7 - sin bonificacion fuera de Computacion):");
        for (int i = 0; i < 4; i++) {
            Estudiante e = c7.get(i);
            double aj = e.getCarrera().equals("Computacion") ? Math.min(10.0, e.getNota() + 0.5) : e.getNota();
            System.out.println("  #" + (i + 1) + " " + e.getCodigo() + " (" + e.getCarrera()
                    + ") orig=" + e.getNota() + " ajustada=" + aj);
        }
    }

    private static boolean estaOrdenada(List<Estudiante> lista, Comparator<Estudiante> comp) {
        for (int i = 0; i < lista.size() - 1; i++) {
            if (comp.compare(lista.get(i), lista.get(i + 1)) > 0) return false;
        }
        return true;
    }
}