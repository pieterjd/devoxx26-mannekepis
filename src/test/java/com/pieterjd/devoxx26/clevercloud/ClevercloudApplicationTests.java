package com.pieterjd.devoxx26.clevercloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class ClevercloudApplicationTests {

    @Autowired
    private PissedOffSelectionRepository pissedOffSelectionRepository;

    @Test
    void contextLoads() {
        PissedOffSelection selection = pissedOffSelectionRepository.save(new PissedOffSelection("a tad"));

        assertNotNull(selection.getId());
        assertEquals("a tad", pissedOffSelectionRepository.findById(selection.getId()).orElseThrow().getMood());
    }
}
