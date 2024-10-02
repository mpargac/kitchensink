package com.kitchensink.kitchensink.data;

import com.kitchensink.kitchensink.entity.Member;
import com.kitchensink.kitchensink.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {
        memberRepository.save(new Member("0", "John Smith", "john.smith@mailinator.com", "2125551212"));
        log.info("Data created");
    }
}
