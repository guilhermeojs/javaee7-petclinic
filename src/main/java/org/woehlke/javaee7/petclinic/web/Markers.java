/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woehlke.javaee7.petclinic.web;
import java.io.Serializable;
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

/**
 *
 * @author Jonata
 */
   

@ManagedBean
public class Markers implements Serializable {
    
    @EJB
    private OwnerDao ownerDao;
   
    private MapModel simpleModel;
  
    @PostConstruct
    public void init()  {
        simpleModel = new DefaultMapModel();
        LatLng aux;  
        

        //Shared coordinates
        //LatLng coord1 = new LatLng(36.879466, 30.667648);
        //Basic marker
        //simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));
        
        List<Owner> ownerList = ownerDao.getAll();
        for (Owner owner:ownerList) {
            double[]resultado = Conversor.stringGeocode(owner.getAddress());
            aux = new LatLng (resultado[0],resultado[1]);
            simpleModel.addOverlay(new Marker(aux,owner.getFirstName()));
        }
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
}
