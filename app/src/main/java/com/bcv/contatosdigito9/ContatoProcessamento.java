package com.bcv.contatosdigito9;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class ContatoProcessamento {

    protected Context context;
    private  Contato novoContato;
    private String telefone = new String();
    ArrayList<Contato> contato = new ArrayList<Contato>();

    String[] ddd = {"51","53","54","55","47","48","49","41","42","43","44","45","46"};

    String _ID = ContactsContract.Contacts._ID;
    String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
    String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

    public  ContatoProcessamento(Context context){
            this.context = context;
    }

    protected ArrayList<Contato> buscaContatos() {

        ContentResolver contentResolver =  context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null,null, null, null);

        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String id = cursor.getString(cursor.getColumnIndex( _ID ));
                String nome = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));

                int temTelefone = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));

                if (temTelefone > 0) {

                    Cursor telefoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { id }, null);

                    while (telefoneCursor.moveToNext()) {
                        telefone = telefoneCursor.getString(telefoneCursor.getColumnIndex(NUMBER));
                        telefone = telefone.replace("-","");
                        telefone = telefone.replace("+","");
                        telefone = telefone.replace(" ", "");

                        novoContato  = new Contato();
                        novoContato.setNome(nome);

                        if ( encontraTamanho(telefone) <8 || ( encontraTamanho(telefone) == 8 && telefone.startsWith("3")  ) ) {
                                novoContato.setCorrecao(telefone);
                            } else {
                                if (encontraTamanho(telefone) == 8 ) {
                                    novoContato.setCorrecao("9"+telefone);
                                } else if (encontraTamanho(telefone) >= 10 )  {
                                    for (String DDD: ddd) {
                                        if (telefone.startsWith("55"+DDD) && telefone.length() == 12) {
                                            novoContato.setCorrecao(telefone.substring(0, 4) + "9" + telefone.substring(4, telefone.length()));
                                        }
                                        if (telefone.startsWith(DDD) && telefone.length() == 10) {
                                            novoContato.setCorrecao(telefone.substring(0, 2) + "9" + telefone.substring(2, telefone.length()));
                                        }
                                        if (telefone.startsWith("0" + DDD)) {
                                            novoContato.setCorrecao(telefone.substring(0, 3) + "9" + telefone.substring(3, telefone.length()));
                                        }
                                        if (telefone.startsWith("015" + DDD)) {
                                            novoContato.setCorrecao(telefone.substring(0, 5) + "9" + telefone.substring(5, telefone.length()));
                                        }

                                    }
                                } else  {
                                    novoContato.setCorrecao(telefone);
                                }
                        }
                        novoContato.setTelefone(telefone);
                        contato.add(novoContato);
                    }

                    telefoneCursor.close();


                }

            }

        }

        Toast.makeText(context, "Exibindo sua lista de contatos completa!", Toast.LENGTH_SHORT).show();

        return contato;
    }


    protected void atualizarContatos() {

        ContentResolver contentResolver =  context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null,null, null, null);

        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String id = cursor.getString(cursor.getColumnIndex( _ID ));
                String nome = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));

                int temTelefone = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));

                if (temTelefone > 0) {

                    Cursor telefoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { id }, null);

                    while (telefoneCursor.moveToNext()) {
                        telefone = telefoneCursor.getString(telefoneCursor.getColumnIndex(NUMBER));
                        telefone = telefone.replace("-","");
                        telefone = telefone.replace("+","");
                        telefone = telefone.replace(" ", "");

                        novoContato  = new Contato();
                        novoContato.setNome(nome);

                        if ( encontraTamanho(telefone) <8 || ( encontraTamanho(telefone) == 8 && telefone.startsWith("3")  ) ) {
                            novoContato.setCorrecao(telefone);
                        } else {
                            if (encontraTamanho(telefone) == 8 ) {
                                novoContato.setCorrecao("9"+telefone);
                            } else if (encontraTamanho(telefone) >= 10 )  {
                                for (String DDD: ddd) {
                                    if (telefone.startsWith("55"+DDD) && telefone.length() == 12) {
                                        novoContato.setCorrecao(telefone.substring(0, 4) + "9" + telefone.substring(4, telefone.length()));
                                    }
                                    if (telefone.startsWith(DDD) && telefone.length() == 10) {
                                        novoContato.setCorrecao(telefone.substring(0, 2) + "9" + telefone.substring(2, telefone.length()));
                                    }
                                    if (telefone.startsWith("0" + DDD)) {
                                        novoContato.setCorrecao(telefone.substring(0, 3) + "9" + telefone.substring(3, telefone.length()));
                                    }
                                    if (telefone.startsWith("015" + DDD)) {
                                        novoContato.setCorrecao(telefone.substring(0, 5) + "9" + telefone.substring(5, telefone.length()));
                                    }

                                }
                            } else  {
                                novoContato.setCorrecao(telefone);
                            }
                        }
                        novoContato.setTelefone(telefone);
                        contato.add(novoContato);
                    }

                   ArrayList<ContentProviderOperation> cPo =
                            new ArrayList<ContentProviderOperation>();

                    String[] selectionArgs = new String[]{id,String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)};
                    String selection = ContactsContract.Data.CONTACT_ID + "=? AND " + ContactsContract.Data.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE +
                            "' AND " + ContactsContract.CommonDataKinds.Phone.TYPE + "=?";
                    cPo.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                            .withSelection(selection, selectionArgs)
                            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, novoContato.getCorrecao())
                            .build());
                    try {
                        context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, cPo);
                    } catch (RemoteException e) {
                        Toast.makeText(context, "Erro na atualização!"+e.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (OperationApplicationException e) {
                        Toast.makeText(context, "Erro na atualização!"+e.getMessage(), Toast.LENGTH_LONG).show();

                    }

                    telefoneCursor.close();


                }

            }

        }

        Toast.makeText(context, "Fim atualização!", Toast.LENGTH_LONG).show();

    }


    private int encontraTamanho(String tamanho){

        return tamanho.length();

    }
}
