package br.edu.unisep.model.dao;

import br.edu.unisep.hibernate.GenericDAO;
import br.edu.unisep.hibernate.HibernateSessionFactory;
import br.edu.unisep.model.vo.ProjetoVO;
import br.edu.unisep.model.vo.TarefaVO;
import br.edu.unisep.model.vo.UsuarioVO;

import java.util.List;

public class TarefaDAO extends GenericDAO<TarefaVO> {

    public List<TarefaVO> listar(ProjetoVO projeto) {
        var session = HibernateSessionFactory.getSession();

        var q = session.createQuery("from TarefaVO where projeto.id = :PROJETO", TarefaVO.class);
        q.setParameter("PROJETO", projeto.getId());

        var lista = q.list();

        session.close();

        return lista;
    }

}
