package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Atividades;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Kamila
 */
public class AtividadesDao {
    
    public Atividades consultar(int idAtividades) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select a from Atividades a where a.idatividades=" + idAtividades);
        Atividades atividades = null;
        if (q.getResultList().size()>0){
            atividades = (Atividades) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return atividades;
    }
    
    public Atividades salvar(Atividades atividades) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        atividades = manager.merge(atividades);
        manager.getTransaction().commit();
        return atividades;
    }
    
    
   public List<Atividades> listar(String sql) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Atividades> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
   
   public void Excluir(int idAtivdade) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("Select a From Atividades a where a.idatividades=" + idAtivdade);
        if (q.getResultList().size()>0){
            Atividades atividades = (Atividades) q.getResultList().get(0);
            manager.remove(atividades);
        }
        manager.getTransaction().commit();
    }
}
