/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 */
package org.itech.equipment.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.dto.MaintenanceDTO;
import org.itech.equipment.exception.OperationFailedException;
import org.itech.equipment.model.Equipement;
import org.itech.equipment.model.LabHasEquipement;
import org.itech.equipment.model.Maintenance;
import org.itech.equipment.model.Panne;
import org.itech.equipment.service.LabHasEquipementService;
import org.itech.equipment.service.LabService;
import org.itech.equipment.service.MaintenanceService;
import org.itech.equipment.service.PanneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

	@Autowired
	private MaintenanceService entityService;
	@Autowired
	private LabHasEquipementService labEquipementService;
	@Autowired
	private LabService labService;
	@Autowired
	private PanneService panneService;

	@PostMapping(value = "")
	public String createMaintenance(@Valid MaintenanceDTO maintenance) {
		if (ObjectUtils.isNotEmpty(maintenance.getMaintenance().getId())) {
			return this.editMaintenance(maintenance);
		}
		LabHasEquipement oldEq = labEquipementService.getOne(maintenance.getMaintenance().getLabHasEquipementId());
		if (oldEq.getStatus() == Equipement.STATUS_CURRENT_MAINTENANCE) {
			throw new OperationFailedException("Maintenance déjà en cours pour cet équipement");
		}
		if (oldEq.getStatus() != Equipement.STATUS_BREAK && maintenance.getMaintenance().getType().equals("CURATIVE")) {
			throw new OperationFailedException("Panne non notifiée pour cet équipement");
		}
		Panne p = panneService.getLastForEquipement(oldEq.getId());
		boolean isPanneExist = ObjectUtils.isNotEmpty(maintenance.getMaintenance().getPanneId())
				|| ObjectUtils.isNotEmpty(p);
		if (ObjectUtils.isEmpty(maintenance.getMaintenance().getId())
				&& maintenance.getMaintenance().getType().equals("CURATIVE") && !isPanneExist) {
			throw new OperationFailedException("Panne non identifiée pour cette maintenance curative");
		}
		Maintenance m = entityService.createOrUpdate(maintenance.getMaintenance());
		if (ObjectUtils.isNotEmpty(m)) {
			LabHasEquipement eq = labEquipementService.getOne(m.getLabHasEquipementId());
			if (ObjectUtils.isEmpty(m.getEndDate())) {
				eq.setStatus(Equipement.STATUS_CURRENT_MAINTENANCE);
			} else {
				// eq.setStatus(maintenance.getStatus());
				eq.setStatus(Equipement.STATUS_OK);
			}
			labEquipementService.createOrUpdate(eq);
		}
		return "redirect:/maintenance";
	}

	@PostMapping(value = "/edit")
	public String editMaintenance(@Valid MaintenanceDTO maintenance) {
		if (ObjectUtils.isEmpty(maintenance.getMaintenance().getId())) {
			this.createMaintenance(maintenance);
		}
		Maintenance m = entityService.createOrUpdate(maintenance.getMaintenance());
		if (ObjectUtils.isNotEmpty(m)) {
			LabHasEquipement eq = labEquipementService.getOne(m.getLabHasEquipementId());
			if (ObjectUtils.isEmpty(m.getEndDate())) {
				eq.setStatus(Equipement.STATUS_CURRENT_MAINTENANCE);
			} else {
				// eq.setStatus(maintenance.getStatus());
				eq.setStatus(Equipement.STATUS_OK);
			}
			labEquipementService.createOrUpdate(eq);
		}
		return "redirect:/maintenance";
	}

	@GetMapping(value = "")
	public String getAllMaintenance(@RequestParam(name = "eid", required = false) Integer labEquip,
			@RequestParam(name = "pid", required = false) Integer panne,
			@RequestParam(name = "mid", required = false) Integer maintenanceId,
			@RequestParam(name = "all", defaultValue = "0") Integer all, Model model) {
		LabHasEquipement equipement = new LabHasEquipement();
		// get maintenance entity from param
		Maintenance maintenance = new Maintenance();
		if (ObjectUtils.isNotEmpty(maintenanceId)) {
			maintenance = entityService.getOne(maintenanceId);
			equipement = maintenance.getLabHasEquipement();
		} else {
			if (ObjectUtils.isNotEmpty(labEquip)) {
				equipement = labEquipementService.getOne(labEquip);
			}
		}
		maintenance.setPanneId(panne);
		MaintenanceDTO maintenanceDTO = new MaintenanceDTO();
		maintenanceDTO.setMaintenance(maintenance);
		maintenanceDTO.setStatus(equipement.getStatus());
		List<Maintenance> maintenances = (all == 0) ? entityService.getPending() : entityService.getAll();
		List<Map<String, Object>> labs = labService.getLabsIdAndNames();
		model.addAttribute("maintenances", maintenances);
		model.addAttribute("equipement", equipement);
		model.addAttribute("maintenance", maintenanceDTO);
		model.addAttribute("labs", labs);
		model.addAttribute("all", all);
		model.addAttribute("mode", ObjectUtils.isNotEmpty(maintenanceId) ? 1 : 0); // add or edit an entry
		// System.out.println(equipement);
		return "maintenance/index";
	}

	@GetMapping(value = "/{id}")
	public String getOneMaintenance(@PathVariable("id") int id, Model model) {
		List<Maintenance> maintenances = entityService.getAll();
		List<LabHasEquipement> equipements = labEquipementService.getAll();
		MaintenanceDTO dto = new MaintenanceDTO();
		Maintenance s = entityService.getOne(id);
		if (s == null) {
			s = new Maintenance();
		}
		dto.setMaintenance(s);
		model.addAttribute("equipements", equipements);
		model.addAttribute("maintenances", maintenances);
		model.addAttribute("mode", 1); // modify an entry
		model.addAttribute("maintenance", dto);
		return "maintenance/index";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteMaintenance(@PathVariable("id") int id) {
		Maintenance m = entityService.getOne(id);
		if (entityService.delete(id)) {
			LabHasEquipement eq = labEquipementService.getOne(m.getLabHasEquipementId());
			eq.setStatus(Equipement.STATUS_OK);
			labEquipementService.createOrUpdate(eq);
		}
		return "redirect:/maintenance";
	}

}
