/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import model.Artista;
import model.Estilo;
import model.Genero;
import model.Musica;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

/**
 *
 * @author Leon
 */
public class MainController {

    ArrayList<Musica> musicas;
    ArrayList<Genero> generos;
    ArrayList<Estilo> estilos;
    ArrayList<Artista> artistas;
    
    public MainController() {
        this.artistas = new ArrayList<>();
        this.estilos = new ArrayList<>();
        this.generos = new ArrayList<>();
        this.musicas = new ArrayList<>();
    }
    
    public void populaEntidades(OWLController oWLController){
        Set<OWLNamedIndividual> individuals = oWLController.getAllObjects("Musica");
        for (OWLNamedIndividual uni : individuals){
            Musica musica = new Musica(musicas.size(),oWLController.getName(uni));
            
            List<OWLIndividual> ehProduzidoPor = oWLController.getObjectsByRelation("ehProduzidoPor", uni);
            List<OWLIndividual> fazParteDe = oWLController.getObjectsByRelation("fazParteDe", uni);
            List<OWLIndividual> pertenceA = oWLController.getObjectsByRelation("pertenceA", uni);
            
            oWLController.printObject(uni);
            for (OWLIndividual oWLIndividual : ehProduzidoPor) {
                Artista artista = new Artista(artistas.size(),oWLController.getName((OWLNamedIndividual)oWLIndividual));
                artista.getProduz().add(musica);
                artistas.add(artista);
                oWLController.printRelation("ehProduzidoPor", oWLIndividual);
            }
            for (OWLIndividual oWLIndividual : fazParteDe) {
                Estilo estilo = new Estilo(estilos.size(),oWLController.getName((OWLNamedIndividual)oWLIndividual));
                estilo.getReune().add(musica);
                estilos.add(estilo);
                oWLController.printRelation("fazParteDe", oWLIndividual);
            }
            for (OWLIndividual oWLIndividual : pertenceA) {
                Genero genero = new Genero(generos.size(),oWLController.getName((OWLNamedIndividual)oWLIndividual));
                genero.getAbrange().add(musica);
                generos.add(genero);
                oWLController.printRelation("pertenceA", oWLIndividual);
            }
            
            musica.setEhProduzidoPor(artistas);
            musica.setFazParteDe(estilos);
            musica.setPertenceA(generos);
            musicas.add(musica);
        }
    }
    
}
