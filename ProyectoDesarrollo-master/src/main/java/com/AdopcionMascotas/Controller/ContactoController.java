package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Contacto;
import com.AdopcionMascotas.Service.IContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactoController {

    @Autowired
    private IContactoService contactoService;

    @GetMapping("/contactoN")
    public String crearContacto(Model model) {
        model.addAttribute("contactoform", new Contacto());
        return "contacto";
    }

    @PostMapping("/saveContact")
    public String guardarContacto(@ModelAttribute Contacto C, RedirectAttributes flash) {
        contactoService.saveContacto(C);
        flash.addFlashAttribute("success", "Hola tu mensaje ha sido enviado, en breve nos pondremos en contacto!");
        return "redirect:/contactoN";
    }
}
