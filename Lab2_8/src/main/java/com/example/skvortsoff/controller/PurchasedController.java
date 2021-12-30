package com.example.skvortsoff.controller;

import com.example.skvortsoff.dto.CourseIdDto;
import com.example.skvortsoff.dto.PurchasedDto;
import com.example.skvortsoff.security.JwtTokenProvider;
import com.example.skvortsoff.service.PurchasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/purchased/**")
public class PurchasedController {

    private final PurchasedService purchasedService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public PurchasedController(PurchasedService purchasedService, JwtTokenProvider jwtTokenProvider) {
        this.purchasedService = purchasedService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("get")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:read')")
    public Iterable<PurchasedDto> GetPurchased(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);
        String email = jwtTokenProvider.getUserName(token);
        return purchasedService.GetPurchased(email);
    }

    @PostMapping("buy")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<?> BuyPurchased(HttpServletRequest request, @RequestBody CourseIdDto id){
        String token = jwtTokenProvider.resolveToken(request);
        String email = jwtTokenProvider.getUserName(token);
        purchasedService.BuyPurchased(email, id);
        return ResponseEntity.ok("Purchased buy success");
    }


}
