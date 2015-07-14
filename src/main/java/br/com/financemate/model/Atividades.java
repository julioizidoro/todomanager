/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "atividades")
@NamedQueries({
    @NamedQuery(name = "Atividades.findAll", query = "SELECT a FROM Atividades a")})
public class Atividades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idatividades")
    private Integer idatividades;
    @Size(max = 100)
    @Column(name = "nome")
    private String nome;
    @Column(name = "prazo")
    @Temporal(TemporalType.DATE)
    private Date prazo;
    @Size(max = 30)
    @Column(name = "prioridade")
    private String prioridade;
    @Size(max = 1)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "inicio")
    private BigInteger inicio;
    @Column(name = "tempo")
    private Integer tempo;
    @Size(max = 6)
    @Column(name = "estado")
    private String estado;
    @Size(max = 5)
    @Column(name = "mostratempo")
    private String mostratempo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividades", fetch = FetchType.LAZY)
    private List<Atividadeusuario> atividadeusuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividades", fetch = FetchType.LAZY)
    private List<Rotinaatividade> rotinaatividadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividades", fetch = FetchType.EAGER)
    private List<Comentarios> comentariosList;
    @JoinColumn(name = "subdepartamento_idsubdepartamento", referencedColumnName = "idsubdepartamento")
    @ManyToOne(optional = false)
    private Subdepartamento subdepartamento;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @Transient
    private boolean selecionado;


    public Atividades() {
    }

    public Atividades(Integer idatividades) {
        this.idatividades = idatividades;
    }

    public Integer getIdatividades() {
        return idatividades;
    }

    public void setIdatividades(Integer idatividades) {
        this.idatividades = idatividades;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigInteger getInicio() {
        return inicio;
    }

    public void setInicio(BigInteger inicio) {
        this.inicio = inicio;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMostratempo() {
        return mostratempo;
    }

    public void setMostratempo(String mostratempo) {
        this.mostratempo = mostratempo;
    }

    public List<Atividadeusuario> getAtividadeusuarioList() {
        return atividadeusuarioList;
    }

    public void setAtividadeusuarioList(List<Atividadeusuario> atividadeusuarioList) {
        this.atividadeusuarioList = atividadeusuarioList;
    }

    public List<Rotinaatividade> getRotinaatividadeList() {
        return rotinaatividadeList;
    }

    public void setRotinaatividadeList(List<Rotinaatividade> rotinaatividadeList) {
        this.rotinaatividadeList = rotinaatividadeList;
    }

    public List<Comentarios> getComentariosList() {
        return comentariosList;
    }

    public void setComentariosList(List<Comentarios> comentariosList) {
        this.comentariosList = comentariosList;
    }

    public Subdepartamento getSubdepartamento() {
        return subdepartamento;
    }

    public void setSubdepartamento(Subdepartamento subdepartamento) {
        this.subdepartamento = subdepartamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idatividades != null ? idatividades.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atividades)) {
            return false;
        }
        Atividades other = (Atividades) object;
        if ((this.idatividades == null && other.idatividades != null) || (this.idatividades != null && !this.idatividades.equals(other.idatividades))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Atividades[ idatividades=" + idatividades + " ]";
    }
    
}
