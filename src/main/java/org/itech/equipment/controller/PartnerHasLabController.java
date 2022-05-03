/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 */
package org.itech.equipment.controller;

import org.itech.equipment.model.PartnerHasLab;
import org.itech.equipment.service.PartnerHasLabService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
* <h2>PartnerHasLabController</h2>
*/
@RestController
@RequestMapping("/partnerhaslab")
public class PartnerHasLabController {


	@Autowired
	private PartnerHasLabService entityService;

	@PostMapping(value = "/")
	public ResponseEntity<PartnerHasLab> createPartnerHasLab(@RequestBody @Valid PartnerHasLab model) {

   		 PartnerHasLab data = entityService.create(model);
    		if (data != null) {
    			return new ResponseEntity<>(data,HttpStatus.CREATED);
  			  } else {
    			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
   			 }
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<PartnerHasLab>> getAllPartnerHasLab() {
        List<PartnerHasLab> lst = entityService.getAll();

        return new ResponseEntity<>(lst,HttpStatus.OK);
    }

        @GetMapping(value = "/{id}")
    public ResponseEntity<PartnerHasLab> getOnePartnerHasLab(@PathVariable("id") int id) {

            PartnerHasLab e = entityService.getOne(id);
            if (e == null) {
            	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(e, HttpStatus.OK);
    }



}
