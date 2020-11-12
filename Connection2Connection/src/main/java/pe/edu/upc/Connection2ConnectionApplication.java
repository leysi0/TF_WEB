package pe.edu.upc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Connection2ConnectionApplication implements CommandLineRunner{

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		 return new BCryptPasswordEncoder();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Connection2ConnectionApplication.class, args);
	}

	public void run(String... args) throws Exception{

		String contrase = "admin";

        for (int i = 0; i < 1; i++) {
            String bcryptPassword = passwordEncoder.encode(contrase);
            System.out.println(bcryptPassword);
        }
	}
}
