package com.alura.rafael.agendaalunos.modelo;

import java.io.Serializable;

/**
 * Created by Desktop on 29/11/2016.
 */

/*Aqui implementamos a classe serializable para o aluno, com isto podemos transferir
dados em bytes de uma classe para outrs. No exemplo recuperamos as informaçoes no formulario
para poder editar o aluno.*/
public class Aluno implements Serializable {

    private Long id;
    private String nome;
    private String end;
    private String tel;
    private String site;
    private Double nota;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }


    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}
