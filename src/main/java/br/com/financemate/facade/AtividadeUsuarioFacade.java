/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.facade;

import br.com.financemate.dao.AtividadeUsuarioDao;
import br.com.financemate.model.Atividadeusuario;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wolverine
 */
public class AtividadeUsuarioFacade {
    
    AtividadeUsuarioDao atividadeUsuarioDao;
    
    public Atividadeusuario salvar(Atividadeusuario atividadeusuario) {
        atividadeUsuarioDao = new AtividadeUsuarioDao();
        try {
            return atividadeUsuarioDao.salvar(atividadeusuario);
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeUsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Atividadeusuario> lista(String sql) {
        atividadeUsuarioDao = new AtividadeUsuarioDao();
        try {
            return atividadeUsuarioDao.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeUsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void excluir(int idAtividadeUsuario) {
        atividadeUsuarioDao = new AtividadeUsuarioDao();
        try {
            atividadeUsuarioDao.excluir(idAtividadeUsuario);
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeUsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Atividadeusuario consultar(int idUsuario,  int idAtividade)  {
        atividadeUsuarioDao = new AtividadeUsuarioDao();
        try {
            return atividadeUsuarioDao.consultar(idUsuario, idAtividade);
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeUsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
