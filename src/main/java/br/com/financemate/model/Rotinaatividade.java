/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.model;

import java.io.Serializable;
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

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "rotinaatividade")
@NamedQueries({
    @NamedQuery(name = "Rotinaatividade.findAll", query = "SELECT r FROM Rotinaatividade r")})
public class Rotinaatividade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrotinaatividade")
    private Integer idrotinaatividade;
    @JoinColumn(name = "rotina_idrotina", referencedColumnName = "idrotina")
    @ManyToOne(optional = false)
    private Rotina rotina;
    @JoinColumn(name = "atividades_idatividades", referencedColumnName = "idatividades")
    @ManyToOne(optional = false)
    private Atividades atividades;

    public Rotinaatividade() {
    }

    public Rotinaatividade(Integer idrotinaatividade) {
        this.idrotinaatividade = idrotinaatividade;
    }

    public Integer getIdrotinaatividade() {
        return idrotinaatividade;
    }

    public void setIdrotinaatividade(Integer idrotinaatividade) {
        this.idrotinaatividade = idrotinaatividade;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }

    public Atividades getAtividades() {
        return atividades;
    }

    public void setAtividades(Atividades atividades) {
        this.atividades = atividades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrotinaatividade != null ? idrotinaatividade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotinaatividade)) {
            return false;
        }
        Rotinaatividade other = (Rotinaatividade) object;
        if ((this.idrotinaatividade == null && other.idrotinaatividade != null) || (this.idrotinaatividade != null && !this.idrotinaatividade.equals(other.idrotinaatividade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Rotinaatividade[ idrotinaatividade=" + idrotinaatividade + " ]";
    }
    
}
