package br.com.financemate.manageBean;

import br.com.financemate.facade.NotificacaoFacade;
import br.com.financemate.model.Notificacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("MenuMB")
@SessionScoped
public class MenuMB implements Serializable{
    
    @Inject
    UsuarioLogadoBean usuarioLogadoBean;
    @Inject
    private InformacaoMB informacaoMB;
    private List<Notificacao> listaNotificacao;
    private String quantidade;

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }


    public List<Notificacao> getListaNotificacao() {
        if (listaNotificacao==null){
            gerarLitaNotificacao();
        }
        return listaNotificacao;
    }

    public void setListaNotificacao(List<Notificacao> listaNotificacao) {
        this.listaNotificacao = listaNotificacao;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
    
    
    
    
    public String cliente(){
        gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadcliente()){
            return"consCliente";    
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    public String departamento(){
        gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getCaddepartamento()){
            return"consDepartamento";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    
     public String subdepartamento(){
         gerarLitaNotificacao();
         if(usuarioLogadoBean.getUsuario().getPerfil().getCadsubdepartamento()){
            return"consSubdepartamento";
         }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
     
      public String rotina(){
        gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadrotina()){  
          return"consRotina";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
      public String situacao(){
        gerarLitaNotificacao();
        return"consSituacao";
    }
      
    public String financeiro(){
      gerarLitaNotificacao();
      return"gestaoFinanceira";  
    }
    
    public String contabilidade(){
      gerarLitaNotificacao();
      return"contabilidade";  
    }
    
    public String tercerizacao(){
      gerarLitaNotificacao();
      return"tercerizacao";  
    }
    
    public String usuario(){
        gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadusuario()){
            return"consUsuario";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
     public String perfil(){
         gerarLitaNotificacao();
         if(usuarioLogadoBean.getUsuario().getPerfil().getCadperfil()){
            return"consPerfil";
         }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
     
     public String informacao(){
       gerarLitaNotificacao();
       informacaoMB.gerarListaInformacao();
       return "informacoes";
     }
     
     public void gerarLitaNotificacao(){
         NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
         listaNotificacao = notificacaoFacade.listar(usuarioLogadoBean.getUsuario().getIdusuario());
         if (listaNotificacao==null){
             listaNotificacao = new ArrayList<Notificacao>();
         }
         if (listaNotificacao.size()<10){
            quantidade=  "  0" + String.valueOf(listaNotificacao.size()) + " ";
        }else quantidade = " " + String.valueOf(listaNotificacao.size()) + " ";
     }
     
     public void concluirNotificacao(Notificacao notificacao){
         NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
         notificacaoFacade.salvar(notificacao);
         gerarLitaNotificacao();
     }
     
     public void concluirListaNotificacao(){
         if (listaNotificacao!=null){
             NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
             for(int i=0;i<listaNotificacao.size();i++){
                 listaNotificacao.get(i).setLido(true);
                 notificacaoFacade.salvar(listaNotificacao.get(i));
             }
             gerarLitaNotificacao();
         }
     }
     
     
 }
