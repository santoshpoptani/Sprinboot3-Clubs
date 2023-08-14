package com.example.springboot.controller;

import com.example.springboot.dto.ClubDto;
import com.example.springboot.models.Club;
import com.example.springboot.services.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model){
        List<ClubDto> clubs = clubService.findAllClub();
        model.addAttribute("club",clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model){
        Club club = new Club();
        model.addAttribute("club",club);
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub( @Valid @ModelAttribute("club") ClubDto clubdto,BindingResult result){

        if(result.hasErrors())
            return "clubs-create";
        clubService.saveClub(clubdto);
        return "redirect:/clubs";

    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId, Model model){
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club",clubDto);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClubForm(@PathVariable("clubId") long clubId,
                                 @Valid @ModelAttribute("club") ClubDto clubDto,
                                 BindingResult result){

        if(result.hasErrors())
            return "clubs-edit";
        clubDto.setId(clubId);
        clubService.updateClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId,Model model){
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club",clubDto);
        return "clubs-detail";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClubs(@PathVariable("clubId") long clubId){
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query,Model model){
        List<ClubDto> clubDtos = clubService.searchClubs(query);
        model.addAttribute("club",clubDtos);
        return "clubs-list";
    }
}
