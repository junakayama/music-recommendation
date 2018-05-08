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
public class Genero {
    
    private Integer id;
    private String name;
    private int likeCount;
    private int dislikeCount;

    private List<Musica> abrange = new ArrayList<>();
    
    public Genero(Integer id, String name) {
        this.abrange = new ArrayList<>();
        this.id = id;
        this.name = name;
    }

     public Genero(String name) {
        this.abrange = new ArrayList<>();
        this.name = name;
    }
    
    public Genero(){this.abrange = new ArrayList<>();
}
    
    public void addLike(){
        likeCount++;
    }
    
    public void addDislike(){
        likeCount--;
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

    public List<Musica> getAbrange() {
        return abrange;
    }

    public void setAbrange(List<Musica> abrange) {
        this.abrange = abrange;
    }
    
    public void addElement(Musica musica){
        abrange.add(musica);
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }
    
}
