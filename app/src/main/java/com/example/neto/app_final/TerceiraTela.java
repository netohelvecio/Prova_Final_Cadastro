package com.example.neto.app_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Banco_de_Dados.DBConnection;
import Banco_de_Dados.Usuario;

public class TerceiraTela extends AppCompatActivity {

    ListView lv_lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceira_tela);

        //captura os componentes
        lv_lista = (ListView) findViewById(R.id.lv_lista);

        ArrayAdapter adapter = new ClientesAdapter(this, CarregaDados()); //passa os usuarios para objeto
        lv_lista.setAdapter(adapter); //muda layout da listview e exibe todos usuarios cadastrados
    }

    private ArrayList<Usuario> CarregaDados(){
        //faz ligação com o banco para carreagr usuarios cadastrados
        DBConnection connection = new DBConnection(this);
        ArrayList<Usuario> list = (ArrayList<Usuario>) connection.carregaDados();

        return list; //retorna lista de usuarios cadastradis
    }
}
