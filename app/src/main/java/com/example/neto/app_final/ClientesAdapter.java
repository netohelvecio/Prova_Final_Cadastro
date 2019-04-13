package com.example.neto.app_final;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import Banco_de_Dados.Usuario;

public class ClientesAdapter extends ArrayAdapter<Usuario> {
    Context context;
    ArrayList<Usuario> list;

    //construtor
   public ClientesAdapter(Context context, ArrayList<Usuario> list){
       super(context, R.layout.activity_lista, list);
       this.context = context;
       this.list = list;
   }

    TextView tv_nome_lv, tv_cpf_lv;
    Button btn_visualizar_lv;
    Usuario usuario;

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_lista, parent, false);
        }

        //captura componentes
        tv_nome_lv = (TextView) view.findViewById(R.id.tv_nome_lv);
        tv_cpf_lv = (TextView) view.findViewById(R.id.tv_cpf_lv);
        btn_visualizar_lv = (Button) view.findViewById(R.id.btn_visualizar_lv);

        //altera caixas para exibir as informações
        tv_nome_lv.setText(list.get(position).getNome());
        tv_cpf_lv.setText(list.get(position).getCpf());

        btn_visualizar_lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abre tela com dados especificos do usuario
                usuario = list.get(position);
                Intent intent = new Intent(context, Quarta_Tela.class);
                intent.putExtra("obj", usuario);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
