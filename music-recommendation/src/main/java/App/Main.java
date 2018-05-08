package App;

import controller.MainController;
import controller.OWLController;
import java.util.ArrayList;
import model.Artista;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import view.TelaPrincipal;
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
        OWLController oWLController = new OWLController();
        MainController mainController = new MainController();
        mainController.populaEntidades(oWLController);
        TelaPrincipal telaPrincipal = new TelaPrincipal(mainController);
        telaPrincipal.show();
    }  
}
