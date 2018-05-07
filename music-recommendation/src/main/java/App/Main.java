package App;

import controller.MainController;
import controller.OWLController;
import java.util.List;
import java.util.Set;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws OWLOntologyCreationException {
        // TODO code application logic here
        //TelaPrincipal telaPrincipal = new TelaPrincipal();
        //telaPrincipal.show();
        
        OWLController oWLController = new OWLController();
        MainController mainController = new MainController();
        mainController.populaEntidades(oWLController);
        
        
//        Set<OWLNamedIndividual> individuals = oWLController.getAllObjects("Artista");
//
//        for (OWLNamedIndividual uni : individuals){
//            System.out.println(oWLController.getName(uni));
//            oWLController.printObject(uni);
//            List<OWLIndividual> ehProduzidoPor = oWLController.getObjectsByRelation("produz", uni);
//            for (OWLIndividual oWLIndividual : ehProduzidoPor) {
//                oWLController.printRelation("produz", oWLIndividual);
//            }
//        }
        
    }  
}
