package com.example.neto.app_final;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Banco_de_Dados.DBConnection;
import Banco_de_Dados.Usuario;


public class Segunda_Tela extends AppCompatActivity {

    EditText et_nome, et_cpf, et_data, et_foto;
    Button btn_foto, btn_salvar, btn_cancelar;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda__tela);

        //captura componentes
        et_nome = (EditText) findViewById(R.id.et_nome);
        et_cpf = (EditText) findViewById(R.id.et_cpf);
        et_data = (EditText) findViewById(R.id.et_data);
        btn_salvar = (Button) findViewById(R.id.btn_salvar);
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
        btn_foto = (Button) findViewById(R.id.btn_foto);
        et_foto = (EditText) findViewById(R.id.et_foto);

        checaPermissao(); //usa metodo para pedir permissão ao usuario se pode ter acesso a galeria

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verifica se as caixas estão preenchidas
                if (et_nome.getText().toString().equals("") || et_cpf.getText().toString().equals("") || et_data.getText().toString().equals("") || et_foto.getText().toString().equals("")) {
                    // se estiver fazia apresenta toast
                    Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                } else { // senão insere dados no banco
                    insereDados(); //metodo que insere dados
                    Toast.makeText(context, "Usuário Cadastrado", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cancela cadastro e volta para tela principal
                finish();
            }
        });

        btn_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //inicia a galeria para pegar imagem
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //metodo pra ter retorno do caminho da imagem
        if (requestCode == 1 && resultCode == RESULT_OK) { // verifica se o codigo está correto
            if (data.getDataString() != null) { //verifica se imagem existe
                Uri myuriimage = data.getData(); //pega o caminho da imagem
                String path = getRealPathFromURI(this, myuriimage); //usa metodo de tratamento do caminho e adiciona ao path
                et_foto.setText(path); //apresenta path na tela
            } else { //caso não passar pela verificação apresenta erro
                Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //metodo para verificar se o usario deu permissão para acessar galeria
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else { }
        }
    }

    private void checaPermissao() { //metodo que pede permissão para acessar galeria
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    private void insereDados() { //metodo que insere dados no banco
        DBConnection connection = new DBConnection(this);

        //pega os textos dos editviews
        Usuario usuario = new Usuario(  et_nome.getText().toString(),
                                        et_cpf.getText().toString(),
                                        et_data.getText().toString(),
                                        et_foto.getText().toString()
        );

        boolean flag = connection.insertUsuarios(usuario); //insere os textos das textsviews

        if (!flag) { //verifica se ouve algum erro na inserção dos valores
            Log.d("BANCO", "ERROR");
        } else {
            Log.d("BANCO", "FUNCIONOU");
        }
    }



    public String getRealPathFromURI(Context context, Uri contentUri) { //metodo que converte o caminho da imagem para um caminho absoluto
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}