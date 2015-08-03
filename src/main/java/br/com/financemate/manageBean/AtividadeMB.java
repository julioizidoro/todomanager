package br.com.financemate.manageBean;


import br.com.financemate.bean.Formatacao;
import br.com.financemate.bean.UsuarioBean;
import br.com.financemate.facade.AtividadeFacade;
import br.com.financemate.facade.AtividadeUsuarioFacade;
import br.com.financemate.facade.ClienteFacade;
import br.com.financemate.facade.ComentariosFacade;
import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.facade.NotificacaoFacade;
import br.com.financemate.facade.RotinaAtividadeFacade;
import br.com.financemate.facade.RotinaclienteFacade;
import br.com.financemate.facade.SubdepartamentoFacade;
import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Atividadeusuario;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Comentarios;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Notificacao;
import br.com.financemate.model.Rotinaatividade;
import br.com.financemate.model.Rotinacliente;
import br.com.financemate.model.Subdepartamento;
import br.com.financemate.model.Usuario;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Kamila
 */
@Named("AtividadeMB")
@SessionScoped
public class AtividadeMB implements Serializable{
    
    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;
    @Inject
    private MenuMB menuMB;
    @Inject
    private CalendarioMB calendarioMB;
    private Atividades atividades;
    private List<Departamento> listaDepartamento;
    private List<Subdepartamento> listaSubdepartamento;
    private List<Cliente> listaCliente;
    private String idDepartamento="0";
    private String idCliente;
    private String idSubdepartamento="0";
    private List<Usuario> listaUsuario;
    private String idUsuario="0";
    private List<Atividadeusuario> listaAtividadedia;
    private List<Atividadeusuario> listaAtividadeSemana;
    private List<Atividadeusuario> listaAtividadeAtrasada;
    private List<Atividadeusuario> listaTodasAtividade;
    private List<Atividadeusuario> listaAtividadesDepartamento;
    private List<Atividadeusuario> listaAtividadesGeral;
    private List<Atividadeusuario> listaAtividadesAmanha;
    private List<Atividadeusuario> listaAtividadesDois;
    private List<Atividadeusuario> listaAtividadesTres;
    private List<Atividadeusuario> listaAtividadesQuatro;
    private List<Atividadeusuario> listaAtividadesCinco;
    private List<Atividadeusuario> listaAtividadesSeis;
    private List<Atividadeusuario> listaAtividadesSete;
    private List<Comentarios> listaComentarios;
    private String atividadeMenu="dia";
    private String ndia;
    private String nsemana;
    private String natrasada;
    private String ndepartamento;
    private String namanha;
    private String dois;
    private String tres;
    private String quatro;
    private String cinco;
    private String seis;
    private String sete;
    private String titulo="Tarefas de Hoje";
    private String linha;
    private String todas;
    private boolean checkConcluidas=false;
    private Comentarios comentarios;
    private String nomeAtividades;
    private String visualizar;
    private List<UsuarioBean> listaUsuarioBean;
    private int idExecutor;
    private int tipo=0;
    

    public AtividadeMB()  {
         atividadeMenu="dia";
        atividades = new Atividades();
        comentarios = new Comentarios();
    }

    public List<Atividadeusuario> getListaAtividadesGeral()  {
        if (listaAtividadesGeral==null){
            listarAtividadesDia();
            listaAtividadesGeral= listaAtividadedia;
        }
        return listaAtividadesGeral;
    }

    public void setListaAtividadesGeral(List<Atividadeusuario> listaAtividadesGeral) {
        this.listaAtividadesGeral = listaAtividadesGeral;
    }

    public List<Atividadeusuario> getListaAtividadedia()  {
        if (listaAtividadedia==null){
            listarAtividadesDia();
        }
        return listaAtividadedia;
    }

    public List<Comentarios> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<Comentarios> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public int getIdExecutor() {
        return idExecutor;
    }

    public void setIdExecutor(int idExecutor) {
        this.idExecutor = idExecutor;
    }

    public void setListaAtividadedia(List<Atividadeusuario> listaAtividadedia) {
        this.listaAtividadedia = listaAtividadedia;
    }

    public String getVisualizar() {
        return visualizar;
    }

    public void setVisualizar(String visualizar) {
        this.visualizar = visualizar;
    }

