package csec.vulnerable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csec.vulnerable.beans.Collection;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.CollectionService;

@RestController()
@RequestMapping("/collections")
public class CollectionController {
	@Autowired
	CollectionService collectionService;
	
	@GetMapping("/{id}")
	public Collection getCollection(@PathVariable int id) {
		return collectionService.getCollection(id);
	}
	
	@GetMapping
	public List<Collection> getCollections(){
		return collectionService.getCollections();
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@PostMapping
	public Response addCollection(@RequestBody Collection collection){
		return collectionService.addCollection(collection);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@PutMapping
	public Response changeCollection(@RequestBody Collection collection) {
		return collectionService.changeCollection(collection);
	}
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public Response deleteCollection(@PathVariable int id) {
		return collectionService.deleteCollection(id);
	}
	
}

