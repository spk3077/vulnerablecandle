package csec.vulnerable.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csec.vulnerable.beans.Tag;
import csec.vulnerable.dao.TagDao;
import csec.vulnerable.http.Response;

@Service
@Transactional
public class TagService {
    @Autowired
    private TagDao tagDao;

    public Optional<Tag> findById(int id) {
        return tagDao.findById(id);
    }

    public Tag findByName(String name) {
        return tagDao.findByName(name);
    }

    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    public Response addTag(Tag tag) {
        tagDao.save(tag);
        return new Response(true, "Tag added successfully.");
    }

    public Response updateTag(Tag tag) {
        if (tagDao.existsById(tag.getId())) {
            tagDao.save(tag);
            return new Response(true, "Tag updated successfully.");
        } else {
            return new Response(false, "Tag not found.");
        }
    }

    public Response deleteTag(int id) {
        if (tagDao.existsById(id)) {
            tagDao.deleteById(id);
            return new Response(true, "Tag deleted successfully.");
        } else {
            return new Response(false, "Tag not found.");
        }
    }
}