    public List<Atividadeusuario> getListaAtividadeSemana()  {
        if (listaAtividadeSemana==null){
            listarAtividadesAmanha();
        }
        return listaAtividadeSemana;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public List<UsuarioBean> getListaUsuarioBean() {
        return listaUsuarioBean;
    }

    public void setListaUsuarioBean(List<UsuarioBean> listaUsuarioBean) {
        this.listaUsuarioBean = listaUsuarioBean;
    }

    public List<Atividadeusuario> getListaTodasAtividade() {
         if (listaTodasAtividade==null){
           listarTodasAtividades();
        }
        return listaTodasAtividade;
    }

    public void setListaTodasAtividade(List<Atividadeusuario> listaTodasAtividade) {
        this.listaTodasAtividade = listaTodasAtividade;
    }

    public List<Atividadeusuario> getListaAtividadesAmanha() {
        if(listaAtividadesAmanha==null){
            listarAtividadesAmanha();
        }
        return listaAtividadesAmanha;
    }

    public void setListaAtividadesAmanha(List<Atividadeusuario> listaAtividadesAmanha) {
        this.listaAtividadesAmanha = listaAtividadesAmanha;
    }

    public List<Atividadeusuario> getListaAtividadesDois() {
        if(listaAtividadesDois==null){
            listarAtividadesDois();
        }
        return listaAtividadesDois;
    }

    public void setListaAtividadesDois(List<Atividadeusuario> listaAtividadesDois) {
        this.listaAtividadesDois = listaAtividadesDois;
    }

    public List<Atividadeusuario> getListaAtividadesTres() {
        if(listaAtividadesTres==null){
            listarAtividadesTres();
        }
        return listaAtividadesTres;
    }

    public void setListaAtividadesTres(List<Atividadeusuario> listaAtividadesTres) {
        this.listaAtividadesTres = listaAtividadesTres;
    }

    public List<Atividadeusuario> getListaAtividadesQuatro() {
        if(listaAtividadesQuatro==null){
            listarAtividadesQuatro();
        }
        return listaAtividadesQuatro;
    }

    public void setListaAtividadesQuatro(List<Atividadeusuario> listaAtividadesQuatro) {
        this.listaAtividadesQuatro = listaAtividadesQuatro;
    }

    public List<Atividadeusuario> getListaAtividadesCinco() {
        if(listaAtividadesCinco==null){
            listarAtividadesCinco();
        }
        return listaAtividadesCinco;
    }

    public void setListaAtividadesCinco(List<Atividadeusuario> listaAtividadesCinco) {
        this.listaAtividadesCinco = listaAtividadesCinco;
    }

    public Comentarios getComentarios() {
        return comentarios;
    }

    public void setComentarios(Comentarios comentarios) {
        this.comentarios = comentarios;
    }
    
    

    public List<Atividadeusuario> getListaAtividadesSeis() {
        if(listaAtividadesSeis==null){
            listarAtividadesSeis();
        }
        return listaAtividadesSeis;
    }

    public void setListaAtividadesSeis(List<Atividadeusuario> listaAtividadesSeis) {
        this.listaAtividadesSeis = listaAtividadesSeis;
    }

    public List<Atividadeusuario> getListaAtividadesSete() {
        if(listaAtividadesSete==null){
            listarAtividadesSete();
        }
        return listaAtividadesSete;
    }

    public void setListaAtividadesSete(List<Atividadeusuario> listaAtividadesSete) {
        this.listaAtividadesSete = listaAtividadesSete;
    }

    public String getNamanha() {
        return namanha;
    }

    public void setNamanha(String namanha) {
        this.namanha = namanha;
    }

    public String getTodas() {
        return todas;
    }

    public void setTodas(String todas) {
        this.todas = todas;
    }
    

    public String getDois() {
        return dois;
    }

    public void setDois(String dois) {
        this.dois = dois;
    }

    public String getTres() {
        return tres;
    }

    public void setTres(String tres) {
        this.tres = tres;
    }

    public String getQuatro() {
        return quatro;
    }

    public void setQuatro(String quatro) {
        this.quatro = quatro;
    }

    public String getCinco() {
        return cinco;
    }

    public void setCinco(String cinco) {
        this.cinco = cinco;
    }

    public String getSeis() {
        return seis;
    }

    public void setSeis(String seis) {
        this.seis = seis;
    }

    public String getSete() {
        return sete;
    }

    public void setSete(String sete) {
        this.sete = sete;
    }

    
    
    public boolean isCheckConcluidas() {
        return checkConcluidas;
    }

    public void setCheckConcluidas(boolean checkConcluidas) {
        this.checkConcluidas = checkConcluidas;
    }

    public void setListaAtividadeSemana(List<Atividadeusuario> listaAtividadeSemana) {
        this.listaAtividadeSemana = listaAtividadeSemana;
    }

    public List<Atividadeusuario> getListaAtividadeAtrasada()  {
        if (listaAtividadeAtrasada==null){
            listarAtividadesAtrasadas();
        }
        return listaAtividadeAtrasada;
    }

    public void setListaAtividadeAtrasada(List<Atividadeusuario> listaAtividadeAtrasada) {
        this.listaAtividadeAtrasada = listaAtividadeAtrasada;
    }

    public List<Atividadeusuario> getListaAtividadesDepartamento()  {
        if (listaAtividadesDepartamento==null){
            listarAtividadesDepartamento();
        }
        return listaAtividadesDepartamento;
    }

    public void setListaAtividadesDepartamento(List<Atividadeusuario> listaAtividadesDepartamento) {
        this.listaAtividadesDepartamento = listaAtividadesDepartamento;
    }

    public String getAtividadeMenu() {
        return atividadeMenu;
    }

    public void setAtividadeMenu(String atividadeMenu) {
        this.atividadeMenu = atividadeMenu;
    }

    public String getNdia() {
        return ndia;
    }

    public void setNdia(String ndia) {
        this.ndia = ndia;
    }

    public String getNsemana() {
        return nsemana;
    }

    public void setNsemana(String nsemana) {
        this.nsemana = nsemana;
    }

    public String getNatrasada() {
        return natrasada;
    }

    public void setNatrasada(String natrasada) {
        this.natrasada = natrasada;
    }

    public String getNdepartamento() {
        return ndepartamento;
    }

    public void setNdepartamento(String ndepartamento) {
        this.ndepartamento = ndepartamento;
    }

     
    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public Atividades getAtividades() {
        return atividades;
    }

    public void setAtividades(Atividades departamento) {
        this.atividades = departamento;
    }

    public List<Departamento> getListaDepartamento()  {
        if(listaDepartamento==null){
            gerarListaDepartamento();
        }
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<Subdepartamento> getListaSubdepartamento()  {
        if(listaSubdepartamento==null){
            gerarListaSubdepartamento();
        }
        return listaSubdepartamento;
    }

    public void setListaSubdepartamento(List<Subdepartamento> listaSubdepartamento) {
        this.listaSubdepartamento = listaSubdepartamento;
    }

    public List<Cliente> getListaCliente()  {
        if(listaCliente==null){
            gerarListaCliente();
        }
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdSubdepartamento() {
        return idSubdepartamento;
    }

    public void setIdSubdepartamento(String idSubdepartamento) {
        this.idSubdepartamento = idSubdepartamento;
    }

    public List<Usuario> getListaUsuario()  {
        if(listaUsuario==null){
            gerarListaUsuario();
        }
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNomeAtividades() {
        return nomeAtividades;
    }

    public void setNomeAtividades(String nomeAtividades) {
        this.nomeAtividades = nomeAtividades;
    }
    
    
    
    
    
    public String novo(){
        menuMB.gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefasincluir()){
            atividades = new Atividades();
            atividades.setTempo(0);
            atividades.setEstado("Play");
            atividades.setMostratempo("00:00");
            idUsuario = String.valueOf(usuarioLogadoBean.getUsuario().getIdusuario());
            idCliente = "4";
            idDepartamento = String.valueOf(usuarioLogadoBean.getUsuario().getSubdepartamento().getDepartamento().getIddepartamento());
            gerarListaSubdepartamento();
            idSubdepartamento = String.valueOf(usuarioLogadoBean.getUsuario().getSubdepartamento().getIdsubdepartamento());
            atividades.setPrazo(new Date());
            atividades.setPrioridade("normal");
            gerarListaUsuarioBean();
            tipo=0;
             RequestContext.getCurrentInstance().openDialog("cadastroTarefa");
        }else{
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public String novoParticular(){
        menuMB.gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefasincluir()){
            atividades = new Atividades();
            atividades.setTempo(0);
            atividades.setEstado("Play");
            atividades.setMostratempo("00:00");
            atividades.setPrazo(new Date());
            atividades.setPrioridade("normal");
            tipo=1;
             RequestContext.getCurrentInstance().openDialog("cadastroTarefaParticular");
        }else{
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
            
            
     public String salvarParticular() {
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefasincluir()){
            idUsuario = String.valueOf(usuarioLogadoBean.getUsuario().getIdusuario());
            idCliente = "1";
            idDepartamento = String.valueOf(usuarioLogadoBean.getUsuario().getSubdepartamento().getDepartamento().getIddepartamento());
            idSubdepartamento = String.valueOf(usuarioLogadoBean.getUsuario().getSubdepartamento().getIdsubdepartamento());
            idExecutor = Integer.parseInt(idUsuario);
            AtividadeFacade atividadeFacade = new AtividadeFacade();
            SubdepartamentoFacade subdepartamentoFacade = new SubdepartamentoFacade();
            Subdepartamento subddepartamento = subdepartamentoFacade.consultar(Integer.parseInt(idSubdepartamento));
            atividades.setSubdepartamento(subddepartamento);
            atividades.setEstado("Play");
            atividades.setInicio(BigInteger.valueOf(0));
            atividades.setTempo(0);
            atividades.setMostratempo("00:00");
            ClienteFacade clienteFacade = new ClienteFacade();
            Cliente cliente = clienteFacade.consultar(Integer.parseInt(idCliente));
            atividades.setCliente(cliente);
            atividades.setTipo("A");
            atividades.getComentariosList();
            UsuarioFacade usuarioFacade = new UsuarioFacade();
            Usuario usuario = usuarioFacade.consultar(Integer.parseInt(idUsuario));
            atividades = atividadeFacade.salvar(atividades);
            salvarUsuarioAtividadeParticular();
            atividadeMenu="dia";
            listarAtividadesAtrasadas();
            listarAtividadesDia();
            listarAtividadesSemana();
            listarTodasAtividades();
            listarAtividadesAmanha();
            listarAtividadesDois();
            listarAtividadesTres();
            listarAtividadesQuatro();
            listarAtividadesCinco();
            listarAtividadesSeis();
            listarAtividadesSete();
            listarAtividadesDepartamento();
            carregarListaGeral();
            atividades = new Atividades();
            idCliente="0";
            idUsuario="0";
            idDepartamento="0";
            idSubdepartamento="0";
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Cadastrado com Sucesso", ""));
            atividadeMenu="dia";
             RequestContext.getCurrentInstance().closeDialog("inicial");
            return "inicial";
        }else{
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
            
            
    public String salvar() {
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefasincluir()){
            idExecutor = Integer.parseInt(idUsuario);
            AtividadeFacade atividadeFacade = new AtividadeFacade();
            SubdepartamentoFacade subdepartamentoFacade = new SubdepartamentoFacade();
            Subdepartamento subddepartamento = subdepartamentoFacade.consultar(Integer.parseInt(idSubdepartamento));
            atividades.setSubdepartamento(subddepartamento);
            atividades.setEstado("Play");
            atividades.setInicio(BigInteger.valueOf(0));
            atividades.setTempo(0);
            atividades.setMostratempo("00:00");
            ClienteFacade clienteFacade = new ClienteFacade();
            Cliente cliente = clienteFacade.consultar(Integer.parseInt(idCliente));
            atividades.setCliente(cliente);
            atividades.setTipo("A");
            atividades.getComentariosList();
            UsuarioFacade usuarioFacade = new UsuarioFacade();
            Usuario usuario = usuarioFacade.consultar(Integer.parseInt(idUsuario));
            atividades = atividadeFacade.salvar(atividades);
            salvarUsuarioAtividade();
            atividadeMenu="dia";
            listarAtividadesAtrasadas();
            listarAtividadesDia();
            listarAtividadesSemana();
            listarTodasAtividades();
            listarAtividadesAmanha();
            listarAtividadesDois();
            listarAtividadesTres();
            listarAtividadesQuatro();
            listarAtividadesCinco();
            listarAtividadesSeis();
            listarAtividadesSete();
            listarAtividadesDepartamento();
            carregarListaGeral();
            atividades = new Atividades();
            idCliente="0";
            idUsuario="0";
            idDepartamento="0";
            idSubdepartamento="0";
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Cadastrado com Sucesso", ""));
            atividadeMenu="dia";
            RequestContext.getCurrentInstance().closeDialog("inicial");
            return "inicial";
        }else{
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    
    
    public void gerarListaCliente() {
        ClienteFacade clienteFacade = new ClienteFacade();
        listaCliente = clienteFacade.listar("", "Ativo");
        if (listaCliente==null){
            listaCliente = new ArrayList<Cliente>();
        }
    }
    
    public void gerarListaDepartamento() {
        DepartamentoFacade departamentoFacade = new DepartamentoFacade();
        listaDepartamento = departamentoFacade.listar("");
        if (listaDepartamento==null){
            listaDepartamento = new ArrayList<Departamento>();
        }
    }
    
    public void gerarListaSubdepartamento()  {
        if (!idDepartamento.equalsIgnoreCase("0")) {
            SubdepartamentoFacade subdepartamentoFacade = new SubdepartamentoFacade();
            listaSubdepartamento = subdepartamentoFacade.listar("", Integer.parseInt(idDepartamento));
            if (listaSubdepartamento == null) {
                listaSubdepartamento = new ArrayList<Subdepartamento>();
            }
        }else {
            listaSubdepartamento = new ArrayList<Subdepartamento>();
        }
    }
    
    public void gerarListaUsuario() {
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        listaUsuario = usuarioFacade.listarAtivos();
        if (listaUsuario==null){
            listaUsuario = new ArrayList<Usuario>();
        }
    }
    
    
    
    public  void listarAtividadesDia()  {
        AtividadeUsuarioFacade atividadeUsuarioFacade = new AtividadeUsuarioFacade();
        String sql = "Select a from Atividadeusuario a where  a.atividades.prazo<='" + Formatacao.ConvercaoDataSql(new Date()) + 
                "' and a.situacao=" + isCheckConcluidas() + " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " and a.participacao='Executor'" +
                " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        listaAtividadedia = atividadeUsuarioFacade.lista(sql);
        if (listaAtividadedia==null){
            listaAtividadedia = new ArrayList<Atividadeusuario>();
        }
        if (listaAtividadedia.size()<10){
            ndia = "Hoje (0" + String.valueOf(listaAtividadedia.size()) + ")";
        }else ndia = "Hoje (" + String.valueOf(listaAtividadedia.size()) +")";
        
    }
    public  void listarTodasAtividades()  {
        if (nomeAtividades == null) {
            nomeAtividades = " ";
        }
        AtividadeUsuarioFacade atividadesAtividadeFacade = new AtividadeUsuarioFacade();
        String sql = "Select a from Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " and a.atividades.nome like '%" + nomeAtividades + "%' and a.situacao=FALSE  and a.participacao='Executor'" 
                + " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        listaTodasAtividade = atividadesAtividadeFacade.lista(sql);
        if (listaTodasAtividade==null){
            listaTodasAtividade = new ArrayList<Atividadeusuario>();
        }
        if (listaTodasAtividade.size()<10){
            todas = "Todas (0" + String.valueOf(listaTodasAtividade.size()) + ")";
        }else todas = "Todas (" + String.valueOf(listaTodasAtividade.size()) +")";
        
    }
    
    public  void listarAtividadesSemana()  {
        AtividadeUsuarioFacade atividadesAtividadeFacade = new AtividadeUsuarioFacade();
        Date data = Formatacao.SomarDiasData(new Date(), 7);
        String sql = "Select a from Atividadeusuario a where a.atividades.prazo>='" + Formatacao.ConvercaoDataSql(new Date()) + 
                "' and a.atividades.prazo<='" + Formatacao.ConvercaoDataSql(data) + "'  and a.situacao=" + isCheckConcluidas() + 
                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario()  +
                " and a.participacao='Executor'" +
                " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        listaAtividadeSemana = atividadesAtividadeFacade.lista(sql);
        if (listaAtividadeSemana==null){
            listaAtividadeSemana = new ArrayList<Atividadeusuario>();
        }
        if (listaAtividadeSemana.size()<10){
            nsemana= "Semana (0" + String.valueOf(listaAtividadeSemana.size()) + ")";
        }else nsemana = "Semana (" + String.valueOf(listaAtividadeSemana.size()) + ")";
    }
    
    
    public  void listarAtividadesAtrasadas()  {
        AtividadeUsuarioFacade atividadesAtividadeFacade = new AtividadeUsuarioFacade();
        String sql = "Select a from Atividadeusuario a where a.atividades.prazo<'" + Formatacao.ConvercaoDataSql(new Date()) + 
                 "' and a.situacao=FALSE  and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " and a.participacao='Executor'" +
                " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        listaAtividadeAtrasada = atividadesAtividadeFacade.lista(sql);
        if (listaAtividadeAtrasada==null){
            listaAtividadeAtrasada = new ArrayList<Atividadeusuario>();
        }
        if (listaAtividadeAtrasada.size()<10){
            natrasada = "Atrasadas (0" + String.valueOf(listaAtividadeAtrasada.size())+")";
        }else natrasada = "Atrasadas (" + String.valueOf(listaAtividadeAtrasada.size())+")";
    }
    
    public void listarAtividadesDepartamento()  {
        AtividadeUsuarioFacade atividadesAtividadeFacade = new AtividadeUsuarioFacade();
        String sql="";
        if (usuarioLogadoBean.getUsuario().getPerfil().getTarefasoutros()) {
            sql = "Select a from Atividadeusuario a where a.situacao=FALSE   and a.participacao='Executor' and a.tipo=0 order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        } else {
            sql = "Select a from Atividadeusuario a where a.situacao=FALSE and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() + 
                    " and a.participacao='Executor' and a.tipo=0" +
                    " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        }
        listaAtividadesDepartamento = atividadesAtividadeFacade.lista(sql);
        if (listaAtividadesDepartamento==null){
            listaAtividadesDepartamento = new ArrayList<Atividadeusuario>();
        }
        if (listaAtividadesDepartamento.size() < 10) {
            ndepartamento = "Atividades (0" + String.valueOf(listaAtividadesDepartamento.size()) + ")";
        } else {
            ndepartamento = "Atividades (" + String.valueOf(listaAtividadesDepartamento.size()) + ")";
        }

    }
    
    public String mostarAtividadesDia(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadedia;
        atividadeMenu="dia";
        titulo="Taferas de Hoje";
        return "inicial";
    }
    
    public String mostarAtividadesSemana(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadeSemana;
        atividadeMenu="semana";
        titulo="Taferas da Semana";
        return "inicial";
    }
    
    public String mostarAtividadesAtrasadas(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadeAtrasada;
        atividadeMenu="atrasada";
         titulo="Taferas Atrasadas";
        return "tarefasAtrasadas";
    }
    
    public String mostarTodasAtividades(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaTodasAtividade;
        atividadeMenu="todas";
        calendarioMB.gerarEventos();
         titulo="Todas as Taferas";
        //return "tarefasTodas";
         return "teste";
    }
    
    public String mostarAtividadesDepartamento(){
        menuMB.gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefasoutros()){
            listaAtividadesGeral = listaAtividadesDepartamento;
            atividadeMenu="departamento";
            titulo="Taferas da Equipe";
            return "tarefaDepartamento";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    
    public void carregarListaGeral(){
        listaAtividadesGeral = new ArrayList<Atividadeusuario>();
        if (atividadeMenu.equalsIgnoreCase("dia")){
            listaAtividadesGeral = listaAtividadedia;
        }else if (atividadeMenu.equalsIgnoreCase("semana")){
            listaAtividadesGeral = listaAtividadeSemana;
        }else if (atividadeMenu.equalsIgnoreCase("atrasada")){
            listaAtividadesGeral = listaAtividadeAtrasada;
        }else if (atividadeMenu.equalsIgnoreCase("amanha")) {
                listarAtividadesAmanha();
            }else if (atividadeMenu.equalsIgnoreCase("dois")) {
                listarAtividadesDois();
            }else if (atividadeMenu.equalsIgnoreCase("tres")) {
                listarAtividadesTres();
            }else if (atividadeMenu.equalsIgnoreCase("quatro")) {
                listarAtividadesQuatro();
            }else if (atividadeMenu.equalsIgnoreCase("cinco")) {
                listarAtividadesCinco();
            }else if (atividadeMenu.equalsIgnoreCase("seis")) {
                listarAtividadesSeis();
            }else if (atividadeMenu.equalsIgnoreCase("sete")) {
                listarAtividadesSete();
            }else  if (atividadeMenu.equalsIgnoreCase("Todas")){
                listaAtividadesGeral = listaTodasAtividade;
            }else listaAtividadesGeral = listaAtividadesDepartamento;
    }
    
    public String imagem(Atividades atividade) {
        if (atividade.getPrioridade().equalsIgnoreCase("urgente")) {
            return "/resources/img/bandeira-vermelho.png";
        } else if (atividade.getPrioridade().equalsIgnoreCase("alta")) {
            return "/resources/img/bandeira_amarela.png";
        } else {
            return "/resources/img/bandeira_verde.png";
        }
    }
    
   
    
    public void salvarAtividadeConcluida(String linha) {
            int iLinha = Integer.parseInt(linha);
            Atividadeusuario atividadeusuario = listaAtividadesGeral.get(iLinha);
            atividades = listaAtividadesGeral.get(iLinha).getAtividades();
            AtividadeUsuarioFacade atividadeUsuarioFacade = new AtividadeUsuarioFacade();
            if (atividades.getEstado().equalsIgnoreCase("Pause")){
                Long termino = new Date().getTime();
                BigInteger valorInicio = atividades.getInicio();
                Long inicio = valorInicio.longValue();
                Long resultado = termino - inicio;
                resultado = resultado/1000;
                resultado = resultado/60;
                int tempo = resultado.intValue();
                tempo = tempo + atividades.getTempo();
                atividades.setTempo(tempo);
                atividades.setEstado("Pause");
        }
        if (usuarioLogadoBean.getUsuario().getIdusuario() == atividadeusuario.getUsuario().getIdusuario()) {
            AtividadeFacade atividadeFacade = new AtividadeFacade();
            atividadeusuario.setDataconclusao(new Date());
            atividadeusuario.setSituacao(true);
            atividadeUsuarioFacade.salvar(atividadeusuario);
            atividades = atividadeFacade.salvar(atividades);
            NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
            for (int i=0;i<atividades.getAtividadeusuarioList().size();i++){
                if (!atividades.getAtividadeusuarioList().get(i).getParticipacao().equalsIgnoreCase("Executor")){
                    Notificacao notificacao = new Notificacao();
                    notificacao.setLido(false);
                    String texto = usuarioLogadoBean.getUsuario().getNome() + " concluiu " +
                            atividades.getNome();
                    if (texto.length()>100){
                        texto = texto.substring(0,100);
                    }
                    notificacao.setTexto(texto);
                    notificacaoFacade.salvar(notificacao);
                }
            }
            if (atividades.getTipo().equalsIgnoreCase("R")){
                gerarProximasAtividades();
            }
            if (atividadeMenu.equalsIgnoreCase("dia")) {
                listarAtividadesDia();
            } else if (atividadeMenu.equalsIgnoreCase("semana")) {
                listarAtividadesSemana();
            } else if (atividadeMenu.equalsIgnoreCase("atrasada")) {
                listarAtividadesAtrasadas();
            } else if (atividadeMenu.equalsIgnoreCase("amanha")) {
                listarAtividadesAmanha();
            }else if (atividadeMenu.equalsIgnoreCase("todas")){
                listarTodasAtividades();
            }else {
                listarAtividadesDepartamento();
            }
            listarAtividadesAmanha();
            listarAtividadesAtrasadas();
            listarAtividadesCinco();
            listarAtividadesDepartamento();
            listarAtividadesDia();
            listarAtividadesDois();
            listarAtividadesQuatro();
            listarAtividadesSeis();
            listarAtividadesSemana();
            listarAtividadesSete();
            listarAtividadesTres();
            listarTodasAtividades();
            carregarListaGeral();
        }else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Atividade de outro usuário", ""));
            if (listaAtividadesGeral.get(iLinha).getAtividades().isSelecionado()){
                listaAtividadesGeral.get(iLinha).getAtividades().setSelecionado(false);
            }else listaAtividadesGeral.get(iLinha).getAtividades().setSelecionado(true);
        }
    }
    
    public String filtarConcluidas(){
        listarAtividadesDia();
        listarAtividadesSemana();
        carregarListaGeral();
        listarAtividadesAmanha();
        listarAtividadesDois();
        listarAtividadesTres();
        listarAtividadesQuatro();
        listarAtividadesCinco();
        listarAtividadesSeis();
        listarAtividadesSete();
        return "inicial";
    }
    
    public void listarAtividadesAmanha(){
        AtividadeUsuarioFacade atividadesAtividadeFacade = new AtividadeUsuarioFacade();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 1);
        String sql = "Select a from Atividadeusuario a where a.atividades.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " and a.participacao='Executor'" +
                " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        listaAtividadesAmanha = atividadesAtividadeFacade.lista(sql);
        if(listaAtividadesAmanha==null){
            listaAtividadesAmanha = new ArrayList<Atividadeusuario>();
        }
        if (listaAtividadesAmanha.size()<10){
            namanha= Formatacao.diaSemanaEscrito(data) + " (0" + String.valueOf(listaAtividadesAmanha.size()) + ")";
        }else namanha = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesAmanha.size()) + ")";
    }
    
    public String mostarAtividadesAmanha(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesAmanha;
        atividadeMenu="amanha";
        titulo="Tarefas de Amanhã";
        return "inicial";
    }
    
    public void listarAtividadesDois(){
        AtividadeUsuarioFacade atividadesAtividadeFacade = new AtividadeUsuarioFacade();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 2);
        String sql = "Select a from Atividadeusuario a where a.atividades.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " and a.participacao='Executor'" +
                " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        listaAtividadesDois = atividadesAtividadeFacade.lista(sql);
        if(listaAtividadesDois==null){
            listaAtividadesDois = new ArrayList<Atividadeusuario>();
        }
        if (listaAtividadesDois.size()<10){
            dois= Formatacao.diaSemanaEscrito(data) + "  (0" + String.valueOf(listaAtividadesDois.size()) + ")";
        }else dois = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesDois.size()) + ")";
    }
    
    public String mostarAtividadesDois(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesDois;
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 2);
        atividadeMenu="dois";
        titulo="Tarefas de " + Formatacao.diaSemanaEscrito(data);
        return "inicial";
    }
    
    public void listarAtividadesTres(){
        AtividadeUsuarioFacade atividadesAtividadeFacade = new AtividadeUsuarioFacade();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 3);
        String sql = "Select a from Atividadeusuario a where a.atividades.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " and a.participacao='Executor'" +
                " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        listaAtividadesTres = atividadesAtividadeFacade.lista(sql);
        if(listaAtividadesTres==null){
            listaAtividadesTres = new ArrayList<Atividadeusuario>();
        }
        if (listaAtividadesTres.size()<10){
            tres= Formatacao.diaSemanaEscrito(data) + "  (0" + String.valueOf(listaAtividadesTres.size()) + ")";
        }else tres = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesTres.size()) + ")";
    }
    
    public String mostarAtividadesTres(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesTres;
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 3);
        atividadeMenu="tres";
        titulo="Tarefas de " + Formatacao.diaSemanaEscrito(data);
        return "inicial";
    }
    
    public void listarAtividadesQuatro(){
        AtividadeUsuarioFacade atividadesAtividadeFacade = new AtividadeUsuarioFacade();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 4);
        String sql = "Select a from Atividadeusuario a where a.atividades.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " and a.participacao='Executor'" +
                " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        listaAtividadesQuatro = atividadesAtividadeFacade.lista(sql);
        if(listaAtividadesQuatro==null){
            listaAtividadesQuatro = new ArrayList<Atividadeusuario>();
        }
        if (listaAtividadesQuatro.size()<10){
            quatro= Formatacao.diaSemanaEscrito(data) + "  (0" + String.valueOf(listaAtividadesQuatro.size()) + ")";
        }else quatro = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesQuatro.size()) + ")";
    }
    
    public String mostarAtividadesQuatro(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesQuatro;
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 4);
        atividadeMenu="quatro";
        titulo="Tarefas de " + Formatacao.diaSemanaEscrito(data);
        return "inicial";
    }
    
    public void listarAtividadesCinco(){
        AtividadeUsuarioFacade atividadesAtividadeFacade = new AtividadeUsuarioFacade();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 5);
        String sql = "Select a from Atividadeusuario a where a.atividades.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " and a.participacao='Executor'" +
                " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        listaAtividadesCinco = atividadesAtividadeFacade.lista(sql);
        if(listaAtividadesCinco==null){
            listaAtividadesCinco = new ArrayList<Atividadeusuario>();
        }
        if (listaAtividadesCinco.size()<10){
            cinco= Formatacao.diaSemanaEscrito(data) + "  (0" + String.valueOf(listaAtividadesCinco.size()) + ")";
        }else cinco = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesCinco.size()) + ")";
    }
    
    public String mostarAtividadesCinco(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesCinco;
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 5);
        atividadeMenu="cinco";
        titulo="Tarefas de " + Formatacao.diaSemanaEscrito(data);
        return "inicial";
    }
    
    public void listarAtividadesSeis(){
        AtividadeUsuarioFacade atividadesAtividadeFacade = new AtividadeUsuarioFacade();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 6);
        String sql = "Select a from Atividadeusuario a where a.atividades.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " and a.participacao='Executor'" +
                " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        listaAtividadesSeis = atividadesAtividadeFacade.lista(sql);
        if(listaAtividadesSeis==null){
            listaAtividadesSeis = new ArrayList<Atividadeusuario>();
        }
        if (listaAtividadesSeis.size()<10){
            seis= Formatacao.diaSemanaEscrito(data) + "  (0" + String.valueOf(listaAtividadesSeis.size()) + ")";
        }else seis = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesSeis.size()) + ")";
    }
    
    public String mostarAtividadesSeis(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesSeis;
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 6);
        atividadeMenu="seis";
        titulo="Tarefas de " + Formatacao.diaSemanaEscrito(data);
        return "inicial";
    }
    
     public void listarAtividadesSete(){
        AtividadeUsuarioFacade atividadesAtividadeFacade = new AtividadeUsuarioFacade();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 7);
        String sql = "Select a from Atividadeusuario a where a.atividades.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " and a.participacao='Executor'" +
                " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        listaAtividadesSete = atividadesAtividadeFacade.lista(sql);
        if(listaAtividadesSete==null){
            listaAtividadesSete = new ArrayList<Atividadeusuario>();
        }
        if (listaAtividadesSete.size()<10){
            sete= Formatacao.diaSemanaEscrito(data) + "  (0" + String.valueOf(listaAtividadesSete.size()) + ")";
        }else sete = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesSete.size()) + ")";
    }
    
    public String mostarAtividadesSete(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesSete;
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 7);
        atividadeMenu="sete";
        titulo="Tarefas de " + Formatacao.diaSemanaEscrito(data);
        return "inicial";
    }
    
    public String verComentarios(String linha) {
       this.linha = linha;
       listaComentarios =  listaAtividadesGeral.get(Integer.parseInt(linha)).getAtividades().getComentariosList();
       if (listaComentarios==null){
           listaComentarios = new ArrayList<Comentarios>();
       }
       return null;
    }
    
    public String salvarComentario() throws SQLException{
        int nLinha = Integer.parseInt(linha);
        ComentariosFacade comentariosFacade = new ComentariosFacade();
        comentarios.setUsuario(getUsuarioLogadoBean().getUsuario());
        comentarios.setAtividades(listaAtividadesGeral.get(nLinha).getAtividades());
        comentarios.setData(new Date());
        comentarios.setHora(Formatacao.foramtarHoraString());
        comentarios = comentariosFacade.salvar(comentarios);
        listaComentarios.add(comentarios);
        comentarios = new Comentarios();
        listaAtividadesGeral.get(nLinha).getAtividades().getComentariosList();
         if (atividadeMenu.equalsIgnoreCase("dia")){
            listaAtividadedia.get(nLinha).getAtividades().setComentariosList(listaComentarios);
        }else if (atividadeMenu.equalsIgnoreCase("semana")){
            listaAtividadeSemana.get(nLinha).getAtividades().setComentariosList(listaComentarios);
        }else if (atividadeMenu.equalsIgnoreCase("atrasada")){
            listaAtividadeAtrasada.get(nLinha).getAtividades().setComentariosList(listaComentarios);
        }else {
            listaAtividadesDepartamento.get(nLinha).getAtividades().setComentariosList(listaComentarios);
        }
         linha="0";
         atividades = new Atividades();
        carregarListaGeral();
        return null;
    }
    
    public void pegarLinha(String linha){
        this.linha = linha;
    }
    
    public String editar(){
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefaseditar()){
            int nLinha= Integer.parseInt(linha);
            idExecutor = usuarioLogadoBean.getUsuario().getIdusuario();
            AtividadeFacade atividadeFacade = new AtividadeFacade();
            atividades = atividadeFacade.consultar(listaAtividadesGeral.get(nLinha).getAtividades().getIdatividades());
            idCliente = String.valueOf(atividades.getCliente().getIdcliente());
            idDepartamento = String.valueOf(listaAtividadesGeral.get(nLinha).getAtividades().getSubdepartamento().getDepartamento().getIddepartamento());
            gerarListaSubdepartamento();
            idSubdepartamento = String.valueOf(listaAtividadesGeral.get(nLinha).getAtividades().getSubdepartamento().getIdsubdepartamento());
            gerarListaUsuarioBeanEditar(atividades.getIdatividades());
            idUsuario =String.valueOf(idExecutor);
            RequestContext.getCurrentInstance().openDialog("editarTarefa");
        }else{
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public String pesquisarNome(){
        listarTodasAtividades();
        return "tarefasTodas";
    
    } 
   
    
    public void iniciarAtividade(String linha){
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefatempo()){
            this.linha = linha;
            int nlinha = Integer.parseInt(linha);
            AtividadeFacade atividadeFacade = new AtividadeFacade();
            if (listaAtividadesGeral.get(nlinha).getAtividades().getEstado().equalsIgnoreCase("Play")){
                //Play
                Long inicio = new Date().getTime();
                listaAtividadesGeral.get(nlinha).getAtividades().setInicio(BigInteger.valueOf(inicio));
                listaAtividadesGeral.get(nlinha).getAtividades().setEstado("Pause");
                atividadeFacade.salvar(listaAtividadesGeral.get(nlinha).getAtividades());
            }else {
                //Pause
                Long termino = new Date().getTime();
                BigInteger valorInicio = listaAtividadesGeral.get(nlinha).getAtividades().getInicio();
                Long inicio = valorInicio.longValue();
                Long resultado = termino - inicio;
                resultado = resultado/1000;
                resultado = resultado/60;
                int tempo = resultado.intValue();
                int tempoAtual = listaAtividadesGeral.get(nlinha).getAtividades().getTempo();
                tempo = tempo + tempoAtual;
                listaAtividadesGeral.get(nlinha).getAtividades().setTempo(tempo);
                int hora = tempo/60;
                int minutos = tempo - hora;
                String sHora;
                if (hora>9){
                    sHora = String.valueOf(hora) + ":";
                }else sHora = "0" + String.valueOf(hora) + ":";
                if (minutos>9){
                    sHora = sHora + String.valueOf(minutos);
                }else sHora = sHora + "0" + String.valueOf(minutos);
                listaAtividadesGeral.get(nlinha).getAtividades().setMostratempo(sHora);
                listaAtividadesGeral.get(nlinha).getAtividades().setEstado("Play");
                atividadeFacade.salvar(listaAtividadesGeral.get(nlinha).getAtividades());
                }
        }else{
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }
    
    public String filtrarTarefasDepartamento(){
        String sql = "Select a From Atividadeusuario a where a.situacao=" + checkConcluidas +
                " and a.participacao='Executor'";
        if (visualizar.equalsIgnoreCase("proxsete")){
            Date data7 = Formatacao.SomarDiasData(new Date(), 7);
            sql = sql + " and a.atividades.prazo>='" + Formatacao.ConvercaoDataSql(new Date()) + "' and a.atividades.prazo<='" 
                    + Formatacao.ConvercaoDataSql(data7) + "' ";
        }
        if (visualizar.equalsIgnoreCase("hoje")){
            sql = sql + " and a.atividades.prazo='" + Formatacao.ConvercaoDataSql(new Date()) + "' ";
        }
        if (visualizar.equalsIgnoreCase("atrasadas")){
            sql = sql + " and a.atividades.prazo<'" + Formatacao.ConvercaoDataSql(new Date()) + "' ";
        }
        if (!idDepartamento.equalsIgnoreCase("0")){
            sql = sql + " and a.atividades.subdepartamento.departamento.iddepartamento=" + Integer.parseInt(idDepartamento);
        }
        if (!idSubdepartamento.equalsIgnoreCase("0")){
            sql = sql + " and a.atividades.subdepartamento.idsubdepartamento=" + Integer.parseInt(idSubdepartamento);
        }
        if (!idCliente.equalsIgnoreCase("0")){
            sql = sql + " and a.atividades.cliente.idcliente=" + Integer.parseInt(idCliente);
        }
        if (usuarioLogadoBean.getUsuario().getPerfil().getTarefaeditaroutros()) {
            if (!idUsuario.equalsIgnoreCase("0")) {
                sql = sql + " and a.usuario.idusuario=" + Integer.parseInt(idUsuario);
            }
        } else {
            sql = sql + " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario();
        }
        sql = sql + " order by a.atividades.prazo, a.atividades.prioridade, a.atividades.nome";
        AtividadeUsuarioFacade atividadesAtividadeFacade = new AtividadeUsuarioFacade();
        listaAtividadesDepartamento = atividadesAtividadeFacade.lista(sql);
        listaAtividadesGeral = listaAtividadesDepartamento;
        if (listaAtividadesDepartamento.size() < 10) {
            ndepartamento = "Atividades (0" + String.valueOf(listaAtividadesDepartamento.size()) + ")";
        } else {
            ndepartamento = "Atividades (" + String.valueOf(listaAtividadesDepartamento.size()) + ")";
        }
        return "inicial";
    }
    public String carregarIcon(Atividades atividade){
        if (atividade.getEstado().equalsIgnoreCase("Play")) {
            return "ui-icon-play";
        }  else {
            return "ui-icon-pause";
        }
    }
    public String atrasadas(Atividades atividade) {
        if (atividade.getPrazo() != null) {
            Date data = new Date();
            String sData = Formatacao.ConvercaoDataPadrao(data);
            data = Formatacao.ConvercaoStringDataBrasil(sData);
            boolean bdata = atividade.getPrazo().before(data);
            if (bdata) {
                return "atrasado";
            } else {
                return "normal";
            }
        }
        return "normal";
    }
    
    public String mostrarTempo(Atividades atifivade){
        String texto = "Tempo " + atifivade.getMostratempo();
        return texto;
    }
    
    public String quantidadeComentario(Atividades atividade){
        String quantidade = "(" + atividade.getComentariosList().size() + ")";
        return quantidade;
    }
    
    public void gerarProximasAtividades() {
        String peridicidade = "nada";
        Rotinacliente rotinaCliente = null;
        RotinaclienteFacade rotinaclienteFacade = new RotinaclienteFacade();
        atividades.getRotinaatividadeList();
        if (atividades.getRotinaatividadeList() != null) {
            if (atividades.getRotinaatividadeList().size()==0){
                RotinaAtividadeFacade rotinaAtividadeFacade = new RotinaAtividadeFacade();
                String sql ="Select r from Rotinaatividade r where r.atividades.idatividades=" + atividades.getIdatividades();
                List<Rotinaatividade> lista = rotinaAtividadeFacade.listar(sql);
                atividades.setRotinaatividadeList(lista);
            }
            if (atividades.getRotinaatividadeList().size() > 0) {
                rotinaCliente = rotinaclienteFacade.getRotinaCliente(atividades.getCliente().getIdcliente(), atividades.getRotinaatividadeList().get(0).getRotina().getIdrotina());
                peridicidade = rotinaCliente.getPeriodicidade();
            }
            if (peridicidade.equalsIgnoreCase("diaria")) {
                criarAtividadesDiaria(rotinaCliente);
            } else if (peridicidade.equalsIgnoreCase("semanal")) {
                criarAtividadesSemanal(rotinaCliente);
            } else {
                criarAtividadeMensalTrimestralAnual(peridicidade, rotinaCliente);
            }
        }
    }

    
    public void criarAtividadeMensalTrimestralAnual(String peridicidade, Rotinacliente rotinaCliente) {
        if (!peridicidade.equalsIgnoreCase("nada")) {
            AtividadeFacade atividadeFacade = new AtividadeFacade();
            Atividades atividades = new Atividades();
            atividades.setCliente(this.atividades.getCliente());
            atividades.setNome(this.atividades.getNome());
            atividades.setPrioridade(this.atividades.getPrioridade());
            atividades.setEstado("Play");
            atividades.setInicio(BigInteger.valueOf(0));
            atividades.setTempo(0);
            atividades.setMostratempo("00:00");
            atividades.setTipo("R");
            atividades.setSubdepartamento(this.atividades.getSubdepartamento());
            RotinaAtividadeFacade rotinaAtividadeFacade = new RotinaAtividadeFacade();
            Calendar c = Calendar.getInstance();
            c.setTime(rotinaCliente.getData());    
            if (peridicidade.equalsIgnoreCase("trimestral")){
                c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 3);
            }else if (peridicidade.equalsIgnoreCase("mensal")){
                c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
            }else if (peridicidade.equalsIgnoreCase("anual")){
                c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
            }
            Date data = c.getTime();
            int diaSemana = Formatacao.diaSemana(c.getTime());
            if (diaSemana == 1) {
                data = Formatacao.SomarDiasData(data, 1);
            } else if (diaSemana == 7) {
                data = Formatacao.SomarDiasData(data, 2);
            }
            atividades.setPrazo(data);
            atividades = atividadeFacade.salvar(atividades);
            Atividadeusuario atividadeusuario = new Atividadeusuario();
            atividadeusuario.setAtividades(atividades);
            atividadeusuario.setParticipacao("Executor");
            atividadeusuario.setSituacao(false);
            atividadeusuario.setUsuario(rotinaCliente.getUsuario());
            AtividadeUsuarioFacade atiUsuarioFacade = new AtividadeUsuarioFacade();
            atiUsuarioFacade.salvar(atividadeusuario);
            RotinaclienteFacade rotinaclienteFacade = new RotinaclienteFacade();
            rotinaCliente.setData(c.getTime());
            rotinaclienteFacade.salvar(rotinaCliente);
            Rotinaatividade rotinaatividade = new Rotinaatividade();
            rotinaatividade.setAtividades(atividades);
            rotinaatividade.setRotina(rotinaCliente.getRotina());
            rotinaAtividadeFacade.salvar(rotinaatividade);
        }
    }
    
    public void criarAtividadesDiaria(Rotinacliente rotinaCliente) {
        AtividadeFacade atividadeFacade = new AtividadeFacade();
        Date data = rotinaCliente.getData();
        Atividades atividades = new Atividades();
        atividades.setCliente(this.atividades.getCliente());
        atividades.setNome(this.atividades.getNome());
        atividades.setPrioridade(this.atividades.getPrioridade());
        atividades.setTipo("R");
        atividades.setEstado("Play");
        atividades.setInicio(BigInteger.valueOf(0));
        atividades.setTempo(0);
        atividades.setMostratempo("00:00");
        atividades.setSubdepartamento(this.atividades.getSubdepartamento());
        data = Formatacao.SomarDiasData(data, 1);
        int diaSemana = Formatacao.diaSemana(data);
        if (diaSemana == 1) {
            data = Formatacao.SomarDiasData(data, 1);
        } else if (diaSemana == 7) {
            data = Formatacao.SomarDiasData(data, 2);
        }
        atividades.setPrazo(data);
        atividades = atividadeFacade.salvar(atividades);
        Atividadeusuario atividadeusuario = new Atividadeusuario();
        atividadeusuario.setAtividades(atividades);
        atividadeusuario.setParticipacao("Executor");
        atividadeusuario.setSituacao(false);
        atividadeusuario.setUsuario(rotinaCliente.getUsuario());
        AtividadeUsuarioFacade atiUsuarioFacade = new AtividadeUsuarioFacade();
        atiUsuarioFacade.salvar(atividadeusuario);
        RotinaclienteFacade rotinaclienteFacade = new RotinaclienteFacade();
        rotinaCliente.setData(data);
        Rotinaatividade rotinaatividade = new Rotinaatividade();
        rotinaatividade.setAtividades(atividades);
        rotinaatividade.setRotina(rotinaCliente.getRotina());
        RotinaAtividadeFacade rotinaAtividadeFacade = new RotinaAtividadeFacade();
        rotinaAtividadeFacade.salvar(rotinaatividade);
        rotinaclienteFacade.salvar(rotinaCliente);
    }
    
    public void criarAtividadesSemanal(Rotinacliente rotinaCliente) {
        AtividadeFacade atividadeFacade = new AtividadeFacade();
        Date data = Formatacao.SomarDiasData(rotinaCliente.getData(), 7);
        int diaSemana = Formatacao.diaSemana(data);
        if (diaSemana == 1) {
            data = Formatacao.SomarDiasData(data, 1);
        } else if (diaSemana == 7) {
            data = Formatacao.SomarDiasData(data, 2);
        }
        Atividades atividades = new Atividades();
        atividades.setCliente(this.atividades.getCliente());
        atividades.setNome(this.atividades.getNome());
        atividades.setPrioridade(this.atividades.getPrioridade());
        atividades.setTipo("R");
        atividades.setEstado("Play");
        atividades.setInicio(BigInteger.valueOf(0));
        atividades.setTempo(0);
        atividades.setMostratempo("00:00");
        atividades.setSubdepartamento(this.atividades.getSubdepartamento());
        atividades.setPrazo(data);
        atividades = atividadeFacade.salvar(atividades);
        Atividadeusuario atividadeusuario = new Atividadeusuario();
        atividadeusuario.setAtividades(atividades);
        atividadeusuario.setParticipacao("Executor");
        atividadeusuario.setSituacao(false);
        atividadeusuario.setUsuario(rotinaCliente.getUsuario());
        AtividadeUsuarioFacade atiUsuarioFacade = new AtividadeUsuarioFacade();
        atiUsuarioFacade.salvar(atividadeusuario);
        RotinaclienteFacade rotinaclienteFacade = new RotinaclienteFacade();
        rotinaCliente.setData(data);
        rotinaclienteFacade.salvar(rotinaCliente);
        Rotinaatividade rotinaatividade = new Rotinaatividade();
        rotinaatividade.setAtividades(atividades);
        rotinaatividade.setRotina(rotinaCliente.getRotina());
        RotinaAtividadeFacade rotinaAtividadeFacade = new RotinaAtividadeFacade();
        rotinaAtividadeFacade.salvar(rotinaatividade);
    }
    
    public void salvarUsuarioAtividadeParticular() {
        AtividadeUsuarioFacade atiUsuarioFacade = new AtividadeUsuarioFacade();
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        Usuario usuario = usuarioFacade.consultar(Integer.parseInt(idUsuario));
        Atividadeusuario atividadeusuario = null;
        NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
        String texto="";
        atividadeusuario = atiUsuarioFacade.consultar(usuario.getIdusuario(), atividades.getIdatividades());
        if (atividadeusuario == null) {
            atividadeusuario = new Atividadeusuario();
            atividadeusuario.setAtividades(atividades);
            atividadeusuario.setSituacao(false);
            atividadeusuario.setNomeexecutor(usuario.getNome());
            atividadeusuario.setParticipacao("Executor");
            atividadeusuario.setUsuario(usuario);
            atividadeusuario.setTipo(tipo);
            atividadeusuario = atiUsuarioFacade.salvar(atividadeusuario);
            Notificacao notificacao = new Notificacao();
            notificacao.setLido(false);
            notificacao.setUsuario(atividadeusuario.getUsuario());
            texto = usuarioLogadoBean.getUsuario().getNome() + " Criou uma nova tarefa.";
            notificacao.setTexto(texto);
            notificacaoFacade.salvar(notificacao);
        }
    }
    
    public void salvarUsuarioAtividade() {
        AtividadeUsuarioFacade atiUsuarioFacade = new AtividadeUsuarioFacade();
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        Usuario usuario = usuarioFacade.consultar(Integer.parseInt(idUsuario));
        Atividadeusuario atividadeusuario = null;
        NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
        String texto="";
        atividadeusuario = atiUsuarioFacade.consultar(usuario.getIdusuario(), atividades.getIdatividades());
        if (atividadeusuario == null) {
            atividadeusuario = new Atividadeusuario();
            atividadeusuario.setAtividades(atividades);
            atividadeusuario.setSituacao(false);
            atividadeusuario.setNomeexecutor(usuario.getNome());
            atividadeusuario.setParticipacao("Executor");
            atividadeusuario.setUsuario(usuario);
            atividadeusuario.setTipo(tipo);
            atividadeusuario = atiUsuarioFacade.salvar(atividadeusuario);
            Notificacao notificacao = new Notificacao();
            notificacao.setLido(false);
            notificacao.setUsuario(atividadeusuario.getUsuario());
            texto = usuarioLogadoBean.getUsuario().getNome() + " Criou uma nova tarefa.";
            notificacao.setTexto(texto);
            notificacaoFacade.salvar(notificacao);
        }
        for (int i = 0; i < listaUsuarioBean.size(); i++) {
            Notificacao notificacao;
            atividadeusuario = atiUsuarioFacade.consultar(listaUsuarioBean.get(i).getUsuario().getIdusuario(), atividades.getIdatividades());
            if (listaUsuarioBean.get(i).isSelecionado()) {
                if (atividadeusuario == null) {
                    atividadeusuario = new Atividadeusuario();
                    atividadeusuario.setAtividades(atividades);
                    atividadeusuario.setSituacao(false);
                    atividadeusuario.setNomeexecutor(usuario.getNome());
                    atividadeusuario.setParticipacao("Informação");
                    atividadeusuario.setUsuario(listaUsuarioBean.get(i).getUsuario());
                    atividadeusuario = atiUsuarioFacade.salvar(atividadeusuario);
                    if (usuarioLogadoBean.getUsuario().getIdusuario() != atividadeusuario.getUsuario().getIdusuario()) {
                        notificacao = new Notificacao();
                        notificacao.setLido(false);
                        notificacao.setUsuario(atividadeusuario.getUsuario());
                        notificacao.setTexto(texto);
                        notificacaoFacade.salvar(notificacao);
                    }
                }
            }else {
                if (atividadeusuario!=null){
                    atiUsuarioFacade.excluir(atividadeusuario.getIdatividadeusuario());
                }
            }
        }
    }
    
    public void gerarListaUsuarioBean(){
        listaUsuarioBean = new ArrayList<UsuarioBean>();
        if (listaUsuario == null) {
            UsuarioFacade usuarioFacade = new UsuarioFacade();
            listaUsuario = usuarioFacade.listarAtivos();
        }
        int nid = Integer.parseInt(idUsuario);
        listaUsuarioBean = new ArrayList<UsuarioBean>();
        for (int i = 0; i < listaUsuario.size(); i++) {
            if (nid != listaUsuario.get(i).getIdusuario()) {
                UsuarioBean usuarioBean = new UsuarioBean();
                usuarioBean.setSelecionado(false);
                usuarioBean.setUsuario(listaUsuario.get(i));
                listaUsuarioBean.add(usuarioBean);
            }
        }
    }
    
    public void gerarListaUsuarioBeanEditar(int idAtividade){
        listaUsuarioBean = new ArrayList<UsuarioBean>();
        if (listaUsuario == null) {
            UsuarioFacade usuarioFacade = new UsuarioFacade();
            listaUsuario = usuarioFacade.listarAtivos();
        }
        AtividadeUsuarioFacade atividadeUsuarioFacade = new AtividadeUsuarioFacade();
        List<Atividadeusuario> lista = atividadeUsuarioFacade.lista("Select a From Atividadeusuario a where a.atividades.idatividades=" +idAtividade);
        if (lista!=null){
            for(int i=0;i<lista.size();i++){
                if (lista.get(i).getParticipacao().equalsIgnoreCase("Executor")){
                    idExecutor = lista.get(i).getUsuario().getIdusuario();
                    i=1000;
                }
            }
        }
        listaUsuarioBean = new ArrayList<UsuarioBean>();
        for (int i = 0; i < listaUsuario.size(); i++) {
            if (idExecutor != listaUsuario.get(i).getIdusuario()) {
                boolean s = false;
                for(int n=0;n<lista.size();n++){
                    if (lista.get(n).getUsuario().getIdusuario()==listaUsuario.get(i).getIdusuario()){
                        s=true;
                    }
                }
                UsuarioBean usuarioBean = new UsuarioBean();
                usuarioBean.setSelecionado(s);
                usuarioBean.setUsuario(listaUsuario.get(i));
                listaUsuarioBean.add(usuarioBean);
            }
        }
    }
    
    public String abrirDialog(){
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefaseditar()){
            return "PF('editar').show()";
        }else{
            return "";
        }
    }
    
    
    public String mensagemGrow(){
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefaseditar()){
            return "atividades:dialog_editTarefa";
        }else{
            return "atividades:growl";
        }
    }
}
