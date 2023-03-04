package csec.vulnerable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csec.vulnerable.beans.Tag;
import csec.vulnerable.dao.ProductDao;
import csec.vulnerable.dao.TagDao;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.http.Response;


@Service
@Transactional
public class TagService {
	@Autowired
	ProductDao productDao;

    @Autowired
    UserDao userDao;
	
	@Autowired
	TagDao tagDao;
	
	public Tag getTag(int id) {
		return tagDao.findById(id).get();
	}
	
	public List<Tag> getTags() {
		return tagDao.findAll();
	}

	public Response addTag(Tag tag) {
		tagDao.save(tag);
		return new Response(true);
	}

	public Response changeTag(Tag tag) {
        Tag col = tagDao.findById(tag.getId()).get();
        col.setName(tag.getName());
        tagDao.save(col);
        return new Response(true);
	}
	
	public Response deleteTag(int id) {
		if(tagDao.findById(id)!=null) {
            tagDao.deleteById(id);
            return new Response(true);
		}else {
			return new Response(false,"Tag is not found");
		}
	}

}
