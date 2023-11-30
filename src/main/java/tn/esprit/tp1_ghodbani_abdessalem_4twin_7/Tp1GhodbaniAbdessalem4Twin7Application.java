package tn.esprit.tp1_ghodbani_abdessalem_4twin_7;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Admin;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Role;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.User;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IAdminRepository;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IUserRepository;

@SpringBootApplication
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class Tp1GhodbaniAbdessalem4Twin7Application {

	private final IUserRepository userRepository;
	private final IAdminRepository adminRepository;
	public static void main(String[] args) {
		SpringApplication.run(Tp1GhodbaniAbdessalem4Twin7Application.class, args);
	}
	public void run(String... args) {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if (adminAccount == null) {
			Admin admin = new Admin();
			admin.setEmail("admin@gmail.com");
			admin.setNom("admin");
			admin.setPrenom("admin");
			admin.setRole(Role.ADMIN);
			admin.setImage("ffffffff");
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			adminRepository.save(admin);
		}
	}
}
