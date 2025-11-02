package estadisticas;

import java.util.Comparator;
import app.Monitor;

public class MejoresMonitoresPorValoracion implements Comparator<Monitor>{
    @Override
    public int compare(Monitor p1, Monitor p2) {
        return p2.calcularValoracion() - p1.calcularValoracion();
    }
}
