package csec.vulnerable.controller;

import java.util.List;

import csec.vulnerable.beans.Tag;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/{id}")
    public Tag getTag(@PathVariable int id) {
        return tagService.findById(id).orElse(null);
    }

    @GetMapping
    public List<Tag> getTags() {
        return tagService.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public Response addTag(@RequestBody Tag tag) {
        return tagService.addTag(tag);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping
    public Response updateTag(@RequestBody Tag tag) {
        return tagService.updateTag(tag);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public Response deleteTag(@PathVariable int id) {
        return tagService.deleteTag(id);
    }
}
