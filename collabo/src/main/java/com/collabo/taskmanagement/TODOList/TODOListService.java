package com.collabo.taskmanagement.TODOList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TODOListService {
    @Autowired
    TODOListRepository todoListRepository;
}
