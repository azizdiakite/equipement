/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 */
package org.itech.equipment.controller;

import org.itech.equipment.model.Partner;
import org.itech.equipment.service.PartnerService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;


/**
* <h2>PartnerController</h2>
*/
@Controller
@RequestMapping("/partner")
public class PartnerController {


	@Autowired
	private PartnerService entityService;

	@PostMapping(value = "/")
	public ResponseEntity<Partner> createPartner(@RequestBody @Valid Partner model) {

   		 Partner data = entityService.create(model);
    		if (data != null) {
    			return new ResponseEntity<>(data,HttpStatus.CREATED);
  			  } else {
    			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
   			 }
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Partner>> getAllPartner() {
        List<Partner> lst = entityService.getAll();

        return new ResponseEntity<>(lst,HttpStatus.OK);
    }

        @GetMapping(value = "/{id}")
    public ResponseEntity<Partner> getOnePartner(@PathVariable("id") int id) {

            Partner e = entityService.getOne(id);
            if (e == null) {
            	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(e, HttpStatus.OK);
    }



}
