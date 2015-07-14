/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Atividadeusuario;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class AtividadeUsuarioDao {
    
    
    public Atividadeusuario salvar(Atividadeusuario atividadeusuario) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        atividadeusuario = manager.merge(atividadeusuario);
        manager.getTransaction().commit();
        return atividadeusuario;
    }
    
    public List<Atividadeusuario> listar(String sql) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Atividadeusuario> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public void excluir(int idAtividadeUsuario) throws SQLException {
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("Seelct a From Atividadeusuario a where a.idatividadeusuario=" + idAtividadeUsuario);
        if (q.getResultList().size()>0){
            Atividadeusuario atividadeusuario = (Atividadeusuario) q.getResultList().get(0);
            manager.remove(atividadeusuario);
        }
        manager.getTransaction().commit();
    }
    
    public Atividadeusuario consultar(int idUsuario,  int idAtividade) throws SQLException {
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("Select a From Atividadeusuario a where a.atividades.idatividades=" + idAtividade +
                " and a.usuario.idusuario=" + idUsuario);
        Atividadeusuario atividadeusuario = null;
        if (q.getResultList().size()>0){
            atividadeusuario = (Atividadeusuario) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return atividadeusuario;
    }
    
}
