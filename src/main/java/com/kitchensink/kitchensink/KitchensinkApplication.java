package com.kitchensink.kitchensink;

import com.kitchensink.kitchensink.entity.Member;
import com.kitchensink.kitchensink.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class KitchensinkApplication implements CommandLineRunner {

	private final MemberRepository memberRepository;

	public static void main(String[] args) {
		SpringApplication.run(KitchensinkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Long extId = 0L;
		if (!memberRepository.existsByExtId(extId)) {
			memberRepository.save(Member.builder()
					.extId(0L)
					.name("John Smith")
					.email("john.smith@mailinator.com")
					.phoneNumber("2125551212")
					.build());
			log.info("Data created");
		}
	}
}
