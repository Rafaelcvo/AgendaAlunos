package com.alura.rafael.agendaalunos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.alura.rafael.agendaalunos.modelo.Aluno;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Rafael on 06/12/2016.
 */

public class AlunoDao extends SQLiteOpenHelper {

    public AlunoDao(Context context) {
        super(context, "Agenda", null, 1);
    }

//    O android chama este metodo sempre que ele necessita criar um banco de dadeos.
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE Alunos(id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, tel TEXT, site TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        /*Esta instrução faz com que o banco de dados jogue a tabela fora para posteriormente ser chamado
        o onCreate(); para criar uma nova tabela.*/
        String sql = "DROP TABLE IF EXISTS Alunos;";
        db.execSQL(sql);

        onCreate(db);
    }

    public void insere(Aluno aluno) {
      /* Pode ser passado a instrução direta para o banco, mas assim podo ocorrer o risco de sofrer um ataque de
                sql. Entao a melhor formar é pedir ao SQLLite para fazer o tratamento.
        String sql = "INSERT INTO Alunos (nome, endereco, tel, site, nota) VALUES ()";*/

        ContentValues dados = pegaDadosDoAluno(aluno);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("Alunos", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDoAluno(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEnd());
        dados.put("tel", aluno.getTel());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());
        return dados;
    }

    public List<Aluno> buscaAlunos() {

        String sql = "SELECT * FROM Alunos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<Aluno>();
        while (c.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEnd(c.getString(c.getColumnIndex("endereco")));
            aluno.setTel(c.getString(c.getColumnIndex("tel")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            alunos.add(aluno);
        }
        c.close();
        return alunos;
    }

    public void deleta(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {aluno.getId().toString()};
        db.delete("Alunos", "id = ?", params);

    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosDoAluno(aluno);
        String[] params = {aluno.getId().toString()};
         db.update("Alunos", dados, "id = ?", params);
    }
}
