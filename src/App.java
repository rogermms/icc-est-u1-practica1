import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        System.out.println("C7: Nota ajustada descendente; nombre ascendente; código ascendente");
        for (Estudiante est : c7) {
            System.out.println("  " + est);
        }
    }

    private static void imprimirResultado(String id, String criterio, List<Estudiante> lista) {
        System.out.println("ID: " + id + " | Criterio: " + criterio);
        for (Estudiante e : lista) {
            System.out.println("   " + e.getCodigo() + " - " + e.getNombre() + " - " + e.getNota() + " - " + e.getCarrera());
        }
        System.out.println("--------------------------------------------------");
    }
}


    
