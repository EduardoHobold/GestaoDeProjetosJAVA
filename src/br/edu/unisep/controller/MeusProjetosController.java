package br.edu.unisep.controller;

import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.ProjetoDAO;
import br.edu.unisep.model.vo.ProjetoVO;
import br.edu.unisep.model.vo.TarefaVO;
import br.edu.unisep.utils.UsuarioUtils;
import br.edu.unisep.view.ProjetoCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MeusProjetosController extends AppController {

    @FXML private ListView<ProjetoVO> lstProjetos;

    @FXML private ListView<TarefaVO> lstTarefas;

    private ObservableList<ProjetoVO> projetos;
    private ObservableList<TarefaVO> tarefas;



    @Override
    protected void onInit() {
        projetos = FXCollections.observableArrayList();
        tarefas = FXCollections.observableArrayList();

        listarProjetos();
    }

    public void listarProjetos(){
        var dao = new ProjetoDAO();
        var lista = dao.listarPorGerente(UsuarioUtils.getUsuario());
        projetos.setAll(lista);

        lstProjetos.setItems(projetos);
        lstProjetos.setCellFactory(lstView -> new ProjetoCell());
    }

    public void listarTarefas(){

    }

}
