package com.bcv.contatosdigito9;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class Contato {

        private String nome, telefone, correcao;

        public Contato() {
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone =  telefone;
        }

        public String getCorrecao() {
           return correcao;
        }

        public void setCorrecao(String correcao) {
        this.correcao = correcao;
    }



    }
