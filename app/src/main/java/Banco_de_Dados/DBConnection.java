package Banco_de_Dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.neto.app_final.ClientesAdapter;

import java.util.ArrayList;
import java.util.List;

public class DBConnection extends SQLiteOpenHelper {

    //cria strings que serão usadas no banco
    public static final String NOME_BANCO = "banco_usuarios.db";
    public static final String NOME_TABELA = "usuarios";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String CPF = "cpf";
    public static final String DATA = "data";
    public static final String FOTO = "foto";
    public static final int VERSION = 1;

    //construtor
    public DBConnection(Context context){
        super(context, NOME_BANCO, null, VERSION);
    }

    //criação da tabela no banco
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + NOME_TABELA +
                " (" + ID + " integer primary key autoincrement, " +
                NOME + " text, " + CPF + " text, " + DATA + " text, " + FOTO + " text)";

        db.execSQL(sql); //cria a tabela no banco
    }

    // deleta e cria nova tabela caso mudar de versão
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
        onCreate(db);
    }

    public boolean insertUsuarios(Usuario usuario){ //metodo para inserir os dados do usuario na tabela
        ContentValues valores = new ContentValues(); //objeto usado para formatar valores
        SQLiteDatabase db = getWritableDatabase(); //permite a leitura e gravação dos dados

        //coleta os dados
        valores.put("nome", usuario.getNome());
        valores.put("cpf", usuario.getCpf());
        valores.put("data", usuario.getData());
        valores.put("foto", usuario.getFoto());

        //insere os dados na tabela
        long result = db.insert(NOME_TABELA, null, valores);
        db.close();

        if(result == -1){ //verifica se houve erro de inserção
            return  false; //erro na inserção
        }

        return true; //inserção com sucesso
    }

    public ArrayList carregaDados(){
        ArrayList<Usuario> list = new ArrayList<Usuario>(); //cria uma arraylist da classe usuario
        Cursor cursor; //objeto que armazena resultados da consulta
        String [] campos = {NOME, CPF, DATA, FOTO}; //array que retorna os valores consultados

        SQLiteDatabase db = getReadableDatabase(); //obtem a conexão com o banco(somente para leitura)
        cursor = db.query(NOME_TABELA, campos, null, null, null, null, null, null); // faz o select dos valores

        if(cursor != null){ //reposiciona o cursor para o primeiro elemento
            cursor.moveToFirst();
        }

        do { //usa do while para carregar usuarios enquanto houver
            Usuario usuario = new Usuario();
            usuario.setNome(cursor.getString(0));
            usuario.setCpf(cursor.getString(1));
            usuario.setData(cursor.getString(2));
            usuario.setFoto(cursor.getString(3));
            list.add(usuario);
        } while (cursor.moveToNext()); //move para o proximo usuario até o ultimo cadastrado

        db.close();
        return list; //retorna lista de usuarios
    }
}
