/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woehlke.javaee7.petclinic.web;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.woehlke.javaee7.petclinic.dao.OwnerDao;
import org.woehlke.javaee7.petclinic.entities.Owner;
import org.woehlke.javaee7.petclinic.entities.Visit;
import org.woehlke.javaee7.petclinic.dao.VisitDao;

/**
 *
 * @author Jonata
 */
   

@ManagedBean
public class Markers implements Serializable {
    
    @EJB
    private OwnerDao ownerDao;
    
    @EJB 
    private VisitDao visitDao;
   
    private MapModel simpleModel;
  
    @PostConstruct
    public void init()  {
        simpleModel = new DefaultMapModel();
        LatLng aux;  
        List<Visit> visitList;
        visitList = visitDao.getAll();
        Date date = new Date();
        
             
        
        
        for (Visit visit : visitList) {
               //adicionar if
                Owner owner = visit.getPet().getOwner();
                double[]resultado = Conversor.stringGeocode(owner.getAddress());
                aux = new LatLng (resultado[0],resultado[1]);
                simpleModel.addOverlay(new Marker(aux,owner.getFirstName()));        
        }
        
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
}
