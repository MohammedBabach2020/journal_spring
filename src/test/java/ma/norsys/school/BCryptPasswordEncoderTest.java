package ma.norsys.school;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class BCryptPasswordEncoderTest {

    @Test
    void generatePassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password[] = {"123456"};
        for (String s : password) log.debug(passwordEncoder.encode(s));
    }

}