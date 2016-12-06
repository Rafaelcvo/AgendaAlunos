package com.alura.rafael.agendaalunos;

import android.widget.EditText;
import android.widget.RatingBar;

import com.alura.rafael.agendaalunos.modelo.Aluno;

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEnd;
    private final EditText campoTel;
    private final EditText campoSite;
    private final RatingBar campoNota;

    public FormularioHelper(FormularioActivity activity) {

        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEnd = (EditText) activity.findViewById(R.id.formulario_end);
        campoTel = (EditText) activity.findViewById(R.id.formulario_tel);
        campoSite = (EditText) activity.findViewById(R.id.formulario_site);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);

    }

    public Aluno pegarAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome(campoNome.getText().toString());
        aluno.setEnd(campoEnd.getText().toString());
        aluno.setTel(campoTel.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));
        return aluno;
    }
}
