/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 */
package org.itech.equipment.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.LabHasEquipement;
import org.itech.equipment.model.MaintenancePlanning;
import org.itech.equipment.model.Panne;
import org.itech.equipment.service.LabHasEquipementService;
import org.itech.equipment.service.LabService;
import org.itech.equipment.service.MaintenancePlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/maintenanceplanning")
public class MaintenancePlanningController {

	@Autowired
	private MaintenancePlanningService entityService;
	@Autowired
	private LabHasEquipementService labEquipementService;
	@Autowired
	private LabService labService;

	@PostMapping(value = "")
	public String createMaintenancePlanning(@Valid MaintenancePlanning planning, RedirectAttributes redirAttrs) {
		MaintenancePlanning mp = entityService.findPlanning(planning);
		try {
			
			if (ObjectUtils.isNotEmpty(planning.getId())) {
				entityService.update(planning);
				redirAttrs.addFlashAttribute("success", "Modification effectuée avec succès");
			}
			else if(ObjectUtils.isEmpty(mp) && ObjectUtils.isEmpty(planning.getId())) {
				entityService.create(planning);
				redirAttrs.addFlashAttribute("success", "Modification effectuée avec succès");
			}
			else if(ObjectUtils.isNotEmpty(mp) && ObjectUtils.isEmpty(planning.getId())) {
				redirAttrs.addFlashAttribute("error", "Maintenance déjà planifiée à cette date pour cet équipement");
			}
		} catch (Exception e) {
			redirAttrs.addFlashAttribute("error", e.getMessage());
		}

		return "redirect:/maintenanceplanning";
	}

	@GetMapping(value = "")
	public String getAllMaintenancePlanning(Model model, @RequestParam(name = "eid", required = false) Integer labEquip,
			@RequestParam(name = "pid", required = false) Integer planningId) {
		List<MaintenancePlanning> plannings = entityService.getAll();
		List<Map<String, Object>> labs = labService.getLabsIdAndNames();
		LabHasEquipement equipement = new LabHasEquipement();
		MaintenancePlanning planning = new MaintenancePlanning();
		if (ObjectUtils.isNotEmpty(planningId)) {
			planning = entityService.getOne(planningId);
			equipement = planning.getLabHasEquipement();
		} else {
			if (ObjectUtils.isNotEmpty(labEquip)) {
				equipement = labEquipementService.getOne(labEquip);
			}
		}
		model.addAttribute("plannings", plannings);
		model.addAttribute("planning", planning);
		model.addAttribute("equipement", equipement);
		model.addAttribute("labs", labs);
		model.addAttribute("mode", 0); // add a new entry
		return "maintenanceplanning/index";
	}

	@GetMapping(value = "/{id}")
	public String getOneMaintenancePlanning(@PathVariable("id") int id, Model model) {
		List<MaintenancePlanning> plannings = entityService.getAll();
		List<LabHasEquipement> equipements = labEquipementService.getAll();
		MaintenancePlanning s = entityService.getOne(id);
		if (s == null) {
			s = new MaintenancePlanning();
		}
		List<Map<String, Object>> labs = labService.getLabsIdAndNames();
		model.addAttribute("equipements", equipements);
		model.addAttribute("plannings", plannings);
		model.addAttribute("mode", 1); // modify an entry
		model.addAttribute("labs", labs);
		model.addAttribute("planning", s);
		return "maintenanceplanning/index";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteMaintenancePlanning(@PathVariable("id") int id) {
		entityService.delete(id);
		return "redirect:/maintenanceplanning";
	}

}
