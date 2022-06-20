package com.fakeleboncoin.front.controller;

import com.fakeleboncoin.client.model.Pageable;
import com.fakeleboncoin.client.model.User;
import com.fakeleboncoin.front.services.FakeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private FakeServices fakeService;

    @GetMapping({"/users"})
    public String getUsers(Model model) throws IOException {

        Call<List<User>> allUsers = fakeService.getUserController().getAllUsers();
        model.addAttribute("users", allUsers.execute().body());
        return "users";
    }

}
