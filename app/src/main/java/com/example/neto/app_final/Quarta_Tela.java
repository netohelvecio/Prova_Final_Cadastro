package com.example.neto.app_final;


import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;



import java.io.IOException;

import Banco_de_Dados.Usuario;

public class Quarta_Tela extends AppCompatActivity {

    TextView nome_cliente, cpf_cliente, data_cliente;
    ImageView foto_cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarta__tela);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("obj");

        String path = usuario.getFoto(); //pega caminho da foto
        Bitmap bitmap = null;
        try {
            //tranforma caminho da imagem em bitmap
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse("file://" + path));
        } catch (IOException e) { //pega o erro caso houver
            Log.d("ERRO",e.getMessage());
        }

        //captura os compoentes
        nome_cliente = (TextView) findViewById(R.id.nome_cliente);
        cpf_cliente = (TextView) findViewById(R.id.cpf_cliente);
        data_cliente = (TextView) findViewById(R.id.data_cliente);
        foto_cliente = (ImageView) findViewById(R.id.foto_cliente);

        //exibe os dados gerais
        nome_cliente.setText(usuario.getNome());
        cpf_cliente.setText(usuario.getCpf());
        data_cliente.setText(usuario.getData());
        foto_cliente.setImageBitmap(bitmap);
    }
}
