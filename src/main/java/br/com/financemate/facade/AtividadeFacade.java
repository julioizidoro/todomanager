/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.facade;

import br.com.financemate.dao.AtividadesDao;
import br.com.financemate.model.Atividades;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamila
 */
public class AtividadeFacade {
    
    AtividadesDao atividadesDao;
    
    public Atividades salvar(Atividades atividades) {
        atividadesDao = new AtividadesDao();
        try {
            return atividadesDao.salvar(atividades);
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Atividades consultar(int idAtividades) {
        atividadesDao = new AtividadesDao();
        try {
            return atividadesDao.consultar(idAtividades);
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Atividades> listar(String sql) {
        atividadesDao = new AtividadesDao();
        try {
            return atividadesDao.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void Excluir(int idAtivdade) {
        atividadesDao = new AtividadesDao();
        try {
            atividadesDao.Excluir(idAtivdade);
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
