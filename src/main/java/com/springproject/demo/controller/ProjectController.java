package com.springproject.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.demo.model.AdminLogin;
import com.springproject.demo.model.Project;
import com.springproject.demo.repo.AdminRepo;
import com.springproject.demo.service.ProjectService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ProjectController {
	@Autowired
	private ProjectService service;
	
	@Autowired
	private AdminRepo adrepo;
	
	private int login=0;
	
	@RequestMapping("/")
	public String home()
	{
		return "land";
	}
	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response)
	{
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //Http 1.1
		login=0;
		return "land";
	}
	@RequestMapping("/validate")
	public ModelAndView retInsert(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		AdminLogin ad = adrepo.findByUserid(req.getParameter("userid"));
		if(ad!=null && ad.getPassword().equals(req.getParameter("pass"))) {
			mv.setViewName("home");
			login=1;
		}
		else
			mv.setViewName("login");
		return mv;
	}
	@RequestMapping("/insertdata")
	public ModelAndView insertdata(HttpServletRequest req,@RequestParam("file") MultipartFile file) throws IOException
	{
		ModelAndView mv = new ModelAndView();
		Project p1 = new Project();
		p1.setGuide(req.getParameter("guide"));
		p1.setBatch(req.getParameter("batch"));
		p1.setTitle(req.getParameter("title"));
		p1.setAbstractdocname(file.getOriginalFilename());
		p1.setAbstractdocsize(file.getSize());
		p1.setAbstractdoc(file.getBytes());
		service.createRecord(p1);
		mv.setViewName("home");
		return mv;
	}
	@RequestMapping("/result")
	public ModelAndView result(HttpServletRequest req)
	{
		ModelAndView mv = new ModelAndView();
<<<<<<< HEAD
		
=======
>>>>>>> refs/remotes/origin/main
		List<Project> relatedLists = new ArrayList<>();
		String searchword = req.getParameter("search");
		String filter = req.getParameter("filter");
        if(searchword!=null) {
        String[] s = searchword.split("\\s+");
        List<Project> listofrecords = service.getAllRecords();
        for (Project list : listofrecords) {
            	boolean found = false;
				String[] words = list.getTitle().split("\\s+");
				if(filter!=null) {
            	if(filter.equals("Batch")) {
            		words = list.getBatch().split("\\s+");
                }
            	else if(filter.equals("Guide")){
                    words = list.getGuide().split("\\s+");
            	}
				}
                for (String word : words) {
                	String w1 = word.toUpperCase();
                    for(String i:s) {
                    	if(i.length()>w1.length() && ((i.toUpperCase()).indexOf(w1)!=-1)) {
                    		found=true;
                    		break;
                    	}else {
                    		if(w1.indexOf(i.toUpperCase())!=-1) {
                    			found=true;
                        		break;
                    		}
                    	}
                    }	
                }
                
                if (found) {
                    relatedLists.add(list);
                }
            }
        }

		mv.addObject("listofrecords",relatedLists);
		mv.setViewName("result");
		return mv;
	}
	@GetMapping("/downloadfile")
	public void dowloadfile(@Param("id") Long id,Model model,HttpServletResponse response) throws IOException
	{
		Optional<Project> temp = service.findRecordById(id);
		if(temp!=null)
		{
			Project project = temp.get();
			response.setContentType("octet-stream");
            String headerKey = "Content-disposition";
            String HeaderValue = "attachment; fileName = "+ project.getAbstractdocname();
            response.setHeader(headerKey, HeaderValue);
            ServletOutputStream outputstream = response.getOutputStream();
            outputstream.write(project.getAbstractdoc());
            outputstream.close();
		}
	}
	
	

}
