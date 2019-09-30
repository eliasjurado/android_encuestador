package com.elias.www.sistemaencuesta;

import java.io.Serializable;

public class Encuesta_BE implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idEncuesta;
    private String titulo;
    private String comentario;
    private String autor;
    private String pregunta1;
    private String pregunta2;
    private String pregunta3;
    private String pregunta4;
    private String pregunta5;

    //Contructores
    public Encuesta_BE(int idEncuesta, String titulo,String comentario, String autor, String pregunta1, String pregunta2, String pregunta3, String pregunta4, String pregunta5) {
        idEncuesta = idEncuesta;
        this.titulo = titulo;
        this.comentario = comentario;
        this.autor = autor;
        this.pregunta1 = pregunta1;
        this.pregunta2 = pregunta2;
        this.pregunta3 = pregunta3;
        this.pregunta4 = pregunta4;
        this.pregunta5 = pregunta5;
    }

    public Encuesta_BE() {
        idEncuesta = 0;
        this.titulo = "";
        this.comentario = "";
        this.autor = "";
        this.pregunta1 = "";
        this.pregunta2 = "";
        this.pregunta3 = "";
        this.pregunta4 = "";
        this.pregunta5 = "";
    }
    //Setters y getters


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public String getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(String pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(String pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public String getPregunta4() {
        return pregunta4;
    }

    public void setPregunta4(String pregunta4) {
        this.pregunta4 = pregunta4;
    }

    public String getPregunta5() {
        return pregunta5;
    }

    public void setPregunta5(String pregunta5) {
        this.pregunta5 = pregunta5;
    }
}
