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
    private WaffleSelectionRepository waffleSelectionRepository;

    @Test
    void contextLoads() {
        WaffleSelection selection = waffleSelectionRepository.save(new WaffleSelection("Strawberry"));

        assertNotNull(selection.getId());
        assertEquals("Strawberry", waffleSelectionRepository.findById(selection.getId()).orElseThrow().getTopping());
    }
}
