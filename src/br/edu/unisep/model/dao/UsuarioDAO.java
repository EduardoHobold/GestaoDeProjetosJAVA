package br.edu.unisep.model.dao;

import br.edu.unisep.hibernate.GenericDAO;
import br.edu.unisep.hibernate.HibernateSessionFactory;
import br.edu.unisep.model.vo.ProjetoVO;
import br.edu.unisep.model.vo.UsuarioVO;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class UsuarioDAO extends GenericDAO<UsuarioVO> {

    public UsuarioVO login(UsuarioVO u){
        var session = HibernateSessionFactory.getSession();

        var q = session.createQuery(
                "from UsuarioVO where email = :EMAIL and senha = :SENHA", UsuarioVO.class);
        q.setParameter("EMAIL", u.getEmail());

        var senha = DigestUtils.md5Hex(u.getSenha());
        q.setParameter("SENHA", senha);

        var usuario = q.uniqueResult();

        session.close();

        return usuario;
    }

    public List<UsuarioVO> ListarPorTipo(Integer tipo){
        var session = HibernateSessionFactory.getSession();

        var q = session.createQuery("from UsuarioVO where tipo = :TIPO", UsuarioVO.class);
        q.setParameter("TIPO", tipo);

        var lista = q.list();

        session.close();
        return  lista;
    }

    public List<UsuarioVO> listarDisponiveisEquipe(ProjetoVO projeto){
        var session = HibernateSessionFactory.getSession();

        var sql = new StringBuffer();
        sql.append("select u.* from usuario u ");
        sql.append("where u.tp_usuario = 3 ");
        sql.append("and not exists( ");
        sql.append("select 1 from membro_equipe me ");
        sql.append("where me.id_usuario = u.id_usuario ");
        sql.append("where me.id_projeto = :PROJETO)");

        var q = session.createNativeQuery(sql.toString(), UsuarioVO.class);

        q.setParameter("PROJETO", projeto.getId());

        var lista = q.list();

        session.close();
        return lista;
    }

}
