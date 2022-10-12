package com.memewiki.core.domain.Heart.service;

import com.memewiki.core.domain.Heart.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class HeartService {
    private final HeartRepository heartRepository;

}
