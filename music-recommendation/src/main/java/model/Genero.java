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
public class Genero {
    
    private Integer id;
    private String name;

    private List<Musica> abrange;
    
    public Genero(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genero(){}
    
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

    public List<Musica> getAbrange() {
        return abrange;
    }

    public void setAbrange(List<Musica> abrange) {
        this.abrange = abrange;
    }
}
