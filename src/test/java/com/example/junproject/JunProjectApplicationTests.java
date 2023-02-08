package com.example.junproject;

import com.example.junproject.domain.entity.EmployeeEntity;
import com.example.junproject.repository.EmployeeEntityRepository;
import com.example.junproject.security.MyRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class JunProjectApplicationTests {

	@Autowired
	EmployeeEntityRepository erepo;
	@Autowired
	PasswordEncoder pe;


	//@Test
	void contextLoads() {
	}

//	@Test
	void 어드민계정() {
		erepo.save(
				EmployeeEntity.builder()
						.email("admin@test.com")
						.pass(pe.encode("1234"))
						.name("김영준")
						.build()//엔티티생성
						.addRole(MyRole.ADMIN)
							);

	}

}
