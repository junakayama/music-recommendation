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
    private int likeCount;
    private boolean liked = false;
    private boolean disliked  = false;
    private int dislikeCount;
    
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
    
    public void addLike(){
        if(!liked){
            getEhProduzidoPor().get(0).addLike();
            getPertenceA().get(0).addLike();
            List<Estilo> e = getFazParteDe();
            for (Estilo estilo : e) {
                estilo.addLike();
            }
            setLikeCount(getLikeCount()+3);
        }
    }
    
    public void addDislike(){
       if(!disliked){
            getEhProduzidoPor().get(0).addDislike();
            getPertenceA().get(0).addDislike();
            List<Estilo> e = getFazParteDe();
            for (Estilo estilo : e) {
                estilo.addDislike();
            }
            setLikeCount(getLikeCount()-3);
        } 
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

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isDisliked() {
        return disliked;
    }

    public void setDisliked(boolean disliked) {
        this.disliked = disliked;
    }
    
}
