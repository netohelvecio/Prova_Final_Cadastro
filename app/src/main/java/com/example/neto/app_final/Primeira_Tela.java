package com.example.neto.app_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Primeira_Tela extends AppCompatActivity {

    Button btn_cadastro, btn_visualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira__tela);

        //caputura componentes
        btn_cadastro = (Button) findViewById(R.id.btn_cadastro);
        btn_visualizar = (Button) findViewById(R.id.btn_visualizar);

        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abre segunda tela de cadastro
                Intent intent = new Intent(Primeira_Tela.this, Segunda_Tela.class);
                startActivity(intent);
            }
        });

        btn_visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abre terceira tela para visualizar clientes cadastrados
                Intent intent = new Intent(Primeira_Tela.this, TerceiraTela.class);
                startActivity(intent);
            }
        });
    }
}
