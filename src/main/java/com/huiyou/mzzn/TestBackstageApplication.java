package com.huiyou.mzzn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.huiyou.mzzn.mapper")
public class TestBackstageApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestBackstageApplication.class, args);
	}
}