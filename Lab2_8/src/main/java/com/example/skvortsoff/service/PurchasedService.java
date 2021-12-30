package com.example.skvortsoff.service;

import com.example.skvortsoff.dto.CourseIdDto;
import com.example.skvortsoff.dto.PurchasedDto;
import com.example.skvortsoff.entity.Purchased;
import com.example.skvortsoff.entity.User;
import com.example.skvortsoff.repository.CourseRepository;
import com.example.skvortsoff.repository.PurchasedRepository;
import com.example.skvortsoff.repository.UserRepository;
import com.example.skvortsoff.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PurchasedService {
    private final PurchasedRepository purchasedRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public PurchasedService(PurchasedRepository purchasedRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.purchasedRepository = purchasedRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public Iterable<PurchasedDto> GetPurchased(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User name not found"));
        return Mapper.mapAll(purchasedRepository.findByUserId(user.getId()), PurchasedDto.class);
    }

    public void BuyPurchased(String email, CourseIdDto courseIDDto){
        var user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));;
        var course = courseRepository.findById(courseIDDto.getId()).orElse(null);
        var purchased = new Purchased();
        purchased.setActive(false);
        purchased.setUser(user);
        purchased.setCourse(course);
        purchased.setKey("xxxxx-xxxxx-xxxxx-xxxxx");

        purchasedRepository.save(purchased);
    }
}
