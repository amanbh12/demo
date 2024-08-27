package com.Example.Employee.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Example.Employee.Service.AssetsService;
import com.Example.Employee.Service.ManageAssetsService;
import com.Example.Employee.entity.Assets;
import com.Example.Employee.entity.Managment;

@Controller
public class FileUploadController {

    @Autowired
    private ManageAssetsService manageService;

    @Autowired
    private AssetsService assetsService;

    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @PostMapping("/uploadCsv")
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "No file selected.");
            return "redirect:/managment";
        }

        if (!file.getOriginalFilename().endsWith(".csv")) {
            redirectAttributes.addFlashAttribute("message", "Invalid file type. Please upload a CSV file.");
            return "redirect:/managment";
        }

        List<Managment> validManages = new ArrayList<>();
        List<String> invalidRecords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            reader.readLine(); // Skip header line if needed

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length < 6) {
                    invalidRecords.add(line); // Collect invalid records
                    continue; // Skip this line and proceed to the next
                }

                try {
                    String category = values[0];
                    String companyName = values[1];
                    LocalDate date = LocalDate.parse(values[2].trim(), DATE_FORMATTER);
                    String employeeName = values[3];
                    int quantity = Integer.parseInt(values[4].trim());
                    String serialNumber = values[5];
                    String subcategory = values.length > 6 ? values[6] : "";

                    
                    Optional<Assets> asset = assetsService.findByCategoryAndSubcategoryAndCompanyNameAndSerialNumber(category, subcategory, companyName, serialNumber);

                    if (asset.isPresent()) {
                        Managment manage = new Managment();
                        manage.setCategory(category);
                        manage.setSubcategory(subcategory);
                        manage.setCompanyName(companyName);
                        manage.setSerialNumber(serialNumber);
                        manage.setEmployeeName(employeeName);
                        manage.setQuantity(quantity);
                        manage.setDate(date);
                        validManages.add(manage);
                    } else {
                    	invalidRecords.add(line);
                    	return "redirect:/management";
                         
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    invalidRecords.add(line); 
                }
            }

            manageService.saveAll(validManages);
            redirectAttributes.addFlashAttribute("message", "File is  uploaded and  processed successfully.");
            if (!invalidRecords.isEmpty()) {
                redirectAttributes.addFlashAttribute("invalidRecords", invalidRecords);
                return "redirect:/management";
            }
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Failed to process the file.");
        }

        return "redirect:/dashboard";
    }
}
