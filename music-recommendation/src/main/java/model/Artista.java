/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leon
 */
public class Artista {
    
    private Integer id;
    private String name;
    
    private List<Musica> produz = new ArrayList<>();

    public Artista(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Artista(){
    }

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

    public List<Musica> getProduz() {
        return produz;
    }

    public void setProduz(List<Musica> produz) {
        this.produz = produz;
    }
    
    
    
    
}
