package Banco_de_Dados;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String nome;
    private String cpf;
    private String data;
    private String foto;

    public Usuario(String name, String cpf, String data, String foto){
        this.nome = name;
        this.cpf = cpf;
        this.data = data;
        this.foto = foto;
    }

    public Usuario() {

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


}
