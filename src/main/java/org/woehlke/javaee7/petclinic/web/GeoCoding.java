/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woehlke.javaee7.petclinic.web;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

/**
 *
 * @author Jonata
 */
public class GeoCoding {
    
    public static void addressReturn () throws Exception {
    // Replace the API key below with a valid API key.
    GeoApiContext context = new GeoApiContext().setApiKey("YOUR_API_KEY");
    GeocodingResult[] results =  GeocodingApi.geocode(context,"1600 Amphitheatre Parkway Mountain View, CA 94043").await();
    System.out.println(results[0].formattedAddress);

    }
    
}
