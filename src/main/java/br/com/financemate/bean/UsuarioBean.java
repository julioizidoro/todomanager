/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.bean;

import br.com.financemate.model.Usuario;

/**
 *
 * @author Wolverine
 */
public class UsuarioBean {
    
    private Usuario usuario;
    private String participacao;
    private boolean selecionado;
    private int idAtividadeUsuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getParticipacao() {
        return participacao;
    }

    public void setParticipacao(String participacao) {
        this.participacao = participacao;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public int getIdAtividadeUsuario() {
        return idAtividadeUsuario;
    }

    public void setIdAtividadeUsuario(int idAtividadeUsuario) {
        this.idAtividadeUsuario = idAtividadeUsuario;
    }
    
    
    
    
}
