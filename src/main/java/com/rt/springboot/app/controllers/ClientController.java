package com.rt.springboot.app.controllers;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rt.springboot.app.models.entity.Client;
import com.rt.springboot.app.models.service.IClientService;
import com.rt.springboot.app.models.service.IUploadFileService;
import com.rt.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
public class ClientController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IClientService clientService;

	@Autowired
	private IUploadFileService uploadFileService;

	@Autowired
	private MessageSource messageSource;

	

	
	/* ----- View Photo ----- */
	// '.+' = returns the file name without format
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> viewPhoto(@PathVariable String filename) {

		Resource resource = null;

		try {
			resource = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}


	/* ----- View Clients Details ----- */
	@Secured("ROLE_USER")
	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash, Locale locale) {

		Client client = clientService.fetchByIdWithInvoices(id);
		if (client == null) {
			flash.addFlashAttribute("error", messageSource.getMessage("text.cliente.flash.db.error", null, locale));
			return "redirect:/list";
		}

		model.addAttribute("client", client);
		model.addAttribute("title",
				messageSource.getMessage("text.cliente.listar.titulo", null, locale) + ": " + client.getFirstName());

		return "view";
	}

	/* ----- List Clients ----- */
	@GetMapping(value = { "/list", "/" })
	public String list(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request, Locale locale) {

		// 2 Ways of seeing Roles
		// 1st Way
		if (authentication != null) {
			logger.info("Hello: " + authentication.getName());
		}

		// 1st Way
		if (hasRole("ROLE_ADMIN")) {
			//logger.info("Hello " + auth.getName() + " tienes acceso");
		} else {
			//logger.info("Hello " + auth.getName() + " NO tienes acceso");
		}

		// 2nd Way
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request,
				"ROLE_");

		if (securityContext.isUserInRole("ADMIN")) {
			
		} else {
			
		}

		// 3rd Way
		if (request.isUserInRole("ROLE_ADMIN")) {
			//logger.info("Form using HttpServletRequest: Hola " + auth.getName() + " tienes acceso");
		} else {
			//logger.info("Form usingHttpServletRequest: Hola " + auth.getName() + " NO tienes acceso");
		}

		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Client> clients = clientService.findAll(pageRequest);
		PageRender<Client> pageRender = new PageRender<>("/list", clients);

		model.addAttribute("title", messageSource.getMessage("text.cliente.listar.titulo", null, locale));
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);
		return "list";
	}

	/* ----- Create Client ----- */
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/form")
	public String create(Model model, Locale locale) {
		Client client = new Client();
		model.addAttribute("client", client);
		model.addAttribute("title", messageSource.getMessage("text.cliente.form.titulo.crear", null, locale));
		return "form";
	}

	/* ----- Edit Client ----- */
	// PreAuthorize <=> @Secured("ROLE_ADMIN")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/form/{id}")
	public String update(@PathVariable(value = "id") Long id, RedirectAttributes flash, Model model, Locale locale) {

		Client client = null;

		// If Customer exist, find
		if (id > 0) {
			client = clientService.findOne(id);
			// If not exist, error
			if (client == null) {
				flash.addFlashAttribute("error", messageSource.getMessage("text.cliente.flash.db.error", null, locale));
				return "redirect:/list";
			}
		} else {
			flash.addFlashAttribute("error", messageSource.getMessage("text.cliente.flash.id.error", null, locale));
			return "redirect:/list";
		}

		model.addAttribute("client", client);
		model.addAttribute("title", messageSource.getMessage("text.cliente.form.titulo.editar", null, locale));

		return "form";
	}

	/* ----- Save Client ----- */
	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/form", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public String save(@Valid Client client, BindingResult result, Model model,
			@RequestPart("file") MultipartFile frontImg1, @RequestPart("file1") MultipartFile rhSide1,
			@RequestPart("file2") MultipartFile lhSide1, @RequestPart("file3") MultipartFile rearImg1,
			@RequestPart("file4") MultipartFile dashImg1, @RequestPart("file5") MultipartFile dickyImg1,
			RedirectAttributes flash, SessionStatus status, Locale locale) {

		if (result.hasErrors()) {
			model.addAttribute("title", messageSource.getMessage("text.cliente.form.titulo", null, locale));
			return "form";
		}

	

		String flashMsg = (client.getId() != null)
				? messageSource.getMessage("text.cliente.flash.editar.success", null, locale)
				: messageSource.getMessage("text.cliente.flash.crear.success", null, locale);

		clientService.save(client, frontImg1, rhSide1, lhSide1, rearImg1, dashImg1, dickyImg1);
		status.setComplete();
		flash.addFlashAttribute("success", flashMsg);
		return "redirect:/list";
	}

	/* ----- Delete Client ----- */
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash, Locale locale) {

		if (id > 0) {
			Client client = clientService.findOne(id);
			clientService.delete(id);

			flash.addFlashAttribute("success",
					messageSource.getMessage("text.cliente.flash.eliminar.success", null, locale));

		
		}
		return "redirect:/list";
	}

	private boolean hasRole(String role) {

		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) {
			return false;
		}
		Authentication auth = context.getAuthentication();

		if (auth == null) {
			return false;
		}
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();


		return authorities.contains(new SimpleGrantedAuthority(role));

	}

}
