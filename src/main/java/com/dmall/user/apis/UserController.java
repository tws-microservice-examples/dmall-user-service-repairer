package com.dmall.user.apis;

import com.dmall.user.apis.dto.UserDTO;
import com.dmall.user.common.ApiForResponse;
import com.dmall.user.common.HttpFacadeBaseClass;
import com.dmall.user.domain.model.*;
import com.dmall.user.infrastructure.persistent.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends HttpFacadeBaseClass {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ContactRepository contactRepository;


    @Autowired
    public UserController(UserRepository userRepository,
                          RoleRepository roleRepository, ContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.contactRepository = contactRepository;
    }

    @Transactional
    @PostMapping(path="",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiForResponse<UserDTO> createOrder(@RequestBody User user) {
        List<Role> roles = user.getRoles().stream().map(role -> {
            role.setUser(user);
            return role;
        }).collect(Collectors.toList());

        user.setRoles(roles);

        User savedUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO(user);


        ApiForResponse<UserDTO> userApiForResponse = new ApiForResponse<>(savedUser.getId(), userDTO);
        return userApiForResponse;
    }

    @Transactional
    @GetMapping("/{id}")
    public  ApiForResponse<UserDTO> findUserById(@PathVariable("id") final long id) {

        User user = userRepository.findOne(id);
        UserDTO userDTO = new UserDTO(user);
        userDTO.setContacts(String.format("/api/v1/users/%d/contacts", id));
        ApiForResponse<UserDTO> userApiForResponse = new ApiForResponse<>(user.getId(), userDTO);
        return userApiForResponse;
    }

    @Transactional
    @GetMapping("/{id}/contacts")
    public  ApiForResponse<List<Contact> > findContactsByUserId(@PathVariable("id") final long id) {

        List<Contact> contacts = contactRepository.findByUserId(id);
        ApiForResponse<List<Contact> > userApiForResponse = new ApiForResponse<>(id, contacts);
        return userApiForResponse;
    }


    @Transactional
    @PostMapping(path="/{id}/contacts",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiForResponse<Contact> createOrder(@PathVariable("id") final long userId, @RequestBody Contact contact) {

        Contact result = contactRepository.save(contact);

        ApiForResponse<Contact> contactApiForResponse = new ApiForResponse<>(-1L, result);
        return contactApiForResponse;
    }
}

