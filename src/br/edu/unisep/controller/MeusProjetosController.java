package br.edu.unisep.controller;

import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.ProjetoDAO;
import br.edu.unisep.model.dao.TarefaDAO;
import br.edu.unisep.model.vo.ProjetoVO;
import br.edu.unisep.model.vo.TarefaVO;
import br.edu.unisep.utils.UsuarioUtils;
import br.edu.unisep.view.ProjetoCell;
import br.edu.unisep.view.TarefaCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MeusProjetosController extends AppController {

    @FXML private ListView<ProjetoVO> listProjetos;
    @FXML private ListView<TarefaVO> listTarefas;

    private ObservableList<ProjetoVO> projetos;
    private ObservableList<TarefaVO> tarefas;

    @Override
    protected void onInit() {
        projetos = FXCollections.observableArrayList();
        tarefas = FXCollections.observableArrayList();

        listarProjetos();

        listProjetos.getSelectionModel().selectedItemProperty()
                .addListener((value, anterior, atual) ->{
                    if (atual != null) {
                        listarTarefas(atual);
                    }
                });

        listProjetos.setCellFactory( f -> new ProjetoCell());
        listProjetos.setItems(projetos);

        listTarefas.setCellFactory( f -> new TarefaCell());
        listTarefas.setItems(tarefas);
    }


    private void listarProjetos() {
        var dao = new ProjetoDAO();
        var lista = dao.listarPorGerente(UsuarioUtils.getUsuario());
        projetos.setAll(lista);
    }

    private void listarTarefas(ProjetoVO proj){
        var dao = new TarefaDAO();
        var lista = dao.listar(proj);
        tarefas.setAll(lista);
    }

}