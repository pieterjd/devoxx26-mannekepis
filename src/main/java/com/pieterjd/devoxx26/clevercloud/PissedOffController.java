package com.pieterjd.devoxx26.clevercloud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PissedOffController {

    private static final List<String> MOODS = List.of(
            "not pissed off",
            "a tad",
            "hugely"
    );

    private final PissedOffSelectionRepository pissedOffSelectionRepository;

    public PissedOffController(PissedOffSelectionRepository pissedOffSelectionRepository) {
        this.pissedOffSelectionRepository = pissedOffSelectionRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        String currentMood = pissedOffSelectionRepository.findTopByOrderByIdDesc()
                .map(PissedOffSelection::getMood)
                .orElse("a tad");
        populateModel(model, currentMood);
        return "index";
    }

    @PostMapping("/mood")
    public String mood(@RequestParam("mood") String mood, Model model) {
        if (MOODS.contains(mood)) {
            pissedOffSelectionRepository.save(new PissedOffSelection(mood));
        }
        populateModel(model, mood);
        return "index";
    }

    private void populateModel(Model model, String selectedMood) {
        model.addAttribute("moods", MOODS);
        model.addAttribute("selectedMood", selectedMood);

        long total = pissedOffSelectionRepository.count();
        List<Map<String, Object>> histogram = new ArrayList<>();

        for (String mood : MOODS) {
            long count = pissedOffSelectionRepository.countByMood(mood);
            double percentage = total == 0 ? 0.0 : ((count * 100.0) / total);
            Map<String, Object> entry = new HashMap<>();
            entry.put("label", mood);
            entry.put("count", count);
            entry.put("percentage", percentage);
            histogram.add(entry);
        }

        model.addAttribute("histogram", histogram);
    }
}
