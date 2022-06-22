package com.fakeleboncoin.front.controller;

import com.fakeleboncoin.client.model.Annonces;
import com.fakeleboncoin.front.services.FakeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

@Controller
public class AnnonceController {

    @Autowired
    private FakeServices fakeService;

    @GetMapping({"/annonces"})
    public String getAnnonce(Model model) throws IOException {

        Call<List<Annonces>> allAnnonces = fakeService.getAnnonceController().getAllAnnonces();
        model.addAttribute("annonces", allAnnonces.execute().body());
        return "annonces";
    }

    @GetMapping({"/form/add/annonce"})
    public String addAnnonces(Model model) throws IOException {
        return "deposer_annonce";
    }

    @PostMapping({"/add/annonce"})
    public String addAnnoncesPost(Annonces annonce,Model model) throws IOException {
        Call<Annonces> createOrUpdate = fakeService.getAnnonceController().createOrUpdate3(annonce);
        createOrUpdate.execute();
        return "annonces";
    }


}
