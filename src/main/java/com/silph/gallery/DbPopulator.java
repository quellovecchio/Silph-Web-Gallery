package com.silph.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		Employee e = new Employee("Luca", "Del Vecchio", "luc.delvecchio@silph.com", "password", "EMPLOYEE");
		Photographer p = new Photographer("Giorgio", "Vanni");
		Album a = new Album("Prove");
		Photo ph = new Photo();
		p.addAlbum(a);
		a.addPhoto(ph);
		ph.setPhotographer(p);
		photograperRepository.save(p);
		photoRepository.save(ph);
		albumRepository.save(a);
		employeeRepository.save(e);
	}
	
	
}
