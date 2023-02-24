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

import csec.vulnerable.beans.Tag;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.TagService;

@RestController()
@RequestMapping("/tags")
public class TagController {
	@Autowired
	TagService tagService;
	
	@GetMapping("/{id}")
	public Tag getTag(@PathVariable int id) {
		return tagService.getTag(id);
	}
	
	@GetMapping
	public List<Tag> getTags(){
		return tagService.getTags();
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@PostMapping
	public Response addTag(@RequestBody Tag tag){
		return tagService.addTag(tag);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@PutMapping
	public Response changeTag(@RequestBody Tag tag) {
		return tagService.changeTag(tag);
	}
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public Response deleteTag(@PathVariable int id) {
		return tagService.deleteTag(id);
	}
	
}

