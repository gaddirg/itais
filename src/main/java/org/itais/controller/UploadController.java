package org.itais.controller;

import org.itais.domain.Inventory;
import org.itais.service.AssetStatusService;
import org.itais.service.AssetTypeService;
import org.itais.service.InventoryService;
import org.itais.service.OfficeService;
import org.itais.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

	private OfficeService officeService;
	private UserService userService;
	private InventoryService inventoryService;
	private AssetTypeService assetTypeService;
	private AssetStatusService assetStatusService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService);
	}

	@Autowired
	public UploadController(OfficeService officeService, UserService userService,
			InventoryService inventoryService, AssetTypeService assetTypeService,
			AssetStatusService assetStatusService)
	{
		super();
		this.officeService = officeService;
		this.userService = userService;
		this.inventoryService = inventoryService;
		this.assetTypeService = assetTypeService;
		this.assetStatusService = assetStatusService;
	}



	//Save the uploaded file to this folder
	private Path UPLOADED_FOLDER = Paths.get("temp");

	@GetMapping("/inventory/upload")
	public String index() {
		return "/inventory/upload";
	}

	@PostMapping("/inventory/upload") 
	public String singleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:upload";
		}

		CSVReader reader = null;

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			reader = new CSVReader(new FileReader(UPLOADED_FOLDER + file.getOriginalFilename()));
			String[] line;
			//skip header
			line = reader.readNext();
			while ((line = reader.readNext()) != null) {

				final Inventory inv = new Inventory(line[0], line[1], line[2], line[3], line[4],
						line[5], line[6], line[7],Long.parseLong(line[8]), Long.parseLong(line[9]), line[10],
						officeService.findById((long) 1), assetTypeService.findByType(line[11]),
						assetStatusService.findByStatus("Operational"));
				inventoryService.save(inv);

			}
			reader.close();
			File fileToDelete = new File(UPLOADED_FOLDER + file.getOriginalFilename());
			fileToDelete.delete();

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:upload";
	}


}