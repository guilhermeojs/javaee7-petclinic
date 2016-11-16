/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woehlke.javaee7.petclinic.web;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.primefaces.model.map.MapModel;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
/**
 *
 * @author Jonata
 */
public class Conversor implements Serializable {
    
   
    public static double[] stringGeocode (String address) {
    // Replace the API key below with a valid API key.
    
    double[] resultado = new double[2];
    GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyAdjH-1lPqD6cmZeunEn0AOe309NTjuaaM");
    GeocodingResult[] results =  GeocodingApi.geocode(context,address).awaitIgnoreError();
    resultado[0] = results[0].geometry.location.lat;
    resultado[1] = results[0].geometry.location.lng;
    
    
    return resultado;
    }
    
    
 

}
