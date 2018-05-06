package App;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import static javafx.scene.input.KeyCode.T;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyImpl;

import org.semanticweb.HermiT.Configuration;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.reasoner.InferenceDepth;
import org.semanticweb.owlapi.reasoner.impl.DefaultNodeSet;
import org.semanticweb.owlapi.util.OWLAPIStreamUtils;
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
//        TelaPrincipal telaPrincipal = new TelaPrincipal();
//        telaPrincipal.show();
          

          //Declaração das paradas importantes
        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        OWLOntology o;
        File file = new File("C:\\Users\\Leon\\Documents\\GitHub\\music-recommendation\\Ontology\\musicontology.owl");

          
        //load owl
        o = man.loadOntologyFromOntologyDocument(file);
        System.out.println("Loaded ontology: " + o.getOntologyID());
        IRI location = man.getOntologyDocumentIRI(o);
        System.out.println("\tfrom: " + location);
          
        long time = System.currentTimeMillis();
          
        // get and configure a reasoner (HermiT)
        //import org.semanticweb.HermiT.Reasoner;
        OWLReasonerFactory reasonerFactory = new ReasonerFactory();
        ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
        OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
                       
        // create the reasoner instance, classify and compute inferences
        OWLReasoner reasoner = reasonerFactory.createReasoner(o, config);
	// perform all the inferences now, to avoid subsequent ad-hoc
        // reasoner calls
	reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
          
        // init prefix manager
	DefaultPrefixManager pm = new DefaultPrefixManager(null, null,"http://www.semanticweb.org/julia/ontologies/2018/3/MusicOntology#");
	// pm.setPrefix("another:", "http://elite.polito.it/ontologies/anotheront.owl#");
          
        // get all the musics
        OWLDataFactory fac = man.getOWLDataFactory();
        OWLClass universities = fac.getOWLClass(IRI.create(pm.getDefaultPrefix(), "Artista"));
        NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(universities, InferenceDepth.ALL);
        Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();
        
        for (OWLNamedIndividual uni : individuals){
            
            // print the individual name
            System.out.println("Individual Name: " + pm.getShortForm(uni));

            // get offered degrees (i.e., "pertenceA" obj property)
            OWLObjectPropertyImpl op = new OWLObjectPropertyImpl(
            IRI.create(pm.getDefaultPrefix() + "produz"));
            List<OWLIndividual> ehProduzidoPor= OWLAPIStreamUtils.asList(EntitySearcher.getObjectPropertyValues(uni, op, o));
            System.out.println(""+pm.getDefaultPrefix() + "produz");
            
            for (OWLIndividual degree : ehProduzidoPor){
                System.out.println("\n\tproduz: " + pm.getShortForm((OWLEntity) degree));

                // get offered courses ("offersCourse" obj property)
                OWLObjectPropertyImpl c = new OWLObjectPropertyImpl(
                                IRI.create(pm.getDefaultPrefix() + "pertenceA"));
                List<OWLIndividual> produz = OWLAPIStreamUtils.asList(EntitySearcher.getObjectPropertyValues(degree, c, o));
                
                for (OWLIndividual course : produz){
                        System.out.println("\n\tpertenceA: " + pm.getShortForm((OWLEntity) course));
                }
            }
            
            System.out.println("---");
            
        }

        System.err.println("All universities (" + individuals.size() + ") extracted in "
					+ (float) (System.currentTimeMillis() - time) / 1000 + " seconds.");
        
//          Set<OWLEntity> entOnt = o.getSignature();
//          for(OWLEntity a : entOnt) {
//            System.out.println("Entity "+a);
//          }
//          OWLDataFactory factory = man.getOWLDataFactory();
//          IRI iri = IRI.create("http://www.semanticweb.org/julia/ontologies/2018/3/MusicOntology#Musica");
//          OWLClass clsAMethodA = factory.getOWLClass(iri);
//          System.out.println(entOnt);

//          http://www.semanticweb.org/owlapi/ontologies/ontology#A
//          http://www.semanticweb.org/julia/ontologies/2018/3/MusicOntology#Pop

//        try {
//            o = man.createOntology();
//            System.out.println(o);
//        } catch (OWLOntologyCreationException e) {
//            e.printStackTrace();
//        }

//            System.out.println(o);

//            IRI IOR = IRI.create("http://owl.api.tutorial");
//            OWLOntologyManager man = OWLManager.createOWLOntologyManager();
//            OWLOntology o = man.createOntology(IOR);
//            OWLDataFactory df = o.getOWLOntologyManager().getOWLDataFactory();
//            OWLClass person = df.getOWLClass(IOR+"#Person");
//            OWLDeclarationAxiom da = df.getOWLDeclarationAxiom(person);
//            o.add(da);
//            System.out.println(o);
            
//            o.logicalAxioms().forEach(System.out::println);
//            o.signature().filter((e->(!e.isBuiltIn()&&e.getIRI().getFragment().startsWith("P")))).forEach(System.out::println);
//            o.signature().filter(e->!e.isBuiltIn()&&e.getIRI().getRemainder().orElse("").startsWith("P")).forEach(System.out::println);
//            o.signature().filter(e->e.getIRI().getRemainder().orElse("").startsWith("P")).forEach(System.out::println);           
//              o.signature().filter(Main::owlClassNameStartsWithP).forEach(System.out::println);
    
    }  
    
    private static boolean owlClassNameStartsWithP(OWLEntity e) {
        return !e.isBuiltIn()&&e.getIRI().getRemainder().orElse("").startsWith("P");
    }
}
