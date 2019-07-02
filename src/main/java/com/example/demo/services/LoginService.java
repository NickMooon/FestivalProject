package com.example.demo.services;

import com.example.demo.model.Login;
import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.repositories.EventsRepository;
import com.example.demo.repositories.LoginRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.valid.AppUserForm;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class LoginService {

    private EventsRepository eventsRepository;
    private UserRepository userRepository;
    private LoginRepository loginRepository;
    private RoleRepository roleRepository;

    public LoginService(EventsRepository eventsRepository, UserRepository userRepository, LoginRepository loginRepository, RoleRepository roleRepository) {
        super();
        this.eventsRepository = eventsRepository;
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.roleRepository = roleRepository;
    }

    public String getUserInfo() {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (obj instanceof UserDetails) {
                UserDetails userdetails = (UserDetails) obj;
                Login login = loginRepository.findByUsername(userdetails.getUsername());
                Users part = userRepository.findByUser_id(login.getUser_id());
                String greeting = "Hi, " + part.getFirstName() + " (" + userdetails.getAuthorities().toString() + "), here are the events that you go to:";
                return greeting;
            } else
                return obj.toString();
        }

        else
            return "Hello. It's Events app. Please login.";
    }

    public Users createNewUser(AppUserForm form) {

        // Get Role
        Role role = roleRepository.findByRole("ROLE_USER");

        // Create Login
        Login login = new Login();
        login.setUsername(form.getUserName());
        login.setPassword(form.getPassword());
        login.getRoles().add(role);

        // Create user
        Users part = new Users();
        part.setAge(form.getAge());
        part.setEmail(form.getEmail());
        part.setFirstName(form.getFirstName());
        part.setLastName(form.getLastName());
        part.setPhone(form.getPhone());

        Login savedLogin = loginRepository.save(login);
        part.setUser_id(savedLogin.getUser_id());
        return userRepository.save(part);
    }

    public void makeAdmin(String id) {
        Users part = userRepository.findById(Long.parseLong(id)).get();
        Login login = loginRepository.findById(part.getUser_id()).get();
        Role role = roleRepository.findByRole("ROLE_ADMIN");
        login.getRoles().add(role);

        loginRepository.save(login);

    }

}
