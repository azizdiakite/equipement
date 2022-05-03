/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 */
package org.itech.equipment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.itech.equipment.model.Equipement;
import org.itech.equipment.service.DashboardService;
import org.itech.equipment.service.EquipementService;
import org.itech.equipment.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private DashboardService entityService;
	@Autowired
	private LabService labService;
	@Autowired
	private EquipementService equipementService;

	@GetMapping(value = "")
	public String index(@RequestParam(required = false) Integer l_id, @RequestParam(required = false) Integer e_id,
			Model model) {

		List<Map<String, Object>> labs = labService.getLabsIdAndNames();
		List<Equipement> equipements = equipementService.getAll();
		Map<String, Object> equipementCount = entityService.getEquipementCount(l_id, e_id);
		model.addAttribute("labs", labs);
		model.addAttribute("labId", l_id);
		model.addAttribute("equipements", equipements);
		model.addAttribute("equipementCount", equipementCount);
		return "dashboard/index";
	}

	@GetMapping(value = "/indicators")
	public String getIndicators(Model model,@RequestParam(required = false) Integer l_id) {
		List<Map<String, Object>> labs = labService.getLabsIdAndNames();
		model.addAttribute("labs", labs);
		model.addAttribute("labId", l_id);
		return "dashboard/indicators";
	}

	@GetMapping(value = "/equipement_by_name")
	public ResponseEntity<List<Map<String, Object>>> getEquipementCountByName(
			@RequestParam(required = false) Integer l_id, @RequestParam(required = false) Integer e_id) {
		Integer labIb = (l_id == 0) ? null : l_id;
		Integer equipementId = (e_id == 0) ? null : e_id;

		List<Map<String, Object>> e = entityService.getEquipementCountByEquipement(labIb, equipementId);

		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/equipement_by_lab_type")
	public ResponseEntity<List<Map<String, Object>>> getEquipementCountByLabType(
			@RequestParam(required = false) Integer l_id, @RequestParam(required = false) Integer e_id) {
		Integer labIb = (l_id == 0) ? null : l_id;
		Integer equipementId = (e_id == 0) ? null : e_id;

		List<Map<String, Object>> e = entityService.getEquipementCountByLabType(labIb, equipementId);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/equipement_warrant")
	public ResponseEntity<List<Map<String, Object>>> getEquipementWarrant(@RequestParam(required = false) Integer l_id,
			@RequestParam(required = false) Integer e_id) {
		Integer labIb = (l_id == 0) ? null : l_id;
		Integer equipementId = (e_id == 0) ? null : e_id;

		Map<String, Object> e = entityService.getEquipementWarrant(labIb, equipementId);
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Map<String, String> legend = new HashMap<String, String>();
		legend.put("all", "Total");
		legend.put("contract", "Sous Contrats");
		legend.put("warrant", "Sous Garanties");
		e.forEach((a, b) -> {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("name", legend.get(a));
			m.put("y", b);
			res.add(m);
		});
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping(value = "/panne_type")
	public ResponseEntity<List<Map<String, Object>>> getPanneByType(@RequestParam(required = false) Integer l_id,
			@RequestParam(required = false) Integer e_id) {
		Integer labIb = (l_id == 0) ? null : l_id;
		Integer equipementId = (e_id == 0) ? null : e_id;

		List<Map<String, Object>> e = entityService.getPanneByType(labIb, equipementId);
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Map<String, String> legend = new HashMap<String, String>();
		legend.put("1", "Mineure");
		legend.put("2", "Critique");
		legend.put("3", "Majeure (Arrêt)");
		e.stream().map(el -> el.replace("name", legend.get(el.get("name"))));
		e.forEach(el -> {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("name", legend.get(el.get("name")));
			m.put("y", el.get("y"));
			res.add(m);
		});
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping(value = "/preventive_maintenance")
	public ResponseEntity<Map<String, Object>> getPreventiveMaintenance(@RequestParam(required = false) Integer l_id) {
		Integer labIb = (l_id == 0) ? null : l_id;
//		Integer equipementId = (e_id == 0) ? null : e_id;

		List<Map<String, Object>> e1 = entityService.getMaintenancePlanning(labIb);
		List<Map<String, Object>> e2 = entityService.getPreventiveMaintenanceDone(labIb);
		Map<String, Object> res = new HashMap<String, Object>();

		List<Object> categories = new ArrayList<Object>();
		List<Object> prev = new ArrayList<Object>();
		List<Object> done = new ArrayList<Object>();
		e1.forEach(el -> {
			categories.add(el.get("name"));
			prev.add(el.get("y"));
		});
		e2.forEach(el -> {
			done.add(el.get("y"));
		});
		res.put("categories", categories);
		res.put("prev", prev);
		res.put("done", done);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping(value = "/curative_maintenance")
	public ResponseEntity<Map<String, Object>> getCurativeMaintenance(@RequestParam(required = false) Integer l_id) {
		Integer labIb = (l_id == 0) ? null : l_id;

		List<Map<String, Object>> e = entityService.getCurativeMaintenance(labIb);
		Map<String, Object> res = new HashMap<String, Object>();

		List<Object> categories = new ArrayList<Object>();
		List<Object> done = new ArrayList<Object>();
		e.forEach(el -> {
			categories.add(el.get("name"));
			done.add(el.get("y"));
		});

		res.put("categories", categories);
		res.put("done", done);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping(value = "/equipement_by_maintenance_type")
	public ResponseEntity<List<Map<String, Object>>> getEquipementCountByMaintenanceType(
			@RequestParam(required = false) Integer l_id, @RequestParam(required = false) Integer e_id) {
		Integer labIb = (l_id == 0) ? null : l_id;
		Integer equipementId = (e_id == 0) ? null : e_id;

		List<Map<String, Object>> e = entityService.getMaintenanceByType(labIb, equipementId);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/total_breakdown_time")
	public ResponseEntity<List<Map<String, Object>>> getTotalBreakdownTime(
			@RequestParam(required = false) Integer l_id) {
		Integer labIb = (l_id == 0) ? null : l_id;

		List<Map<String, Object>> e = entityService.getTotalBreakdownTime(labIb);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/total_repair_time")
	public ResponseEntity<List<Map<String, Object>>> getTotalRepairTime(@RequestParam(required = false) Integer l_id) {
		Integer labIb = (l_id == 0) ? null : l_id;

		List<Map<String, Object>> e = entityService.getTotalRepairTime(labIb);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/mtbf")
	public ResponseEntity<List<Map<String, Object>>> getMTBF(@RequestParam(required = false) Integer l_id) {
		Integer labIb = (l_id == 0) ? null : l_id;

		List<Map<String, Object>> e = entityService.getMeanTimeBetweenFailure(labIb);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/mttr")
	public ResponseEntity<Map<String, Object>> getMTTR(@RequestParam(required = false) Integer l_id) {
		Integer labIb = (l_id == 0) ? null : l_id;

		List<Map<String, Object>> e1 = entityService.getMeanTimeToRepair(labIb);
		List<Map<String, Object>> e2 = entityService.getReparationMeanTime(labIb);

		Map<String, Object> res = new HashMap<String, Object>();

		List<Object> categories = new ArrayList<Object>();
		List<Object> TimeToRepair = new ArrayList<Object>();
		List<Object> ReparationTime = new ArrayList<Object>();
		e1.forEach(el -> {
			categories.add(el.get("name"));
			TimeToRepair.add(el.get("y"));
		});
		e2.forEach(el -> {
			ReparationTime.add(el.get("y"));
		});
		res.put("categories", categories);
		res.put("TimeToRepair", TimeToRepair);
		res.put("ReparationTime", ReparationTime);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping(value = "/availability")
	public ResponseEntity<List<Map<String, Object>>> getEquipementAvailability(
			@RequestParam(required = false) Integer l_id) {
		Integer labIb = (l_id == 0) ? null : l_id;

		List<Map<String, Object>> e = entityService.getAvailability(labIb);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

}
