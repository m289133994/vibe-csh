package csh.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import csh.entity.User;
import csh.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!existsUser("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@csh.com");
            admin.setRole("ROLE_ADMIN");
            userMapper.insert(admin);
            System.out.println("Admin user created");
        }

        if (!existsUser("user")) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setEmail("user@csh.com");
            user.setRole("ROLE_USER");
            userMapper.insert(user);
            System.out.println("Test user created");
        }

        if (existsUser("admin") && existsUser("user")) {
            System.out.println("Test users ready: admin/admin123, user/user123");
        }
    }

    private boolean existsUser(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return userMapper.selectCount(wrapper) > 0;
    }
}
