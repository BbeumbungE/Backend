package com.siliconvalley;

import com.siliconvalley.domain.post.service.PostingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SiliconValleyApplicationTests {

	@Autowired
	PostingService postingService;

	@Test
	void contextLoads() {
	}

}
