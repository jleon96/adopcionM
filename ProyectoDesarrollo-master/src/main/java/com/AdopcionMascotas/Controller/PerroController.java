package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Perro;
import com.AdopcionMascotas.Service.IPerroService;
import com.AdopcionMascotas.Service.PerroReportService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

@Controller
public class PerroController {

    @Autowired
    private IPerroService perroService;

    @GetMapping("/leerperros")
    public String Index(Model model) {
        List<Perro> listaPerro = perroService.getAllPerro();
        model.addAttribute("titulo", "Lista de Perros");
        model.addAttribute("perros", listaPerro);
        return "leerperros";
    }

    @GetMapping("/leerperrosU")
    public String leerperrosU(Model model) {
        List<Perro> listaPerro = perroService.getAllPerro();
        model.addAttribute("titulo", "Lista de Perros");
        model.addAttribute("perros", listaPerro);
        return "leerperrosU";
    }

    @GetMapping("crearperroN")
    public String CrearPerro(Model model) {
        model.addAttribute("perros", new Perro());
        return "crearperro";
    }

    @PostMapping("/saveP")
    public String GuardarPerro(@ModelAttribute Perro P, @RequestParam(name = "file", required = false) MultipartFile imagen, RedirectAttributes flash) {
        if (!imagen.isEmpty()) {
            String ruta = "C://temp//fotos";

            try {
                byte[] bytes = imagen.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
                Files.write(rutaAbsoluta, bytes);
                P.setImagen(imagen.getOriginalFilename());

            } catch (Exception e) {

            }

        }
        perroService.savePerro(P);
        flash.addFlashAttribute("success", "¡Perro editado o creado con exito!");
        return "redirect:/leerperros";

    }

    @GetMapping("/EditarPerro/{ID}")
    public String EditarPerro(@PathVariable("ID") Long IDPerro, Model model) {
        Perro P = perroService.getPerroById(IDPerro);
        model.addAttribute("perros", P);
        return "crearperro";
    }

    @GetMapping("/EliminarPerro/{ID}")
    public String EliminarPerro(Perro P, RedirectAttributes flash) {
        perroService.EliminarPerro(P.getID());
        flash.addFlashAttribute("success", "¡Perro eliminado con exito!");
        return "redirect:/leerperros";
    }

    @GetMapping("/EliminarPerroS/{ID}")
    public String EliminarPerroS(Perro P, RedirectAttributes flash) {
        perroService.EliminarPerro(P.getID());
        flash.addFlashAttribute("success", "¡Perro adoptado con exito!");
        return "redirect:/leerperrosU";
    }

    @Autowired
    private PerroReportService PReportService;

    @GetMapping(path = "/leerperros/Perros", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    byte[] getFile() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(new File(PReportService.generateReport()));
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
