package com.Example.Employee.controller;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Set;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Example.Employee.Service.EmployeeService;
import com.Example.Employee.Service.HrService;

import com.Example.Employee.entity.HrModule;
import com.Example.Employee.repository.EmployeeRepo;
import com.Example.Employee.repository.HrRepo;


@RestController
@RequestMapping("/hrmodule")

public class HRmoduleController {
	@Autowired
	private HrService service;
	@Autowired
	private EmployeeService empservice;
	@Autowired
	private EmployeeRepo emprepo;
	@Autowired
	private HrRepo hrrepo;
	

	@GetMapping("/hrms")
	public ModelAndView data13() {
		ModelAndView view = new ModelAndView("hr");
		return view;
	}

	
	@PostMapping("/addhr")
	public ResponseEntity<String> applyForLeave(@RequestBody HrModule Hrmod) {
		try {
			LocalDate myObj = LocalDate.now();
			Hrmod.setModifydate(myObj);
			System.out.println(Hrmod);
			service.save(Hrmod);
			return new ResponseEntity<>("Leave applied successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to apply for leave", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

   
    
        @GetMapping("/fetchEmployees")
        @ResponseBody
        public List<String> fetchEmployees() {
            List<String> employees = empservice.getAllEmployees();
            if (employees == null) {
                return Collections.emptyList();
            }
            return employees;
        }
        
        @GetMapping("/hrmsview")
    	public ModelAndView viewdata() {
    		ModelAndView view = new ModelAndView("hrView");
    		return view;
    	}
        
        @GetMapping("/fetchhr")
        public ResponseEntity<List<HrModule>> fetchHr(
                @RequestParam("startDate") String startDateStr,
                @RequestParam("endDate") String endDateStr,
                @RequestParam(value = "employeename", defaultValue = "") String employee) {

            System.out.println("Received request with startDate: " + startDateStr + ", endDate: " + endDateStr + ", employeename: " + employee);

            try {
               
                LocalDate startDate = LocalDate.parse(startDateStr);
                LocalDate endDate = LocalDate.parse(endDateStr);

              
                if (startDate.isAfter(endDate)) {
                    return ResponseEntity.badRequest().body(null);
                }

               
                List<HrModule> hrModules = service.getdataByDateRange(startDate, endDate, employee);

                
                return ResponseEntity.ok(hrModules);

            } catch (DateTimeParseException e) {
                
                return ResponseEntity.badRequest().body(null);
            } catch (Exception e) {
               
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        
        @GetMapping("/Hredit")
        public ModelAndView editLeavePage(@RequestParam("id") Integer id) {
            System.out.println("Requested ID: " + id);
            ModelAndView modelAndView = new ModelAndView();
            try {
                HrModule hrmod = service.getLeaveRequestById(id);
                modelAndView.addObject("hrmod", hrmod);
                modelAndView.setViewName("Hredit");
                System.out.println("Fetched HR Module: " + hrmod);
            } catch (Exception e) {
                modelAndView.addObject("error", "Leave request not found.");
                modelAndView.setViewName("hrView");
                System.out.println("Exception: " + e.getMessage());
            }
            return modelAndView;
        }

        
        @PostMapping("/update")
        public ResponseEntity<String> updateLeave(@RequestBody HrModule hrmod) {
            try {
                if (hrmod.getId() == null) {
                    return ResponseEntity.badRequest().body("Leave request ID is missing");
                }
             
                hrmod.setModifydate(LocalDate.now());

            
                boolean updated = service.updateLeave(hrmod);
                if (updated) {
                    return ResponseEntity.ok("Leave request updated successfully");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("update request not found");
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update leave request");
            }
        }
        
        
//generating the excel file
        @GetMapping("/generateExcel")
        public ResponseEntity<InputStreamResource> generateExcel(
                @RequestParam("startDate") String startDateStr,
                @RequestParam("endDate") String endDateStr) throws IOException {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);

            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                XSSFSheet sheet = workbook.createSheet("Report");
                XSSFRow header = sheet.createRow(0);
                header.createCell(0).setCellValue("Employee Name");

                LocalDate currentDate = startDate;
                int columnIndex = 1;
                while (!currentDate.isAfter(endDate)) {
                    header.createCell(columnIndex++).setCellValue(currentDate.format(formatter));
                    currentDate = currentDate.plusDays(1);
                }

                try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    workbook.write(baos);
                    byte[] excelBytes = baos.toByteArray();

                    ByteArrayInputStream bais = new ByteArrayInputStream(excelBytes);
                    InputStreamResource resource = new InputStreamResource(bais);

                    HttpHeaders headers = new HttpHeaders();
                    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xlsx");
                    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

                    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
                }
            }
        }

        @PostMapping("/upload")
        public ResponseEntity<String> uploadExcel(@RequestParam("fileUpload") MultipartFile file) {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("No file selected.");
            }

            List<String> validEmployeeNames = emprepo.findAllEmployeeFirstNames();
            Set<String> validEmployeeNameSet = new HashSet<>(validEmployeeNames);

            List<String> validStatuses = hrrepo.findDistinctStatuses();
            Set<String> validStatusSet = new HashSet<>(validStatuses);

            try (InputStream inputStream = file.getInputStream();
                 XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

                XSSFSheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();

                Row headerRow = rowIterator.next();
                int numCols = headerRow.getPhysicalNumberOfCells();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                while (rowIterator.hasNext()) {
                    Row dataRow = rowIterator.next();
                    String employeeName = getCellValueAsString(dataRow.getCell(0));

                    if (employeeName == null || employeeName.isEmpty() || !validEmployeeNameSet.contains(employeeName)) {
                        System.err.println("Invalid or missing employee name: " + employeeName);
                        continue;
                    }

                    for (int col = 1; col < numCols; col++) {
                        String dateStr = getCellValueAsString(headerRow.getCell(col));
                        String status = getCellValueAsString(dataRow.getCell(col));

                        if (status == null || status.isEmpty() || !validStatusSet.contains(status)) {
                            System.err.println("Invalid or missing status: " + status);
                            continue;
                        }

                        LocalDate date;
                        try {
                            date = LocalDate.parse(dateStr, dateFormatter);
                        } catch (DateTimeParseException e) {
                            System.err.println("Date parsing failed for value: " + dateStr + " Error: " + e.getMessage());
                            continue;
                        }

                        if (hrrepo.existsByEmployeenameAndStartDateAndStatus(employeeName, date, status)) {
                            System.err.println("Leave request already exists for employee: " + employeeName + ", date: " + date + ", status: " + status);
                            continue;
                        }

                        HrModule hrmod = new HrModule();
                        hrmod.setEmployeename(employeeName);
                        hrmod.setStartDate(date);
                        hrmod.setStatus(status);
                        hrmod.setModifydate(LocalDate.now());

                        try {
                            service.save(hrmod);
                        } catch (Exception e) {
                            System.err.println("Failed to save leave request: " + hrmod + " Error: " + e.getMessage());
                        }
                    }
                }

                return ResponseEntity.ok("File uploaded and data saved successfully");

            } catch (IOException e) {
                System.err.println("IO Error: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file. Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Unexpected Error: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Error: " + e.getMessage());
            }
        }

        private String getCellValueAsString(Cell cell) {
            if (cell == null) {
                return null;
            }
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getDateCellValue().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().toString();
                    }
                    return String.valueOf(cell.getNumericCellValue());
                default:
                    return cell.toString();
            }
        }

    }

        
    


