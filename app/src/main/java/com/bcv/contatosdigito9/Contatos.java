package com.bcv.contatosdigito9;


import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Contatos extends AppCompatActivity {

    private List<Contato> contatoList;
    private RecyclerView recyclerView;
    private ContatosAdapter contatosAdapter;
    private ContatoProcessamento contatoProcessamento;
    private FloatingActionButton fabSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        contatoProcessamento = new ContatoProcessamento(this);
        contatoList = contatoProcessamento.buscaContatos();
        contatosAdapter = new ContatosAdapter(contatoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contatosAdapter);

        fabSalvar = (FloatingActionButton) findViewById(R.id.fab_salvar);

        fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        Contatos.this);

                alertDialogBuilder.setTitle("Deseja alterar seus contatos?");

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Sim",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                contatoProcessamento.atualizarContatos();
                            }
                        })
                        .setNegativeButton("Não",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }




}
