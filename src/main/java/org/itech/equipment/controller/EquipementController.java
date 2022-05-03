/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 */
package org.itech.equipment.controller;

import org.itech.equipment.model.Equipement;
import org.itech.equipment.service.EquipementService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/equipement")
public class EquipementController {

	@Autowired
	private EquipementService entityService;

	@PostMapping(value = "")
	public String createEquipement(@Valid Equipement equipement) {
		try {
			entityService.createOrUpdate(equipement);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/equipement";
	}

	@GetMapping(value = "")
	public String getAllEquipement(Model model) {
		List<Equipement> equipements = entityService.getAll();
		model.addAttribute("equipements", equipements);
		model.addAttribute("equipement", new Equipement());
		model.addAttribute("mode", 0); // add a new entry
		return "equipement/index";
	}

	@GetMapping(value = "/{id}")
	public String getOneEquipement(@PathVariable("id") int id, Model model) {
		List<Equipement> equipements = entityService.getAll();
		Equipement e = entityService.getOne(id);
		if (e == null) {
			e = new Equipement();
		}
		model.addAttribute("equipements", equipements);
		model.addAttribute("mode", 1); // modify an entry
		model.addAttribute("equipement", e);
		return "equipement/index";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteEquipement(@PathVariable("id") int id) {
		entityService.delete(id);
		return "redirect:/equipement";
	}

}
