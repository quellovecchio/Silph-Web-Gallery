package com.silph.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.silph.gallery.model.Album;
import com.silph.gallery.model.Employee;
import com.silph.gallery.model.Photo;
import com.silph.gallery.model.Photographer;
import com.silph.gallery.repositories.AlbumRepository;
import com.silph.gallery.repositories.EmployeeRepository;
import com.silph.gallery.repositories.PhotoRepository;
import com.silph.gallery.repositories.PhotographerRepository;

@SpringBootApplication
public class DbPopulator implements ApplicationRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private PhotographerRepository photograperRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		deleteAll();
		addAll();
	}
	
	private void deleteAll() {
		employeeRepository.deleteAll();
	}

	private void addAll() {
		Employee e = new Employee("Luca", "Del Vecchio", "luc.delvecchio@silph.com", null, "EMPLOYEE");
		String guestPassword = new BCryptPasswordEncoder().encode("password");
        e.setPwd(guestPassword);
		Photo ph = new Photo("Allora come va", "https://source.unsplash.com/pWkk7iiCoDM/400x300");
		Photo ph2 = new Photo("Tutto bene grazie", "https://images.unsplash.com/photo-1560329649-c952ee843f92?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80");
		Album a = new Album("Prove");
		a.addPhoto(ph);
		a.addPhoto(ph2);
		ph.setAlbum(a);
		ph2.setAlbum(a);
		Photographer p = new Photographer("Giorgio", "Vanni");
		ph.setPhotographer(p);
		ph2.setPhotographer(p);
		a.setPhotographer(p);
		
		photograperRepository.save(p);
		albumRepository.save(a);
		photoRepository.save(ph);
		photoRepository.save(ph2);
		employeeRepository.save(e);
	}
	
	
}
