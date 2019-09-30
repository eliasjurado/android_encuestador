package com.elias.www.sistemaencuesta;

public class Respuesta_BE {
    private static final long serialVersionUID = 1L;
    private int idRespuesta;
    private String titulo;
    private String comentario;
    private String autor;
    private String usuario;
    private String pregunta1;
    private String respuesta1;
    private String pregunta2;
    private String respuesta2;
    private String pregunta3;
    private String respuesta3;
    private String pregunta4;
    private String respuesta4;
    private String pregunta5;
    private String respuesta5;
    //Constructores

    public Respuesta_BE() {
        idRespuesta = 0;
        this.titulo = "";
        this.comentario = "";
        this.autor = "";
        this.usuario = "";
        this.pregunta1 = "";
        this.respuesta1="";
        this.pregunta2 = "";
        this.respuesta2="";
        this.pregunta3 = "";
        this.respuesta3="";
        this.pregunta4 = "";
        this.respuesta4="";
        this.pregunta5 = "";
        this.respuesta5="";
    }

    public Respuesta_BE(int idRespuesta, String titulo, String comentario, String autor, String usuario,String pregunta1, String respuesta1, String pregunta2, String respuesta2, String pregunta3, String respuesta3, String pregunta4, String respuesta4, String pregunta5, String respuesta5) {
        this.idRespuesta = idRespuesta;
        this.titulo = titulo;
        this.comentario = comentario;
        this.autor = autor;
        this.usuario = usuario;
        this.pregunta1 = pregunta1;
        this.respuesta1 = respuesta1;
        this.pregunta2 = pregunta2;
        this.respuesta2 = respuesta2;
        this.pregunta3 = pregunta3;
        this.respuesta3 = respuesta3;
        this.pregunta4 = pregunta4;
        this.respuesta4 = respuesta4;
        this.pregunta5 = pregunta5;
        this.respuesta5 = respuesta5;
    }

    //Getters y setters


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(String pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(String pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public String getPregunta4() {
        return pregunta4;
    }

    public void setPregunta4(String pregunta4) {
        this.pregunta4 = pregunta4;
    }

    public String getRespuesta4() {
        return respuesta4;
    }

    public void setRespuesta4(String respuesta4) {
        this.respuesta4 = respuesta4;
    }

    public String getPregunta5() {
        return pregunta5;
    }

    public void setPregunta5(String pregunta5) {
        this.pregunta5 = pregunta5;
    }

    public String getRespuesta5() {
        return respuesta5;
    }

    public void setRespuesta5(String respuesta5) {
        this.respuesta5 = respuesta5;
    }
}
