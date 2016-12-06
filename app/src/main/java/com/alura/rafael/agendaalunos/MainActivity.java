package com.alura.rafael.agendaalunos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.alura.rafael.agendaalunos.dao.AlunoDao;
import com.alura.rafael.agendaalunos.modelo.Aluno;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*String[] alunos = {"Rafael", "Antonio", "Marcelo", "Alberto", "Rafael", "Antonio",
                "Marcelo", "Alberto", "Rafael", "Antonio", "Marcelo", "Alberto"};*/



        Button novo_aluno =(Button) findViewById(R.id.btn_novoAluno);
        novo_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiParaFormulario = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(vaiParaFormulario);
            }
        });
    }

    private void carregaLista() {
        AlunoDao dao = new AlunoDao(this);
        List<Aluno> alunos =  dao.buscaAlunos();
        dao.close();
        ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        ArrayAdapter<Aluno> adpter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_expandable_list_item_1, alunos);
        listaAlunos.setAdapter(adpter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }
}
