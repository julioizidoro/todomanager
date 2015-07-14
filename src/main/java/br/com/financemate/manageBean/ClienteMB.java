package br.com.financemate.manageBean;


import br.com.financemate.bean.Formatacao;
import br.com.financemate.facade.ClienteFacade;
import br.com.financemate.model.Cliente;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Kamila
 */
@Named("ClienteMB")
@SessionScoped
public class ClienteMB implements Serializable{
    
    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;
    @Inject AtividadeMB atividadeMB;
    private Cliente cliente;
    private String nomeCliente;
    private List<Cliente> listaClientes;
    private String valorgestaofinanceira;
    private String valorcontabilidade;
    private String valortercerizacao;
    private String valoroutros;
    private String linha;
    private boolean contabilidade;
    private boolean gestaofinanceira;
    private boolean tercerizacao;
    private boolean outros;
    
    
    

    public ClienteMB() {
        cliente = new Cliente();
        gerarListaClientes();
    }
    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<Cliente> getListaClientes() {
        if (listaClientes==null){
            gerarListaClientes();
        }
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public String getValorgestaofinanceira() {
        return valorgestaofinanceira;
    }

    public void setValorgestaofinanceira(String valorgestaofinanceira) {
        this.valorgestaofinanceira = valorgestaofinanceira;
    }

    public String getValorcontabilidade() {
        return valorcontabilidade;
    }

    public void setValorcontabilidade(String valorcontabilidade) {
        this.valorcontabilidade = valorcontabilidade;
    }

    public String getValortercerizacao() {
        return valortercerizacao;
    }

    public void setValortercerizacao(String valortercerizacao) {
        this.valortercerizacao = valortercerizacao;
    }

    public String getValoroutros() {
        return valoroutros;
    }

    public void setValoroutros(String valoroutros) {
        this.valoroutros = valoroutros;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public boolean isContabilidade() {
        return contabilidade;
    }

    public void setContabilidade(boolean contabilidade) {
        this.contabilidade = contabilidade;
    }

    public boolean isGestaofinanceira() {
        return gestaofinanceira;
    }

    public void setGestaofinanceira(boolean gestaofinanceira) {
        this.gestaofinanceira = gestaofinanceira;
    }

    public boolean isTercerizacao() {
        return tercerizacao;
    }

    public void setTercerizacao(boolean tercerizacao) {
        this.tercerizacao = tercerizacao;
    }

    public boolean isOutros() {
        return outros;
    }

    public void setOutros(boolean outros) {
        this.outros = outros;
    }

    
    
    public void gerarListaClientes() {
        if (nomeCliente == null) {
            nomeCliente = "";
        }
        ClienteFacade clienteFacade = new ClienteFacade();
        listaClientes = clienteFacade.listar(nomeCliente);
        if (listaClientes == null) {
            listaClientes = new ArrayList<Cliente>();
        }
    }
    public String pesquisarNome(){
        gerarListaClientes();
        return "consCliente";
    
    } 
    public String novo(){
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadclienteincluir()){
            cliente = new Cliente();
            cliente.setSituacao("Ativo");
            cliente.setContabilidade(false);
            cliente.setGetaofinanceira(false);
            cliente.setTercerizacao(false);
            cliente.setOutros(false);
            contabilidade=true;
            tercerizacao=true;
            gestaofinanceira=true;
            outros=true;
            return "cadCliente";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    public String salvar() throws SQLException{
            ClienteFacade clienteFacade = new ClienteFacade();
            if (valorcontabilidade.length()>0){
                cliente.setValorcontabilidade(Formatacao.formatarStringfloat(valorcontabilidade));
            }else cliente.setValorcontabilidade(0.0f);
            if (valorgestaofinanceira.length()>0){
                cliente.setValorgestaofinanceira(Formatacao.ConvercaoMonetariaFloat(valorgestaofinanceira));
            }else cliente.setValorgestaofinanceira(0.0f);
            if (valoroutros.length()>0){
                cliente.setValoroutros(Formatacao.ConvercaoMonetariaFloat(valoroutros));
            }else cliente.setValoroutros(0.0f);
            if (valortercerizacao.length()>0){
                cliente.setValortercerizacao(Formatacao.ConvercaoMonetariaFloat(valortercerizacao));
            }else cliente.setValortercerizacao(0.0f);
            clienteFacade.salvar(cliente);
            valorcontabilidade="";
            valorgestaofinanceira="";
            valoroutros="";
            valortercerizacao="";
            cliente = new Cliente();
            gerarListaClientes();
             FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Cadastrado com Sucesso", ""));
            atividadeMB.gerarListaCliente();
            return "consCliente";
    }
    public String editar() throws SQLException{
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadclienteeditar()){
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
            int idCliente =  Integer.parseInt(params.get("id_cliente"));
            if (idCliente>0){
                ClienteFacade clienteFacade = new ClienteFacade();
                cliente = clienteFacade.consultar(idCliente);
                 if (cliente!=null){
                     valorcontabilidade = Formatacao.foramtarFloatString(cliente.getValorcontabilidade());
                     valorgestaofinanceira = Formatacao.foramtarFloatString(cliente.getValorgestaofinanceira());
                     valortercerizacao = Formatacao.foramtarFloatString(cliente.getValortercerizacao());
                     valoroutros = Formatacao.foramtarFloatString(cliente.getValoroutros());
                    return "cadCliente";
                }
            }
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return null;
    }
    
    public void pegarLinhaTabela(String linha){
        this.linha = linha;
    }
    
    public String habilitarDesabilitar(){
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadclientesituacao()){
            if (linha!=null){
                int nlinha = Integer.parseInt(linha);
               if (nlinha>=0){
                   if (listaClientes.get(nlinha).getSituacao().equalsIgnoreCase("Ativo")){
                       listaClientes.get(nlinha).setSituacao("Inativo");
                   }else listaClientes.get(nlinha).setSituacao("Ativo");
                   ClienteFacade clienteFacade = new ClienteFacade();
                   clienteFacade.salvar(listaClientes.get(nlinha));
                   atividadeMB.gerarListaCliente();
                   return "consCliente";
               } 
            }
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    public void alterarComboBox(){
        if (cliente.getContabilidade()){
            contabilidade = false;
        }else {
            contabilidade=true;
            valorcontabilidade="0,00";
        }
        if (cliente.getGetaofinanceira()){
            gestaofinanceira=false;
        }else {
            gestaofinanceira=true;
            valorgestaofinanceira="0,00";
        }
        if (cliente.getTercerizacao()){
            tercerizacao=false;
        }else {
            tercerizacao=true;
            valortercerizacao="0,00";
        }
        if (cliente.getOutros()){
            outros=false;
        }else {
            outros=true;
            valoroutros="0,00";
        }
    }

}
