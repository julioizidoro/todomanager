/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.manageBean;

import br.com.financemate.bean.Formatacao;
import br.com.financemate.facade.AtividadeUsuarioFacade;
import br.com.financemate.model.Atividadeusuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */

@Named("InformacaoMB")
@SessionScoped
public class InformacaoMB implements Serializable{
    
    @Inject 
    private UsuarioLogadoBean usuarioLogadoBean;
    private List<Atividadeusuario> listaInformacao;
    

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public List<Atividadeusuario> getListaInformacao() {
        return listaInformacao;
    }

    public void setListaInformacao(List<Atividadeusuario> listaInformacao) {
        this.listaInformacao = listaInformacao;
    }

   
    public void gerarListaInformacao(){
        AtividadeUsuarioFacade atividadeUsuarioFacade = new AtividadeUsuarioFacade();
        String sql = "Select a from Atividadeusuario a where a.situacao=FALSE" + 
                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " and a.participacao<>'Executor'" +
                " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        listaInformacao = atividadeUsuarioFacade.lista(sql);
        if (listaInformacao==null){
            listaInformacao = new ArrayList<Atividadeusuario>();
        }
    }
    
    public String atrasadas(Atividadeusuario atividade) {
        if (atividade.getAtividades().getPrazo() != null) {
            Date data = new Date();
            String sData = Formatacao.ConvercaoDataPadrao(data);
            data = Formatacao.ConvercaoStringDataBrasil(sData);
            boolean bdata = atividade.getAtividades().getPrazo().before(data);
            if (bdata) {
                return "atrasado";
            } else {
                return "normal";
            }
        }
        return "normal";
    }
    
}
