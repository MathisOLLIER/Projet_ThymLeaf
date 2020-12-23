package com.myaudiolibrary.web.Controller;

import com.myaudiolibrary.web.Repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(method = RequestMethod.GET, value = "")
    public String index(final ModelMap model){
        return "accueil";
    }

}