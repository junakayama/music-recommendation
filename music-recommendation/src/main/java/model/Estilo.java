/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leon
 */
public class Estilo {
    
    private Integer id;
    private String name;

    private List<Musica> reune;
    
    public Estilo(Integer id, String name) {
        this.reune = new ArrayList<>();
        this.id = id;
        this.name = name;
    }

     public Estilo(String name) {
        this.reune = new ArrayList<>();
        this.name = name;
    }
    
    public Estilo(){this.reune = new ArrayList<>();
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

    public List<Musica> getReune() {
        return reune;
    }

    public void setReune(List<Musica> reune) {
        this.reune = reune;
    }
    
    public void addElement(Musica musica){
        reune.add(musica);
    }
    
}
