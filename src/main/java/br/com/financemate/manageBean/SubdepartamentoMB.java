package br.com.financemate.manageBean;

import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.facade.SubdepartamentoFacade;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Subdepartamento;
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
@Named("SubdepartamentoMB")
@SessionScoped
public class SubdepartamentoMB implements Serializable{
    
    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;
    private Subdepartamento subdepartamento;
    private List<Subdepartamento> listaSubdepartamento;
    private String idDepartamento="0";
    private List<Departamento> listaDepartamento;
    private String linha;

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public Subdepartamento getSubdepartamento() {
        return subdepartamento;
    }

    public void setSubdepartamento(Subdepartamento subdepartamento) {
        this.subdepartamento = subdepartamento;
    }

    public List<Subdepartamento> getListaSubdepartamento() {
        if (listaSubdepartamento==null){
            gerarListaSubdepartamento();
        }
        return listaSubdepartamento;
    }

    public void setListaSubdepartamento(List<Subdepartamento> listaSubdepartamento) {
        this.listaSubdepartamento = listaSubdepartamento;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    
    
    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public List<Departamento> getListaDepartamento() throws SQLException {
        if(listaDepartamento==null){
            gerarListaDepartamento();
        }
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }
    
    
    public void gerarListaSubdepartamento() {
        SubdepartamentoFacade subdepartamentoFacade = new SubdepartamentoFacade();
        listaSubdepartamento = subdepartamentoFacade.listar();
        if (listaSubdepartamento == null) {
            listaSubdepartamento = new ArrayList<Subdepartamento>();
        }
    }
    
    public String novo() throws SQLException{
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadsubdepartamentoincluir()){
            subdepartamento = new Subdepartamento();
            subdepartamento.setSituacao("Ativo");
            gerarListaDepartamento();
            return "cadSubdepartamento";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    public String salvar() throws SQLException{
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadsubdepartamentoincluir()){
            SubdepartamentoFacade subdepartamentoFacade = new SubdepartamentoFacade();
            DepartamentoFacade departamentoFacade = new DepartamentoFacade();
            Departamento departamento = departamentoFacade.consultar(Integer.parseInt(idDepartamento));
            subdepartamento.setDepartamento(departamento);
            subdepartamento.setSituacao("Ativo");
            subdepartamentoFacade.salvar(subdepartamento);
            subdepartamento = new Subdepartamento();
            gerarListaSubdepartamento();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Cadastrado com Sucesso", ""));
            return "consSubdepartamento";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    public String editar() throws SQLException{
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadsubdepartamentoeditar()){
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
            int idSub =  Integer.parseInt(params.get("id_subdepartamento"));
            if (idSub>0){
                SubdepartamentoFacade subdepartamentoFacade = new SubdepartamentoFacade();
                subdepartamento = subdepartamentoFacade.consultar(idSub);
                 if (subdepartamento!=null){
                     idDepartamento= String.valueOf(subdepartamento.getDepartamento().getIddepartamento());
                     gerarListaDepartamento();
                    return "cadSubdepartamento";
                }
            }
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return null;
    }
    
    public void gerarListaDepartamento() throws SQLException{
        DepartamentoFacade departamentoFacade = new DepartamentoFacade();
        listaDepartamento = departamentoFacade.listar("");
        if (listaDepartamento==null){
            listaDepartamento = new ArrayList<Departamento>();
        }
    }
    public void pegarLinhaTabela(String linha){
        this.linha = linha;
    }
    
    public String habilitarDesabilitar(){
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadsubdepartamentosituacao()){
            if (linha!=null){
                int nlinha = Integer.parseInt(linha);
               if (nlinha>=0){
                   if (listaSubdepartamento.get(nlinha).getSituacao().equalsIgnoreCase("Ativo")){
                       listaSubdepartamento.get(nlinha).setSituacao("Inativo");
                   }else listaSubdepartamento.get(nlinha).setSituacao("Ativo");
                   SubdepartamentoFacade subdepartamentoFacade = new SubdepartamentoFacade();
                   subdepartamentoFacade.salvar(listaSubdepartamento.get(nlinha));
                   return "consSubdepartamento";
               } 
            }
        }else{
           FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
}
