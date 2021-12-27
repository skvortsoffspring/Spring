package com.example.skvortsoff.service;

import com.example.skvortsoff.controller.PurchasedController;
import com.example.skvortsoff.dto.PurchasedDto;
import com.example.skvortsoff.entity.Purchased;
import com.example.skvortsoff.entity.User;
import com.example.skvortsoff.exeption.ExceptionNotFoundUser;
import com.example.skvortsoff.repository.PurchasedRepository;
import com.example.skvortsoff.repository.UserRepository;
import com.example.skvortsoff.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PurchasedService {
    private final PurchasedRepository purchasedRepository;
    private final UserRepository userRepository;

    @Autowired
    public PurchasedService(PurchasedRepository purchasedRepository, UserRepository userRepository) {
        this.purchasedRepository = purchasedRepository;
        this.userRepository = userRepository;
    }

    public Iterable<PurchasedDto> GetPurchased(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User name not found"));
        var a = Mapper.mapAll(purchasedRepository.findByUserId(user.getId()), PurchasedDto.class);
        return a;
    }
}
