/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Leon
 */
public class Musica {
    
    private Integer id;
    private String name;
    
    private List<Artista> ehProduzidoPor;
    private List<Genero> pertenceA;
    private List<Estilo> fazParteDe;

    public Musica(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Musica(String name) {
        this.name = name;
    }
    
    public Musica(){}
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artista> getEhProduzidoPor() {
        return ehProduzidoPor;
    }

    public void setEhProduzidoPor(List<Artista> ehProduzidoPor) {
        this.ehProduzidoPor = ehProduzidoPor;
    }

    public List<Genero> getPertenceA() {
        return pertenceA;
    }

    public void setPertenceA(List<Genero> pertenceA) {
        this.pertenceA = pertenceA;
    }

    public List<Estilo> getFazParteDe() {
        return fazParteDe;
    }

    public void setFazParteDe(List<Estilo> fazParteDe) {
        this.fazParteDe = fazParteDe;
    }
}
