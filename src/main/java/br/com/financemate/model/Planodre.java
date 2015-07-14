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
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "planodre")
@NamedQueries({
    @NamedQuery(name = "Planodre.findAll", query = "SELECT p FROM Planodre p")})
public class Planodre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplanodre")
    private Integer idplanodre;
    @Size(max = 200)
    @Column(name = "planoconta")
    private String planoconta;
    @Size(max = 200)
    @Column(name = "descrica")
    private String descrica;
    @Size(max = 200)
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Planodre() {
    }

    public Planodre(Integer idplanodre) {
        this.idplanodre = idplanodre;
    }

    public Integer getIdplanodre() {
        return idplanodre;
    }

    public void setIdplanodre(Integer idplanodre) {
        this.idplanodre = idplanodre;
    }

    public String getPlanoconta() {
        return planoconta;
    }

    public void setPlanoconta(String planoconta) {
        this.planoconta = planoconta;
    }

    public String getDescrica() {
        return descrica;
    }

    public void setDescrica(String descrica) {
        this.descrica = descrica;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanodre != null ? idplanodre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planodre)) {
            return false;
        }
        Planodre other = (Planodre) object;
        if ((this.idplanodre == null && other.idplanodre != null) || (this.idplanodre != null && !this.idplanodre.equals(other.idplanodre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Planodre[ idplanodre=" + idplanodre + " ]";
    }
    
}
