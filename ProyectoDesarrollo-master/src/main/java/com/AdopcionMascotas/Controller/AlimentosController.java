package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Alimentos;
import com.AdopcionMascotas.Service.AlimentoReportService;
import com.AdopcionMascotas.Service.IAlimentosService;
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
public class AlimentosController {

    @Autowired
    private IAlimentosService alimentosService;

    @GetMapping("/leerAlimentos")
    public String Index(Model model) {
        List<Alimentos> listaAlimentos = alimentosService.getAllAlimentos();
        model.addAttribute("titulo", "Lista de Alimentos");
        model.addAttribute("alimentos", listaAlimentos);
        return "leerAlimentos";
    }

    @GetMapping("/leerAlimentosU")
    public String leerAlimentosU(Model model) {
        List<Alimentos> listaAlimentos = alimentosService.getAllAlimentos();
        model.addAttribute("titulo", "Lista de Alimentos");
        model.addAttribute("alimentos", listaAlimentos);
        return "leerAlimentosU";
    }

    @GetMapping("agregarAlimentoN")
    public String AgregarAlimento(Model model) {
        model.addAttribute("alimentos", new Alimentos());
        return "agregarAlimento";
    }

    @PostMapping("/saveA")
    public String GuardarAlimentos(@ModelAttribute Alimentos A, @RequestParam(name = "file", required = false) MultipartFile imagen, RedirectAttributes flash) {
        if (!imagen.isEmpty()) {
            String ruta = "C://temp//fotos";
            try {
                byte[] bytes = imagen.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
                Files.write(rutaAbsoluta, bytes);
                A.setImagen(imagen.getOriginalFilename());
            } catch (Exception e) {
            }
        }
        alimentosService.saveAlimentos(A);
        flash.addFlashAttribute("success", "¡Alimento Creado o Editado con Exito! ");
        return "redirect:/leerAlimentos";
    }

    @GetMapping("/EditarAlimento/{id}")
    public String EditarAlimento(@PathVariable("id") Long id, Model model) {
        Alimentos A = alimentosService.getAlimentosById(id);
        model.addAttribute("alimentos", A);
        return "agregarAlimento";
    }

    @GetMapping("/EliminarAlimento/{id}")
    public String EliminarAlimento(Alimentos A, RedirectAttributes flash) {
        alimentosService.EliminarAlimentos(A.getId());
        flash.addFlashAttribute("info", "¡Alimento comprado con exito!");
        return "redirect:/leerAlimentosU";
    }
    
        @GetMapping("/EliminarAlimentoA/{id}")
    public String EliminarAlimentoA(Alimentos A, RedirectAttributes flash) {
        alimentosService.EliminarAlimentos(A.getId());
        flash.addFlashAttribute("info", "¡Alimento comprado con exito!");
        return "redirect:/leerAlimentos";
    }

    @Autowired
    private AlimentoReportService AReportService;

    @GetMapping(path = "/leerAlimentos/Alimentos", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    byte[] getFile() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(new File(AReportService.generateReport()));
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
