package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Usuario;
import com.AdopcionMascotas.Service.IUsuarioService;
import com.AdopcionMascotas.Service.UsuariosReportService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioService usuarioService;

    /*Metodo para leer los usuario*/
    @GetMapping("/leerusuarios")
    public String Index(Model model) {
        List<Usuario> listaUsuario = usuarioService.getAllUsuario();
        model.addAttribute("titulo", "Lista de Usuarios");
        model.addAttribute("usuario", listaUsuario);
        return "leerusuarios";
    }

    /*Metodo para leer los usuario*/
    @GetMapping("/leerusuarioU")
    public String leerusuarioU(Model model) {
        List<Usuario> listaUsuario = usuarioService.getAllUsuario();
        model.addAttribute("titulo", "Lista de Usuarios");
        model.addAttribute("usuario", listaUsuario);
        return "leerusuarioU";
    }

    /*Metodo para crear una persona
    este se usa en el boton de login*/
    @GetMapping("/usuarioN")
    public String crearUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "nuevoUsuario";
    }

    @PostMapping("/saveU")
    public String guardarUsuario(@ModelAttribute Usuario U, @RequestParam(name = "file", required = false) MultipartFile imagen, RedirectAttributes flash) {

        if (!imagen.isEmpty()) {
            String ruta = "C://temp//fotosU";
            try {
                byte[] bytes = imagen.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
                Files.write(rutaAbsoluta, bytes);
                String passw = U.getPassword();//recibo la contrasena en texto plano
                String encriptado = passwordEncoder.encode(passw);//convierto la contrasena
                U.setPassword(encriptado);//mando la contrasena ya encriptada
                U.setImagen(imagen.getOriginalFilename());
                U.setActive(1);
                U.setPermisos("USER");
                U.setRoles("USER");

            } catch (Exception e) {

            }

        }

        usuarioService.saveUsuario(U);
        flash.addFlashAttribute("success", "Usuario Creado Con Exito, por favor inicia sesion con tu usuario y clave!");
        return "redirect:/login";
    }

    /*Metodo para crear una persona
    este se usa en el boton de leerusuarios*/
    @GetMapping("/usuarioNI")
    public String crearUsuarioI(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "nuevoUsuario1";
    }

    @PostMapping("/saveI")
    public String guardarUsuarioI(@ModelAttribute Usuario U, @RequestParam(name = "file", required = false) MultipartFile imagen, RedirectAttributes flash) {

        if (!imagen.isEmpty()) {
            String ruta = "C://temp//fotosU";

            try {
                byte[] bytes = imagen.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
                Files.write(rutaAbsoluta, bytes);
                U.setImagen(imagen.getOriginalFilename());
                String passw = U.getPassword();//recibo la contrasena en texto plano
                String encriptado = passwordEncoder.encode(passw);//convierto la contrasena
                U.setPassword(encriptado);//mando la contrasena ya encriptada
                U.setActive(1);
                U.setPermisos("USER");
                U.setRoles("USER");

            } catch (IOException e) {

            }

        }

        usuarioService.saveUsuario(U);
        flash.addFlashAttribute("success", "Usuario Creadoo Editado Con Exito!");
        return "redirect:/leerusuarios";
    }

    /*Metodo para editar un usuario*/
    @GetMapping("/EditarUsuario/{ID}")
    public String EditarUsuario(@PathVariable("ID") Long id, Model model) {
        Usuario U = usuarioService.getUsuarioById(id);
        model.addAttribute("usuario", U);
        return "nuevoUsuario1";
    }

    @RequestMapping("/ConsultarUsuario/{ID}")
    public String ConsultarUsuario(@PathVariable("ID") Long ID, Model model) {
        Usuario U = usuarioService.getUsuarioById(ID);
        model.addAttribute("usuario", U);
        return "perfil";
    }

    /*Metodo para eliminar un usuario*/
    @GetMapping("/EliminarUsuario/{ID}")
    public String EliminarUsuario(@PathVariable("ID") Long id) {
        usuarioService.EliminarUsuario(id);
        return "redirect:/leerusuarios";
    }

    @Autowired
    private UsuariosReportService PReportService;

    @GetMapping(path = "/leerusuarios/Usuarios", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    byte[] getFile() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(new File(PReportService.generateReport()));
            byte[] targetArray = new byte[fis.available()];
            fis.read(targetArray);
            return targetArray;
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
