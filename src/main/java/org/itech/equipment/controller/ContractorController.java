/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 */
package org.itech.equipment.controller;

import java.util.List;

import javax.validation.Valid;

import org.itech.equipment.model.Contractor;
import org.itech.equipment.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contractor")
public class ContractorController {

	@Autowired
	private ContractorService entityService;

	@PostMapping(value = "")
	public String createContractor(@Valid Contractor contractor) {
		try {
			entityService.createOrUpdate(contractor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/contractor";
	}

	@GetMapping(value = "")
	public String getAllContractor(Model model) {
		List<Contractor> contractors = entityService.getAll();
		model.addAttribute("contractors", contractors);
		model.addAttribute("contractor", new Contractor());
		model.addAttribute("mode", 0); // add a new entry
		return "contractor/index";
	}

	@GetMapping(value = "/{id}")
	public String getOneContractor(@PathVariable("id") Integer id, Model model) {
		List<Contractor> contractors = entityService.getAll();
		Contractor e = entityService.getOne(id);
		if (e == null) {
			e = new Contractor();
		}
		model.addAttribute("contractors", contractors);
		model.addAttribute("mode", 1); // modify an entry
		model.addAttribute("contractor", e);
		return "contractor/index";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteContractor(@PathVariable("id") Integer id) {
		entityService.delete(id);
		return "redirect:/contractor";
	}

}
