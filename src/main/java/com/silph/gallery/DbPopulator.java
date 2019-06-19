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
import com.silph.gallery.model.UsageRequest;
import com.silph.gallery.repositories.AlbumRepository;
import com.silph.gallery.repositories.EmployeeRepository;
import com.silph.gallery.repositories.PhotoRepository;
import com.silph.gallery.repositories.PhotographerRepository;
import com.silph.gallery.repositories.UsageRequestRepository;

@SpringBootApplication
public class DbPopulator implements ApplicationRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private UsageRequestRepository usageRequestRepository;

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
		Photo ph = new Photo("Blonde girl with blue eyes", "https://images.unsplash.com/photo-1560061632-422600917f8a?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=800&h=600&fit=crop&ixid=eyJhcHBfaWQiOjF9");
		Photo ph2 = new Photo("Architeture with cables", "https://images.unsplash.com/photo-1560223715-5e99c47b5881?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=800&h=600&fit=crop&ixid=eyJhcHBfaWQiOjF9");
		Photo ph3 = new Photo("Neon lights", "https://images.unsplash.com/photo-1559050772-2ee6d74b5894?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=800&h=600&fit=crop&ixid=eyJhcHBfaWQiOjF9");
		Photo ph4 = new Photo("Sunrise panorama with lake and trees", "https://images.unsplash.com/photo-1559626627-cb31b201e27f?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=800&h=600&fit=crop&ixid=eyJhcHBfaWQiOjF9");
		Photo ph5 = new Photo("Hand to the sky", "https://images.unsplash.com/photo-1559492312-5ded812272e8?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=800&h=600&fit=crop&ixid=eyJhcHBfaWQiOjF9");
		Photo ph6 = new Photo("Shore monolith", "https://images.unsplash.com/photo-1560391243-5caac66e0134?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=800&h=600&fit=crop&ixid=eyJhcHBfaWQiOjF9");

		Album a = new Album("Takes");
		Album a2 = new Album("Light games");
		Album a3 = new Album("Heart shoots");
		Album a4 = new Album("Oldies");
		a.addPhoto(ph);
		a.addPhoto(ph2);
		a2.addPhoto(ph3);
		a3.addPhoto(ph4);
		a4.addPhoto(ph5);
		a4.addPhoto(ph6);
		ph.setAlbum(a);
		ph2.setAlbum(a);
		ph3.setAlbum(a2);
		ph4.setAlbum(a3);
		ph5.setAlbum(a4);
		ph6.setAlbum(a4);
		Photographer p = new Photographer("Giorgio", "Vanni");
		Photographer p2 = new Photographer("Elisa", "Mususca");
		Photographer p3 = new Photographer("Vincenzo", "Giorgi");
		ph.setPhotographer(p);
		ph2.setPhotographer(p);
		ph3.setPhotographer(p2);
		ph4.setPhotographer(p2);
		ph5.setPhotographer(p3);
		ph6.setPhotographer(p3);
		a.setPhotographer(p);
		a2.setPhotographer(p2);
		a3.setPhotographer(p2);
		a4.setPhotographer(p3);

		UsageRequest ur = new UsageRequest("Giovanni", "Rana", "g.r@giovannirana.it");
		ur.getChosenPhotos().add(ph2);
		ur.getChosenPhotos().add(ph5);
		
		photograperRepository.save(p);
		photograperRepository.save(p2);
		photograperRepository.save(p3);
		albumRepository.save(a);
		albumRepository.save(a2);
		albumRepository.save(a3);
		albumRepository.save(a4);
		photoRepository.save(ph);
		photoRepository.save(ph2);
		photoRepository.save(ph3);
		photoRepository.save(ph4);
		photoRepository.save(ph5);
		photoRepository.save(ph6);
		employeeRepository.save(e);
		usageRequestRepository.save(ur);
	}
	
	
}
