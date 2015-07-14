/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.manageBean;

import br.com.financemate.model.Atividadeusuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Wolverine
 */
@Named("CalendarioMB")
@SessionScoped
public class CalendarioMB implements Serializable{
    
    @Inject 
    private AtividadeMB atividadeMB;
    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private Date dataInicia;

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public Date getDataInicia() {
        return dataInicia;
    }

    public void setDataInicia(Date dataInicia) {
        this.dataInicia = dataInicia;
    }
    
    public void gerarEventos(){
        List<Atividadeusuario> listaAtividade = atividadeMB.getListaTodasAtividade();
        eventModel = new DefaultScheduleModel();
        dataInicia = listaAtividade.get(0).getAtividades().getPrazo();
        for(int i=0;i<listaAtividade.size();i++){
            DefaultScheduleEvent evento = new DefaultScheduleEvent();
            evento.setId(String.valueOf(i));
            evento.setTitle(listaAtividade.get(i).getAtividades().getNome());
            evento.setStartDate(listaAtividade.get(i).getAtividades().getPrazo());
            evento.setEndDate(listaAtividade.get(i).getAtividades().getPrazo());
            evento.setAllDay(true);
            eventModel.addEvent(evento);
        }
    }
    
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
    
     public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }
    
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    
}
