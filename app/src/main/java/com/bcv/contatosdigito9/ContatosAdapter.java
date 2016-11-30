package com.bcv.contatosdigito9;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.ViewHolder> {

        private List<Contato> contatoList;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView nome, telefone,correcao;

            public ViewHolder(View view) {
                super(view);
                nome= (TextView) view.findViewById(R.id.nome);
                telefone= (TextView) view.findViewById(R.id.telefone);
                correcao= (TextView) view.findViewById(R.id.correcao);
            }
        }


        public ContatosAdapter(List<Contato> contatoList) {
            this.contatoList = contatoList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.lista_contatos, parent, false);

            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Contato contato= contatoList.get(position);
            holder.nome.setText(contato.getNome());
            holder.telefone.setText(contato.getTelefone());
            holder.correcao.setText(contato.getCorrecao());
        }

        @Override
        public int getItemCount() {
            return contatoList.size();
        }
    }
