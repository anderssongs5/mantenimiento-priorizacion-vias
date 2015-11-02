/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mantpriorivias;

import co.edu.udea.mantpriorivias.archivos.LectorArchivoExcel;
import co.edu.udea.mantpriorivias.entidades.MantPriorViasInfo;
import java.io.IOException;

/**
 *
 * @author Samsung
 */
public class MantenimientoPriorizacionVias {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        long inicio = System.currentTimeMillis();

        String ruta = "C:\\Users\\Andersson\\Desktop\\Temporales\\Plantilla.xlsx";

        LectorArchivoExcel lectorArchivoExcel = new LectorArchivoExcel();
        MantPriorViasInfo mantPriorViasInfo = lectorArchivoExcel.leerArchivo(ruta);

        long fin = System.currentTimeMillis();
        System.out.println("\n\nTiempo total milesegundos: " + (fin - inicio));
    }

}
