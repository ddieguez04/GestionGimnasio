
package estadisticas;

import java.util.Comparator;
import app.Actividad;

public class MejorActividadPorValoracion implements Comparator<Actividad> {
    @Override
    public int compare(Actividad a1, Actividad a2) {
        return a2.calcularValoracion() - a1.calcularValoracion();
    }
}
