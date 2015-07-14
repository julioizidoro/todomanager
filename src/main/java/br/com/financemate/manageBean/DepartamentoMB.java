package br.com.financemate.manageBean;

import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Usuario;
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
@Named("DepartamentoMB")
@SessionScoped
public class DepartamentoMB implements Serializable{
    
    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;
    private Departamento departamento;
     private List<Usuario> listaUsuario;
    private List<Departamento> listaDepartamento;
    private String idUsuario="0";


    
    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento Departamento) {
        this.departamento = Departamento;
    }

    public List<Departamento> getListaDepartamento() {
        if (listaDepartamento==null){
            gerarListaDepartamento("");
        }
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Usuario> getListaUsuario() throws SQLException {
        if(listaUsuario==null){
            gerarListaUsuario();
        }
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    public void gerarListaDepartamento(String nome) {
        DepartamentoFacade departamentoFacade = new DepartamentoFacade();
        listaDepartamento = departamentoFacade.listar(nome);
        if (listaDepartamento == null) {
            listaDepartamento = new ArrayList<Departamento>();
        }
    }
    
    public String novo() throws SQLException{
        if(usuarioLogadoBean.getUsuario().getPerfil().getCaddepartamentoincluir()){
            departamento = new Departamento();
            gerarListaUsuario();
            return "cadDepartamento";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    public String salvar() throws SQLException{
        if(usuarioLogadoBean.getUsuario().getPerfil().getCaddepartamentoincluir()){
            DepartamentoFacade departamentoFacade = new DepartamentoFacade();
            UsuarioFacade usuarioFacade = new UsuarioFacade();
            Usuario usuario = usuarioFacade.consultar(Integer.parseInt(idUsuario));
            departamento.setUsuario(usuario);
            departamentoFacade.salvar(departamento);
            departamento = new Departamento();
            gerarListaDepartamento("");
             FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Cadastrado com Sucesso", ""));
            return "consDepartamento";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    public String editar() throws SQLException{
        if(usuarioLogadoBean.getUsuario().getPerfil().getCaddepartamentoeditar()){
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
            int idDepartamento =  Integer.parseInt(params.get("id_departamento"));
            if (idDepartamento>0){
                DepartamentoFacade departamentoFacade = new DepartamentoFacade();
                departamento = departamentoFacade.consultar(idDepartamento);
                 if (departamento!=null){
                     gerarListaUsuario();
                    return "cadDepartamento";
                }
            }
        }else{
           FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return null;
    }
    
    public void gerarListaUsuario() throws SQLException{
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        listaUsuario = usuarioFacade.listarAtivos();
        if (listaUsuario==null){
            listaUsuario = new ArrayList<Usuario>();
        }
    }
}
