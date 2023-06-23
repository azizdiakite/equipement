/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 */
package org.itech.equipment.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.exception.OperationFailedException;
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
	public String index(@RequestParam(required = false) Integer l_id, Model model,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {

		List<Map<String, Object>> labs = labService.getLabsIdAndNames();
		List<Equipement> equipements = equipementService.getAll();
		Map<String, Object> equipementCount = entityService.getEquipementCount(l_id, null);

		model.addAttribute("labs", labs);
		model.addAttribute("labId", l_id);
		model.addAttribute("startDate", start);
		model.addAttribute("endDate", end);
		model.addAttribute("equipements", equipements);
		model.addAttribute("equipementCount", equipementCount);
		return "dashboard/index";
	}

	@GetMapping(value = "/indicators")
	public String getIndicators(Model model, @RequestParam(required = false) Integer l_id,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
		List<Map<String, Object>> labs = labService.getLabsIdAndNames();
		model.addAttribute("startDate", start);
		model.addAttribute("endDate", end);
		model.addAttribute("labs", labs);
		model.addAttribute("labId", l_id);
		return "dashboard/indicators";
	}

	@GetMapping(value = "/equipement_by_name")
	public ResponseEntity<List<Map<String, Object>>> getEquipementCountByName(
			@RequestParam(required = false, defaultValue = "0") Integer l_id) {
		Integer labIb = (l_id == 0) ? null : l_id;

		List<Map<String, Object>> e = entityService.getEquipementCountByEquipement(labIb, null);

		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/equipement_by_lab_type")
	public ResponseEntity<List<Map<String, Object>>> getEquipementCountByLabType(
			@RequestParam(required = false, defaultValue = "0") Integer l_id) {
		Integer labIb = (l_id == 0) ? null : l_id;

		List<Map<String, Object>> e = entityService.getEquipementCountByLabType(labIb, null);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/equipement_warrant")
	public ResponseEntity<List<Map<String, Object>>> getEquipementWarrant(
			@RequestParam(required = false, defaultValue = "0") Integer l_id) {
		Integer labIb = (l_id == 0) ? null : l_id;

		Map<String, Object> e = entityService.getEquipementWarrant(labIb, null);
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
	public ResponseEntity<List<Map<String, Object>>> getPanneByType(
			@RequestParam(required = false, defaultValue = "0") Integer l_id,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
		Integer labIb = (l_id == 0) ? null : l_id;
		SimpleDateFormat df1 = new SimpleDateFormat("dd/mm/yyyy");
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		try {
			Date startDate = ObjectUtils.isNotEmpty(start) ? df1.parse(start) : null;
			Date endDate = ObjectUtils.isNotEmpty(end) ? df1.parse(end) : null;
			List<Map<String, Object>> e = entityService.getPanneByType(labIb, null, startDate, endDate);
			Map<String, String> legend = new HashMap<String, String>();
			legend.put("1", "Carte mère");
			legend.put("2", "Relai statique");
			legend.put("3", "Compresseur");
			legend.put("4", "Ventilateur");
			legend.put("5", "Frayons");
			legend.put("6", "Sonde");
			legend.put("7", "Filtre Déshydrater");
			legend.put("8", "Batteries");
			legend.put("9", "Module");
			legend.put("10", "Accessoires Divers");
			legend.put("", "N/A");
			e.stream().map(el -> el.replace("name", legend.get(el.get("name"))));
			e.forEach(el -> {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("name", legend.get(el.get("name")));
				m.put("y", el.get("y"));
				res.add(m);
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(ex.getMessage());
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping(value = "/preventive_maintenance")
	public ResponseEntity<Map<String, Object>> getPreventiveMaintenance(
			@RequestParam(required = false, defaultValue = "0") Integer l_id,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
		Integer labIb = (l_id == 0) ? null : l_id;
		Map<String, Object> res = new HashMap<String, Object>();
		SimpleDateFormat df1 = new SimpleDateFormat("dd/mm/yyyy");
		try {
			Date startDate = ObjectUtils.isNotEmpty(start) ? df1.parse(start) : null;
			Date endDate = ObjectUtils.isNotEmpty(end) ? df1.parse(end) : null;
			List<Map<String, Object>> e1 = entityService.getMaintenancePlanning(labIb, startDate, endDate);
			List<Map<String, Object>> e2 = entityService.getPreventiveMaintenanceDone(labIb, startDate, endDate);

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
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(ex.getMessage());
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping(value = "/curative_maintenance")
	public ResponseEntity<Map<String, Object>> getCurativeMaintenance(
			@RequestParam(required = false, defaultValue = "0") Integer l_id,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
		Integer labIb = (l_id == 0) ? null : l_id;
		SimpleDateFormat df1 = new SimpleDateFormat("dd/mm/yyyy");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Date startDate = ObjectUtils.isNotEmpty(start) ? df1.parse(start) : null;
			Date endDate = ObjectUtils.isNotEmpty(end) ? df1.parse(end) : null;

			List<Map<String, Object>> e = entityService.getCurativeMaintenance(labIb, startDate, endDate);

			List<Object> categories = new ArrayList<Object>();
			List<Object> done = new ArrayList<Object>();
			e.forEach(el -> {
				categories.add(el.get("name"));
				done.add(el.get("y"));
			});

			res.put("categories", categories);
			res.put("done", done);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(ex.getMessage());
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}


	@GetMapping(value = "/number_panne_by_equipement")
	public ResponseEntity<Map<String, Object>> getPanneByEquipement(
			@RequestParam(required = false, defaultValue = "0") Integer l_id,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
		Integer labIb = (l_id == 0) ? null : l_id;
		SimpleDateFormat df1 = new SimpleDateFormat("dd/mm/yyyy");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Date startDate = ObjectUtils.isNotEmpty(start) ? df1.parse(start) : null;
			Date endDate = ObjectUtils.isNotEmpty(end) ? df1.parse(end) : null;

			List<Map<String, Object>> e = entityService.getPanneByEquipement(labIb, startDate, endDate);

			List<Object> names = new ArrayList<Object>();
			List<Object> datas = new ArrayList<Object>();
			e.forEach(el -> {
				names.add(el.get("name"));
				datas.add(el.get("y"));
			});

			res.put("names", names);
			res.put("datas", datas);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(ex.getMessage());
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping(value = "/equipement_by_maintenance_type")
	public ResponseEntity<List<Map<String, Object>>> getEquipementCountByMaintenanceType(
			@RequestParam(required = false, defaultValue = "0") Integer l_id,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
		Integer labIb = (l_id == 0) ? null : l_id;
		SimpleDateFormat df1 = new SimpleDateFormat("dd/mm/yyyy");

		List<Map<String, Object>> e = new ArrayList<Map<String, Object>>();
		try {
			Date startDate = ObjectUtils.isNotEmpty(start) ? df1.parse(start) : null;
			Date endDate = ObjectUtils.isNotEmpty(end) ? df1.parse(end) : null;
			e = entityService.getMaintenanceByType(labIb, null, startDate, endDate);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(ex.getMessage());
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/total_breakdown_time")
	public ResponseEntity<List<Map<String, Object>>> getTotalBreakdownTime(
			@RequestParam(required = false, defaultValue = "0") Integer l_id,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
		Integer labIb = (l_id == 0) ? null : l_id;
		SimpleDateFormat df1 = new SimpleDateFormat("dd/mm/yyyy");
		List<Map<String, Object>> e = new ArrayList<Map<String, Object>>();

		try {
			Date startDate = ObjectUtils.isNotEmpty(start) ? df1.parse(start) : null;
			Date endDate = ObjectUtils.isNotEmpty(end) ? df1.parse(end) : null;
			e = entityService.getTotalBreakdownTime(labIb, startDate, endDate);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(ex.getMessage());
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/total_repair_time")
	public ResponseEntity<List<Map<String, Object>>> getTotalRepairTime(
			@RequestParam(required = false, defaultValue = "0") Integer l_id,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
		Integer labIb = (l_id == 0) ? null : l_id;
		SimpleDateFormat df1 = new SimpleDateFormat("dd/mm/yyyy");

		List<Map<String, Object>> e = new ArrayList<Map<String, Object>>();
		try {
			Date startDate = ObjectUtils.isNotEmpty(start) ? df1.parse(start) : null;
			Date endDate = ObjectUtils.isNotEmpty(end) ? df1.parse(end) : null;
			e = entityService.getTotalRepairTime(labIb, startDate, endDate);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(ex.getMessage());
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	/*Mean Time Between Failures = mtbf */
	@GetMapping(value = "/mtbf")
	public ResponseEntity<List<Map<String, Object>>> getMTBF(
			@RequestParam(required = false, defaultValue = "0") Integer l_id,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
		Integer labIb = (l_id == 0) ? null : l_id;
		SimpleDateFormat df1 = new SimpleDateFormat("dd/mm/yyyy");

		List<Map<String, Object>> e = new ArrayList<Map<String, Object>>();
		try {
			Date startDate = ObjectUtils.isNotEmpty(start) ? df1.parse(start) : null;
			Date endDate = ObjectUtils.isNotEmpty(end) ? df1.parse(end) : null;
			e = entityService.getMeanTimeBetweenFailure(labIb, startDate, endDate);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(ex.getMessage());
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@GetMapping(value = "/mttr")
	public ResponseEntity<Map<String, Object>> getMTTR(@RequestParam(required = false, defaultValue = "0") Integer l_id,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
		Integer labIb = (l_id == 0) ? null : l_id;
		SimpleDateFormat df1 = new SimpleDateFormat("dd/mm/yyyy");
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			Date startDate = ObjectUtils.isNotEmpty(start) ? df1.parse(start) : null;
			Date endDate = ObjectUtils.isNotEmpty(end) ? df1.parse(end) : null;
			List<Map<String, Object>> e1 = entityService.getMeanTimeToRepair(labIb, startDate, endDate);
			List<Map<String, Object>> e2 = entityService.getReparationMeanTime(labIb, startDate, endDate);

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
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(ex.getMessage());
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping(value = "/availability")
	public ResponseEntity<List<Map<String, Object>>> getEquipementAvailability(
			@RequestParam(required = false, defaultValue = "0") Integer l_id,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
		Integer labIb = (l_id == 0) ? null : l_id;
		SimpleDateFormat df1 = new SimpleDateFormat("dd/mm/yyyy");

		List<Map<String, Object>> e = new ArrayList<Map<String, Object>>();
		try {
			Date startDate = ObjectUtils.isNotEmpty(start) ? df1.parse(start) : null;
			Date endDate = ObjectUtils.isNotEmpty(end) ? df1.parse(end) : null;
			e = entityService.getAvailability(labIb, startDate, endDate);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(ex.getMessage());
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}


	@GetMapping(value = "/year_first_maintenance")
	public ResponseEntity<Map<String, Object>> getYearFirstMaintenanceByEquipement(
			@RequestParam(required = false, defaultValue = "0") Integer l_id,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
		Integer labIb = (l_id == 0) ? null : l_id;
		SimpleDateFormat df1 = new SimpleDateFormat("dd/mm/yyyy");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Date startDate = ObjectUtils.isNotEmpty(start) ? df1.parse(start) : null;
			Date endDate = ObjectUtils.isNotEmpty(end) ? df1.parse(end) : null;

			List<Map<String, Object>> e = entityService.getYearFirstMaintenanceByEquipement(labIb, startDate, endDate);

			ArrayList datas = new ArrayList<>();

			e.forEach(el -> {
				ArrayList data = new ArrayList<>();
				data.add(String.valueOf(el.get("name")));
				data.add(Integer.parseInt(String.valueOf(el.get("y"))));
				data.add(false);
				datas.add(data);
			});
			res.put("datas", datas);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(ex.getMessage());
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
