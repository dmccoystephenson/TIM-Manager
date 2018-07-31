package com.trihydro.odewrapper.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.trihydro.library.model.ActiveTim;
import com.trihydro.library.service.ActiveTimService;
import com.trihydro.odewrapper.model.ControllerResult;
import com.trihydro.odewrapper.model.WydotTim;
import com.trihydro.odewrapper.model.WydotTimList;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

@CrossOrigin
@RestController
@Api(description="Parking")
public class WydotTimParkingController extends WydotTimBaseController {
    
    @RequestMapping(value="/parking-tim", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<String> createParkingTim(@RequestBody WydotTimList wydotTimList) {        
        
        System.out.println("Create Parking TIM");

        List<ControllerResult> resultList = new ArrayList<ControllerResult>();
        ControllerResult resultTim = null;      

        // build TIM        
        for (WydotTim wydotTim : wydotTimList.getTimParkingList()) {
            if(wydotTim.getDirection().equals("both")) {

                wydotTim.setFromRm(wydotTim.getMileMarker() - 10);     
                wydotTim.setToRm(wydotTim.getMileMarker());    
                resultTim = wydotTimService.createUpdateTim("P", wydotTim, "eastbound");
                resultList.add(resultTim);

                wydotTim.setFromRm(wydotTim.getMileMarker());     
                wydotTim.setToRm(wydotTim.getMileMarker() + 10);   
                resultTim = wydotTimService.createUpdateTim("P", wydotTim, "westbound");      
                resultList.add(resultTim);
            }
            else{
                if(wydotTim.getDirection().equals("eastbound")){
                    wydotTim.setFromRm(wydotTim.getMileMarker() - 10);     
                    wydotTim.setToRm(wydotTim.getMileMarker());    
                }
                else{
                    wydotTim.setFromRm(wydotTim.getMileMarker());     
                    wydotTim.setToRm(wydotTim.getMileMarker() + 10);   
                }
                resultTim = wydotTimService.createUpdateTim("P", wydotTim, wydotTim.getDirection());      
                resultList.add(resultTim);
            }
        }

        String responseMessage = gson.toJson(resultList);         
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);       
    }

    @RequestMapping(value="/parking-tim", method = RequestMethod.GET, headers="Accept=application/json")
    public Collection<ActiveTim> getParkingTims() { 
       
        // clear TIM
        List<ActiveTim> activeTims = wydotTimService.selectTimsByType("P");        

        return activeTims;
    }

    @RequestMapping(value="/parking-tim/{clientId}", method = RequestMethod.GET, headers="Accept=application/json")
    public Collection<ActiveTim> getParkingTimById(@PathVariable String clientId) { 
       
        // clear TIM
        List<ActiveTim> activeTims = wydotTimService.selectTimByClientId("P", clientId);        

        return activeTims;
    }

    @RequestMapping(value="/parking-tim/itis-codes/{id}", method = RequestMethod.GET, headers="Accept=application/json")
    public Collection<ActiveTim> getParkingTimByIdWithItisCodes(@PathVariable String id) { 
               
        // get tims              
        List<ActiveTim> activeTims = wydotTimService.selectTimByClientId("P", id); 

        // add ITIS codes to TIMs
        for (ActiveTim activeTim : activeTims) {
            ActiveTimService.addItisCodesToActiveTim(activeTim);
        }

        return activeTims;
    }

    @RequestMapping(value="/parking-tim/{id}", method = RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<String> deleteRoadContructionTim(@PathVariable String id) { 

        // clear TIM
        wydotTimService.clearTimsById("P", id);
        
        String responseMessage = "success";
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}