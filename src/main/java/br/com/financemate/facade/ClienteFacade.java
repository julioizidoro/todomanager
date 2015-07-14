package br.com.financemate.facade;

import br.com.financemate.dao.ClienteDao;
import br.com.financemate.model.Cliente;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClienteFacade {
    
    ClienteDao clienteDao;
    
    public Cliente salvar(Cliente cliente){
        clienteDao = new ClienteDao();
        try {
            return clienteDao.salvar(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public Cliente consultar(int idCliente){
        clienteDao = new ClienteDao();
        try {
            return clienteDao.consultar(idCliente);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Cliente> listar(String nome) {
        clienteDao = new ClienteDao();
        try {
            return clienteDao.listar(nome);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Cliente> listar(String nome, String situacao) {
        clienteDao = new ClienteDao();
        try {
            return clienteDao.listar(nome, situacao);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
