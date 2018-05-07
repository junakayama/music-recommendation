/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.util.List;
import java.util.Set;
import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.InferenceDepth;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.OWLAPIStreamUtils;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyImpl;

/**
 *
 * @author Leon
 */
public class OWLController {

    OWLOntologyManager man;
    OWLOntology o;
    File file;
    DefaultPrefixManager pm;
    OWLReasoner reasoner;
    
    public OWLController() throws OWLOntologyCreationException{
        this.file = new File("..\\Ontology\\MusicOntology");
        this.man = OWLManager.createOWLOntologyManager();
        init();
    }
    
    public void init() throws OWLOntologyCreationException{
        loadOWL();
        configureReasoner();
        configurePrefixManager();
    }
    
    public void loadOWL() throws OWLOntologyCreationException{
        o = man.loadOntologyFromOntologyDocument(file);
        System.out.println("Loaded ontology: " + o.getOntologyID());
        IRI location = man.getOntologyDocumentIRI(o);
        System.out.println("\tfrom: " + location);
        long time = System.currentTimeMillis();
    }
    
    public void configureReasoner(){
        OWLReasonerFactory reasonerFactory = new ReasonerFactory();
        ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
        OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
        reasoner = reasonerFactory.createReasoner(o, config);
        reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
    }
    
    public void configurePrefixManager(){
        pm = new DefaultPrefixManager(null, null,"http://www.semanticweb.org/julia/ontologies/2018/3/MusicOntology#");
    }
    
    public Set<OWLNamedIndividual> getAllObjects(String objeto){
        OWLDataFactory fac = man.getOWLDataFactory();
        OWLClass entities = fac.getOWLClass(IRI.create(pm.getDefaultPrefix(),objeto));
        NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(entities, InferenceDepth.ALL);
        Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();
        return individuals;
    }
    
    public List<OWLIndividual> getObjectsByRelation(String relation,OWLNamedIndividual individuals){
        List<OWLIndividual> relatedWith = null;
        OWLObjectPropertyImpl op = new OWLObjectPropertyImpl(IRI.create(pm.getDefaultPrefix()+relation));
        relatedWith = OWLAPIStreamUtils.asList(EntitySearcher.getObjectPropertyValues(individuals, op, o));
        return relatedWith;
    }
    
    public void printRelation(String relation,OWLIndividual individuals){
        System.out.println("\n\t"+relation+": "+ pm.getShortForm((OWLEntity)individuals));
    }
    
    public void printObject(OWLNamedIndividual individuals){
        System.out.println("Individual Name: " + pm.getShortForm(individuals)); 
    }
    
    public String getName(OWLNamedIndividual individuals){
        String name = pm.getShortForm(individuals);
        return name.substring(1);
    }
}
