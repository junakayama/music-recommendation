/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Comparator;
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

    private ArrayList<Artista> artistas;
    private ArrayList<Genero> generos;
    private ArrayList<Estilo> estilos;
    private ArrayList<Musica> musicas;
    
    public MainController() {
        this.musicas = new ArrayList<>();
        this.artistas = new ArrayList<>();
        this.generos = new ArrayList<>();
        this.estilos = new ArrayList<>();
    }
    
    public void likeMusic(Musica musica){
        musica.addLike();
        musica.setLiked(true);
        sortMusicByLikes();
    }
    
    public void dislikeMusic(Musica musica){
        musica.addDislike();
        musicas.remove(musica);
        sortMusicByLikes();
    }
    
    public void sortMusicByLikes(){
        musicas.sort(Comparator.comparing(Musica::getSumLike).reversed());
    }
    
    public void populaArtistas(OWLController oWLController){
        Set<OWLNamedIndividual> individuals = oWLController.getAllObjects("Artista");
        for (OWLNamedIndividual individual : individuals) {
            Artista artista = new Artista(oWLController.getName(individual));
            artistas.add(artista);
        }
    }
    
    public void populaEstilos(OWLController oWLController){
        Set<OWLNamedIndividual> individuals = oWLController.getAllObjects("Estilo");
        for (OWLNamedIndividual individual : individuals) {
            Estilo estilo = new Estilo(oWLController.getName(individual));
            estilos.add(estilo);
        }
    }
    
    public void populaGeneros(OWLController oWLController){
         Set<OWLNamedIndividual> individuals = oWLController.getAllObjects("Genero");
        for (OWLNamedIndividual individual : individuals) {
            Genero genero = new Genero(oWLController.getName(individual));
            generos.add(genero);
        }
    }
    
    public void populaEntidades(OWLController oWLController){
        populaArtistas(oWLController);
        populaEstilos(oWLController);
        populaGeneros(oWLController);
        Set<OWLNamedIndividual> individuals = oWLController.getAllObjects("Musica");
        for (OWLNamedIndividual uni : individuals){
            ArrayList<Artista> artistasTemp = new ArrayList<>();
            ArrayList<Genero> generosTemp = new ArrayList<>();
            ArrayList<Estilo> estilosTemp = new ArrayList<>();
            Musica musica = new Musica(oWLController.getName(uni));
            
            List<OWLIndividual> ehProduzidoPor = oWLController.getObjectsByRelation("ehProduzidoPor", uni);
            List<OWLIndividual> fazParteDe = oWLController.getObjectsByRelation("fazParteDe", uni);
            List<OWLIndividual> pertenceA = oWLController.getObjectsByRelation("pertenceA", uni);
            
            oWLController.printObject(uni);
            for (OWLIndividual oWLIndividual : ehProduzidoPor) {
                for(Artista artista : artistas) {
                    if(artista.getName().equals(oWLController.getName((OWLNamedIndividual)oWLIndividual))){
                        artista.getProduz().add(musica);
                        artistasTemp.add(artista);
                    }
                }
//                oWLController.printRelation("ehProduzidoPor", oWLIndividual);
            }
            for (OWLIndividual oWLIndividual : fazParteDe) {
                for(Estilo estilo : estilos) {
                    if(estilo.getName().equals(oWLController.getName((OWLNamedIndividual)oWLIndividual))){
                        estilo.getReune().add(musica);
                        estilosTemp.add(estilo);
                    }
                }
//                oWLController.printRelation("fazParteDe", oWLIndividual);
            }
            for (OWLIndividual oWLIndividual : pertenceA) {         
                for(Genero genero : generos) {
                    if(genero.getName().equals(oWLController.getName((OWLNamedIndividual)oWLIndividual))){
                        genero.getAbrange().add(musica);
                        generosTemp.add(genero);
                    }
                }
//                oWLController.printRelation("pertenceA", oWLIndividual);
            }
            
            musica.setEhProduzidoPor(artistasTemp);
            musica.setFazParteDe(estilosTemp);
            musica.setPertenceA(generosTemp);
            musicas.add(musica);
        }
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }  

    public ArrayList<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(ArrayList<Artista> artistas) {
        this.artistas = artistas;
    }

    public ArrayList<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(ArrayList<Genero> generos) {
        this.generos = generos;
    }

    public ArrayList<Estilo> getEstilos() {
        return estilos;
    }

    public void setEstilos(ArrayList<Estilo> estilos) {
        this.estilos = estilos;
    }
    
    
}
