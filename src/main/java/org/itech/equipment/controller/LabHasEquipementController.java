/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 */
package org.itech.equipment.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.Equipement;
import org.itech.equipment.model.LabHasEquipement;
import org.itech.equipment.model.Supplier;
import org.itech.equipment.service.ContractorService;
import org.itech.equipment.service.EquipementService;
import org.itech.equipment.service.LabHasEquipementService;
import org.itech.equipment.service.LabService;
import org.itech.equipment.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lab_equipement")
public class LabHasEquipementController {

	@Autowired
	private LabHasEquipementService entityService;
	@Autowired
	private ContractorService contractorService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private EquipementService equipementService;
	@Autowired
	private LabService labService;

	@PostMapping(value = "")
	public String createLab(@Valid LabHasEquipement equipement) {
		entityService.createOrUpdate(equipement);
		return "redirect:/lab_equipement";
	}

	@GetMapping(value = "")
	public String getAllLabEquipements(Model model, @RequestParam(name = "labId", required = false) Integer labId) {

		List<LabHasEquipement> equipements = new ArrayList<LabHasEquipement>();
		if (ObjectUtils.isNotEmpty(labId) && labId != -1) {
			equipements = entityService.findByLabId(labId);
		} else {
			equipements = entityService.getAll();
		}

		List<Map<String, Object>> labs = labService.getLabsIdAndNames();
		model.addAttribute("equipements", equipements);
		model.addAttribute("labs", labs);
		model.addAttribute("labId", labId);
		return "equipement/lab_equipement";
	}

	@GetMapping(value = { "/edit", "/edit/{id}" })
	public String getOneLab(@PathVariable(name = "id", required = false) Integer id, Model model) {
		LabHasEquipement equipement;
		short mode = 0;
		if (id != null) {
			equipement = entityService.getOne(id);
			mode = 1;
			if (equipement == null) {
				equipement = new LabHasEquipement();
			}
		} else
			equipement = new LabHasEquipement();
		List<Map<String, Object>> contractors = contractorService.getContractorIdAndNames();
		List<Map<String, Object>> labs = labService.getLabsIdAndNames();
		List<Supplier> suppliers = supplierService.getAll();
		List<Equipement> equipements = equipementService.getAll();
		model.addAttribute("equipement", equipement);
		model.addAttribute("equipements", equipements);
		model.addAttribute("contractors", contractors);
		model.addAttribute("labs", labs);
		model.addAttribute("mode", mode);
		model.addAttribute("suppliers", suppliers);
		return "equipement/edit_equipement";
	}

	@GetMapping(value = "/details/{id}")
	public String getOneLabEquipement(@PathVariable("id") int id, Model model) {
		LabHasEquipement equipement = entityService.getOne(id);
		if (equipement == null) {
			equipement = new LabHasEquipement();
		}
		model.addAttribute("equipement", equipement);
		return "equipement/detail_equipement";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteLabEquipement(@PathVariable("id") int id) {
		entityService.delete(id);
		return "redirect:/lab_equipement";
	}

	@GetMapping(value = "/equipement_short/{lab_id}")
	public ResponseEntity<List<Map<String, Object>>> getEquipementShortList(@PathVariable("lab_id") Integer labId) {
		List<Map<String, Object>> e = entityService.getEquipementList(labId);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/func_equipement_short/{lab_id}")
	public ResponseEntity<List<Map<String, Object>>> getFunctionalEquipementShortList(
			@PathVariable("lab_id") Integer labId) {
		List<Map<String, Object>> e = entityService.getEquipementList(labId, Equipement.STATUS_OK);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/broken_equipement_short/{lab_id}")
	public ResponseEntity<List<Map<String, Object>>> getBrokenEquipementShortList(
			@PathVariable("lab_id") Integer labId) {
		List<Map<String, Object>> e = entityService.getEquipementList(labId, Equipement.STATUS_BREAK);
//		List<Map<String, Object>> e2 = entityService.getEquipementList(labId,Equipement.STATUS_CURRENT_MAINTENANCE);
//		e.addAll(e2);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/maintenance_equipement_short/{lab_id}")
	public ResponseEntity<List<Map<String, Object>>> getMaintenanceEquipementShortList(
			@PathVariable("lab_id") Integer labId) {
		List<Map<String, Object>> e = entityService.getEquipementList(labId, Equipement.STATUS_CURRENT_MAINTENANCE);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
}
