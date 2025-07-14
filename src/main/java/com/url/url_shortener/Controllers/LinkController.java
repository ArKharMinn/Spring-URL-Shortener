package com.url.url_shortener.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.url.url_shortener.Models.Link;
import com.url.url_shortener.Repos.LinkRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class LinkController {
    @Autowired
    private LinkRepo linkRepo;

    @GetMapping("/")
    public String index(Model model) {
        List<Link> links = linkRepo.findAll();
        model.addAttribute("links", links);
        return "index";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Link link) {
        String shortCode = UUID.randomUUID().toString().substring(0, 8);
        link.setShortUrl(shortCode);
        linkRepo.save(link);
        return "redirect:/";
    }

    @GetMapping("/{shortUrl}")
    public String shorten(@PathVariable String shortUrl) {
        Link link = linkRepo.findByShortUrl(shortUrl);
        link.setClick(link.getClick() + 1);
        linkRepo.save(link);
        String originalUrl = link.getOriginalUrl();
        return "redirect:" + originalUrl;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        linkRepo.deleteById(id);
        return "redirect:/";
    }

}
