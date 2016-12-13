package com.alura.rafael.agendaalunos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.alura.rafael.agendaalunos.dao.AlunoDao;
import com.alura.rafael.agendaalunos.modelo.Aluno;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*String[] alunos = {"Rafael", "Antonio", "Marcelo", "Alberto", "Rafael", "Antonio",
                "Marcelo", "Alberto", "Rafael", "Antonio", "Marcelo", "Alberto"};*/

        listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View iten, int position, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);
                Toast.makeText(ListaAlunosActivity.this, "Aluno  " + aluno.getNome(), Toast.LENGTH_LONG).show();
            }
        });


        Button novo_aluno = (Button) findViewById(R.id.btn_novoAluno);
        novo_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiParaFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(vaiParaFormulario);
            }
        });

        /*Para aprarecer o deletar quando de um click longo no nome.*/
        registerForContextMenu(listaAlunos);
    }

    private void carregaLista() {
        AlunoDao dao = new AlunoDao(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        ArrayAdapter<Aluno> adpter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_expandable_list_item_1, alunos);
        listaAlunos.setAdapter(adpter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    //criar um menu de alunos.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        /*Esse comportamento pode ser deletado pois nao sera preciso usa lo.
        super.onCreateContextMenu(menu, v, menuInfo);*/
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                /*Lembrando que o menuItem é o item do deletar e nao o menu do item aluno*
                e graças ao metodo passado como parametro no menuInfo que ira apresentar o conteudo que foi clicado/
                 */
//                Vamos informar que o menuInfo é um adapter, para isto fazemos a conversao.
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);
                AlunoDao dao = new AlunoDao(ListaAlunosActivity.this);
                dao.deleta(aluno);
                dao.close();

                carregaLista();
//                Toast.makeText(ListaAlunosActivity.this, "Deletar o Aluno " + aluno.getNome(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }


}
