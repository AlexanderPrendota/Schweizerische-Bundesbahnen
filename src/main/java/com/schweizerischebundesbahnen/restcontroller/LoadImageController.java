package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.exceptions.StorageFileNotFoundException;
import com.schweizerischebundesbahnen.model.User;
import org.springframework.core.io.Resource;
import com.schweizerischebundesbahnen.repository.UserRepository;
import com.schweizerischebundesbahnen.service.api.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;

/**
 * Created by aleksandrprendota on 27.03.17.
 */

@RestController
@RequestMapping(value = "/images")
public class LoadImageController {

    private final StorageService storageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public LoadImageController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     *
     * @param filename - name of file which one will be upload
     * @return file form local dir from storage properties
     */
    @GetMapping("/{filename:.+}")
    @ResponseBody
    public Resource serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return file;
    }

    /**
     *
     * @param file — get the file for writting to database
     *             name of file will be "unixtime.expansion"
     * @return rediretc to account page. The params on this page will be update
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file,
                                         RedirectAttributes redirectAttributes) {
        Long time = Calendar.getInstance().getTimeInMillis();
        if (file.getOriginalFilename().equals("")){
            return new ModelAndView("redirect:" + "account");
        }
        String extension = file.getOriginalFilename().split("\\.")[1];
        String fileName = time.toString() + "." + extension;
        storageService.store(file, fileName);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser;
        currentUser = userRepository.findByEmail(auth.getName());
        currentUser.setImagepath("images/" + fileName);
        userRepository.save(currentUser);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return new ModelAndView("redirect:" + "account");
    }


    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
