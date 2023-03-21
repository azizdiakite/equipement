/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 */
package org.itech.equipment.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.itech.equipment.model.Lab;
import org.itech.equipment.service.LabService;
import org.itech.equipment.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <h2>LabController</h2>
 */
@Controller
@RequestMapping("/lab")
public class LabController {

	@Autowired
	private LabService entityService;
	@Autowired
	private SiteService siteService;

	@PostMapping(value = "")
	public String createLab(@Valid Lab lab) {
		entityService.createOrUpdate(lab);
		return "redirect:/lab";
	}

	@GetMapping(value = "")
	public String getAllLab(Model model) {
		List<Map<String, Object>> sites = siteService.getSiteIdAndNames();
		List<Lab> Labs = entityService.getAll();
		model.addAttribute("labs", Labs);
		model.addAttribute("sites", sites);
		model.addAttribute("lab", new Lab());
		model.addAttribute("mode", 0); // add a new entry
		return "lab/index";
	}

	@GetMapping(value = "/{id}")
	public String getOneLab(@PathVariable("id") int id, Model model) {
		List<Lab> Labs = entityService.getAll();
		List<Map<String, Object>> sites = siteService.getSiteIdAndNames();
		Lab s = entityService.getOne(id);
		if (s == null) {
			s = new Lab();
		}
		model.addAttribute("sites", sites);
		model.addAttribute("Labs", Labs);
		model.addAttribute("mode", 1); // modify an entry
		model.addAttribute("lab", s);
		return "lab/index";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteLab(@PathVariable("id") int id) {
		entityService.delete(id);
		return "redirect:/lab";
	}

}
