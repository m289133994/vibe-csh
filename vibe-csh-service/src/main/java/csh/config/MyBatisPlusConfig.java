package csh.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("csh.mapper")
public class MyBatisPlusConfig {
}
