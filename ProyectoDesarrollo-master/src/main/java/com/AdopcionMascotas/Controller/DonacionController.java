package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Donacion;
import com.AdopcionMascotas.Service.DonacionReportService;
import com.AdopcionMascotas.Service.IDonacionService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DonacionController {

    @Autowired
    private IDonacionService donacionService;

    @GetMapping("leerdonaciones")
    public String Index(Model model) {
        List<Donacion> listaDonacion = donacionService.getAllDonacion();
        model.addAttribute("titulo", "Lista de Donaciones");
        model.addAttribute("donacion", listaDonacion);
        return "leerdonaciones";
    }

    @GetMapping("donacionesN")
    public String CrearDonacion(Model model) {
        model.addAttribute("donacion", new Donacion());
        return "donaciones";
    }

    @PostMapping("/saveD")
    public String GuardarDonacion(@ModelAttribute Donacion D,RedirectAttributes flash) {
        donacionService.saveDonacion(D);
        flash.addFlashAttribute("success", "¡Manos que dan nunca estaran vacias,gracias!");

        return "redirect:/donacionesN";
    }

    @Autowired
    private DonacionReportService DReportService;

    @GetMapping(path = "/leerdonaciones/Donaciones", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    byte[] getFile() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(new File(DReportService.generateReport()));
            byte[] targetArray = new byte[fis.available()];
            fis.read(targetArray);
            return targetArray;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
