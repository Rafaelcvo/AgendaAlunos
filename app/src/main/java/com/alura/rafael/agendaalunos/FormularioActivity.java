package com.alura.rafael.agendaalunos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alura.rafael.agendaalunos.dao.AlunoDao;
import com.alura.rafael.agendaalunos.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

//     È preciso recuperar os dados da intent, para isto iremos criar uma nova intent..
        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

       if (aluno != null){
           helper.preencheFormulario(aluno);
       }

//        Button btn_salvar = (Button) findViewById(R.id.formulario_salvar);
//        btn_salvar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(FormularioActivity.this, "Aluno salvo!", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

//        O inflater esta transformando um menu xml em um menu de verdade
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                AlunoDao dao = new AlunoDao(this);
                Aluno aluno = helper.pegarAluno();

                if (aluno.getId() != null){
                    dao.altera(aluno);
                }else {
                    dao.insere(aluno);
                }
                dao.close();
                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " salvo!", Toast.LENGTH_SHORT).show();


               /*

               Para poder aproveita melhor o codigo e nao ter que sempre criar novos campos foi
               criado uma nova classe para tratar do findViewById.

                EditText campoNome = (EditText) findViewById(R.id.formulario_nome);
                String nome = campoNome.getText().toString();

                EditText campoEnd= (EditText) findViewById(R.id.formulario_end);
                String end = campoEnd.getText().toString();

                EditText campoTel= (EditText) findViewById(R.id.formulario_tel);
                String tel = campoTel.getText().toString();

                EditText campoSite= (EditText) findViewById(R.id.formulario_site);
                String site = campoSite.getText().toString();*/



                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
