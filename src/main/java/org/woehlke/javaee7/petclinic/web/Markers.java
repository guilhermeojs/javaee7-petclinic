/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woehlke.javaee7.petclinic.web;
import com.google.api.client.util.DateTime;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.OverlaySelectEvent;
 
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.woehlke.javaee7.petclinic.dao.OwnerDao;
import org.woehlke.javaee7.petclinic.entities.Owner;
import org.woehlke.javaee7.petclinic.entities.Visit;
import org.woehlke.javaee7.petclinic.dao.VisitDao;
import org.woehlke.javaee7.petclinic.dao.VisitDaoImpl;

/**
 *
 * @author Jonata
 */
   

@ViewScoped
@Named
public class Markers implements Serializable {
    
    @EJB
    private OwnerDao ownerDao;
    
    @EJB 
    private VisitDao visitDao;
   
    private MapModel simpleModel;
    
    private Marker marker;
    
    private List<Visit> visitList;
    
    
  
    @PostConstruct
    public void init()  {
        simpleModel = new DefaultMapModel();
        LatLng aux;  
       
        this.visitList = visitDao.getAll();
        Date hoje = new Date();
        
             
        
        
        for (Visit visit : visitList) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                
                
                if((df.format(visit.getDate()).equals(df.format(hoje)))){
                if (!visit.getDescription().contains("Busca concluída as ")) {
                Owner owner = visit.getPet().getOwner();
                String endereco = owner.getAddress()+", "+owner.getCity();
                double[]resultado = Conversor.stringGeocode(endereco);
                aux = new LatLng (resultado[0],resultado[1]);
                simpleModel.addOverlay(new Marker(aux,visit.getPet().getName()));
                    }
                
           }
        }
        
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
     public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        marker.setVisible(false);
        this.visitList = visitDao.getAll();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
          
        for (Visit visit : visitList) {
          if (visit.getPet().getName().equals(marker.getTitle())) {
                Date hora = Calendar.getInstance().getTime(); 
                String dataFormatada = df.format(hora);
                visit.setDescription("Busca concluída as "+dataFormatada);
                visitDao.update(visit);
                
            }
        }
        
        
        
 }
     
     
    public Marker getMarker() {
        return marker;
    }
}
