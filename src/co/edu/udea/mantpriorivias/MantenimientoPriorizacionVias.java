/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mantpriorivias;

import co.edu.udea.mantpriorivias.archivos.ArchivoExcel;
import co.edu.udea.mantpriorivias.entidades.MantPriorViasInfo;
import co.edu.udea.mantpriorivias.general.Util;
import java.io.File;
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

//        String ruta = "C:\\Users\\Andersson\\Desktop\\Temporales\\Plantilla.xlsx";
//        File archivo = new File(ruta);
//
//        ArchivoExcel lectorArchivoExcel = new ArchivoExcel();
//        MantPriorViasInfo mantPriorViasInfo = lectorArchivoExcel.leerPlantilla(archivo);
//
//        long fin = System.currentTimeMillis();
//        System.out.println("\n\nTiempo total milesegundos: " + (fin - inicio));
        
        Util.getRutaTemporal();
    }

}
