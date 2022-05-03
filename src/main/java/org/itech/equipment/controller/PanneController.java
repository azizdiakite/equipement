/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 */
package org.itech.equipment.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.Equipement;
import org.itech.equipment.model.LabHasEquipement;
import org.itech.equipment.model.Maintenance;
import org.itech.equipment.model.Panne;
import org.itech.equipment.service.LabHasEquipementService;
import org.itech.equipment.service.LabService;
import org.itech.equipment.service.MaintenanceService;
import org.itech.equipment.service.PanneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <h2>PanneController</h2>
 */
@Controller
@RequestMapping("/panne")
public class PanneController {

	@Autowired
	private PanneService entityService;
//	@Autowired
//	private MaintenanceService maintenanceService;

	@Autowired
	private LabHasEquipementService labEquipementService;
	@Autowired
	private LabService labService;

	@PostMapping(value = "")
	public String createPanne(@Valid Panne panne) {

		Panne p = entityService.createOrUpdate(panne);
		if (ObjectUtils.isNotEmpty(p)) {
			LabHasEquipement eq = labEquipementService.getOne(p.getLabHasEquipementId());
//			if (ObjectUtils.isNotEmpty(panne.getContractorInformDate())) {
//				eq.setStatus(Equipement.STATUS_CURRENT_MAINTENANCE);
//				Maintenance m = new Maintenance();
//				m.setLabHasEquipementId(p.getLabHasEquipementId());
//				m.setPanneId(p.getId());
//				m.setType("CURATIVE");
//				m.setStartDate(p.getContractorInformDate());
//			} else
				eq.setStatus(Equipement.STATUS_BREAK);
			labEquipementService.createOrUpdate(eq);
		}
		return "redirect:/panne";
	}

	@GetMapping(value = "")
	public String getAllPanne(HttpServletRequest servletRequest,
			@RequestParam(name = "eid", required = false) Integer labEquip,
			@RequestParam(name = "pid", required = false) Integer panneId,
			@RequestParam(name = "all", defaultValue = "0") Integer all, Model model) {
		LabHasEquipement equipement = new LabHasEquipement();
		// get panne entity from param
		Panne panne = new Panne();
		if (ObjectUtils.isNotEmpty(panneId)) {
			panne = entityService.getOne(panneId);
			equipement = panne.getLabHasEquipement();
		} else {
			if (ObjectUtils.isNotEmpty(labEquip)) {
				equipement = labEquipementService.getOne(labEquip);
			}
		}
		// List<Panne> pannes = entityService.getAll();
		List<Panne> pannes = (all == 0) ? entityService.getPending() : entityService.getAll();
		List<Map<String, Object>> labs = labService.getLabsIdAndNames();
		model.addAttribute("pannes", pannes);
		model.addAttribute("equipement", equipement);
		model.addAttribute("panne", panne);
		model.addAttribute("labs", labs);
		model.addAttribute("all", all);
		model.addAttribute("mode", 0); // add a new entry
		return "panne/index";
	}

	@GetMapping(value = "/{id}")
	public String getOnePanne(@PathVariable("id") int id, Model model) {
		List<Panne> pannes = entityService.getAll();
		List<LabHasEquipement> equipements = labEquipementService.getAll();
		Panne s = entityService.getOne(id);
		if (s == null) {
			s = new Panne();
		}
		model.addAttribute("equipements", equipements);
		model.addAttribute("pannes", pannes);
		model.addAttribute("mode", 1); // modify an entry
		model.addAttribute("panne", s);
		return "panne/index";
	}

	@GetMapping(value = "/delete/{id}")
	public String deletePanne(@PathVariable("id") int id) {
		Panne p = entityService.getOne(id);
		if (entityService.delete(id)) {
			LabHasEquipement eq = labEquipementService.getOne(p.getLabHasEquipementId());
			eq.setStatus(Equipement.STATUS_OK);
			labEquipementService.createOrUpdate(eq);
		}
		return "redirect:/panne";
	}
}
