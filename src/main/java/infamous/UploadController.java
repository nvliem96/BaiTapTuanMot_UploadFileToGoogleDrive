package infamous;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import GDservice.InformationFile;

import GDservice.GoogleDriveService;

@Controller
public class UploadController {
 private final GoogleDriveService googleService;
 @Autowired
 public UploadController(GoogleDriveService googleService) {
		this.googleService = googleService;
	}
	//Home
	@GetMapping("/")
	public String Home(Model model) {
		return "index";
	}

	/**
	 * View
	 */
	@GetMapping("upload")
	public String Upload(Model model) {
		return "upload";
	}
	/**
	 * Submit
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("upload")
 public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                RedirectAttributes redirectAttributes) {

     boolean flag=googleService.uploadFile(file.getOriginalFilename(),file.getName(), file.getContentType());
     System.out.println(file.getName()+"     "+file.getOriginalFilename()+"     "+file.getContentType());
     
     if(flag==true){
     	 redirectAttributes.addFlashAttribute("message",
                  "Upload Success");
     }else{
     	redirectAttributes.addFlashAttribute("message",
                 "Upload fail");
     }
     
     return "redirect:/upload";
	
 }
}
