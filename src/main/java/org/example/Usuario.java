package org.example;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String login;
    private String senha;
    private String cpf;
    private String nome;

    public Usuario() {

    }

    public Usuario(String login, String senha, String cpf, String nome) {
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Usuario: " + this.nome + " " + this.cpf + " " + this.login + " " + this.senha;
    }
}
