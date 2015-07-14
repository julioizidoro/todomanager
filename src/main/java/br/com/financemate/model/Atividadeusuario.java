/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "atividadeusuario")
@NamedQueries({
    @NamedQuery(name = "Atividadeusuario.findAll", query = "SELECT a FROM Atividadeusuario a")})
public class Atividadeusuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idatividadeusuario")
    private Integer idatividadeusuario;
    @Column(name = "situacao")
    private Boolean situacao;
    @Column(name = "dataconclusao")
    @Temporal(TemporalType.DATE)
    private Date dataconclusao;
    @Size(max = 15)
    @Column(name = "participacao")
    private String participacao;
    @Size(max = 50)
    @Column(name = "nomeexecutor")
    private String nomeexecutor;
    @Column(name = "tipo")
    private int tipo;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "atividades_idatividades", referencedColumnName = "idatividades")
    @ManyToOne(optional = false)
    private Atividades atividades;
    
    
    

    public Atividadeusuario() {
    }

    public Atividadeusuario(Integer idatividadeusuario) {
        this.idatividadeusuario = idatividadeusuario;
    }

    public Integer getIdatividadeusuario() {
        return idatividadeusuario;
    }

    public void setIdatividadeusuario(Integer idatividadeusuario) {
        this.idatividadeusuario = idatividadeusuario;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public Date getDataconclusao() {
        return dataconclusao;
    }

    public void setDataconclusao(Date dataconclusao) {
        this.dataconclusao = dataconclusao;
    }

    public String getParticipacao() {
        return participacao;
    }

    public void setParticipacao(String participacao) {
        this.participacao = participacao;
    }

    public String getNomeexecutor() {
        return nomeexecutor;
    }

    public void setNomeexecutor(String nomeexecutor) {
        this.nomeexecutor = nomeexecutor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Atividades getAtividades() {
        return atividades;
    }

    public void setAtividades(Atividades atividades) {
        this.atividades = atividades;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idatividadeusuario != null ? idatividadeusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atividadeusuario)) {
            return false;
        }
        Atividadeusuario other = (Atividadeusuario) object;
        if ((this.idatividadeusuario == null && other.idatividadeusuario != null) || (this.idatividadeusuario != null && !this.idatividadeusuario.equals(other.idatividadeusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Atividadeusuario[ idatividadeusuario=" + idatividadeusuario + " ]";
    }
    
}
