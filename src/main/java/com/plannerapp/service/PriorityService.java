package com.plannerapp.service;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.enums.PriorityName;
import com.plannerapp.repo.PriorityRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @PostConstruct
    public void initPriorities() {
        if (priorityRepository.count() == 0) {
            Arrays.stream(PriorityName.values())
                    .forEach(priorityName -> {
                        Priority priority = new Priority();
                        priority.setName(priorityName);
                        switch (priorityName) {
                            case URGENT:
                                priority.setDescription("An urgent problem that blocks the system use until the issue is resolved.");
                                break;
                            case IMPORTANT:
                                priority.setDescription("A core functionality that your product is explicitly supposed to perform is compromised.");
                                break;
                            case LOW:
                                priority.setDescription("Should be fixed if time permits but can be postponed.");
                                break;
                        }
                        priorityRepository.save(priority);
                    });
        }
    }

    public Priority findByName(PriorityName name) {
        return priorityRepository.findByName(name);
    }
}
