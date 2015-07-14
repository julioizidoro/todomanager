/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Rotinaatividade;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class RotinaAtividadeDao {
    
    
    public Rotinaatividade salvar(Rotinaatividade rotinaatividade) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        rotinaatividade = manager.merge(rotinaatividade);
        manager.getTransaction().commit();
        return rotinaatividade;
    }
    
    public List<Rotinaatividade> listar(String sql)throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Rotinaatividade> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public void excluir(Rotinaatividade rotinaatividade) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("Select r From Rotinaatividade r where r.idrotinaatividade=" + rotinaatividade.getIdrotinaatividade());
        if (q.getResultList().size()>0){
            rotinaatividade = (Rotinaatividade) q.getResultList().get(0);
            manager.remove(rotinaatividade);
        }
        manager.getTransaction().commit();
    }
    
    public Rotinaatividade consultar(int idRotina, int idCliente, String dataInicial, String dataFinal) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        String sql = "Select r From Rotinaatividade r where r.rotina.idrotina=" + idRotina +
                " and r.atividades.cliente.idcliente=" + idCliente + " and r.atividades.prazo>='" + dataInicial +
                "' and r.atividades.prazo<='" + dataFinal + "'";
        Query q = manager.createQuery(sql);
        List<Rotinaatividade> lista = q.getResultList();
        manager.getTransaction().commit();
        if (lista.size()>0){
            return lista.get(0);
                    
        }
        return null;
    }
    
}
