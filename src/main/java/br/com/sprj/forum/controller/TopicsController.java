package br.com.sprj.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sprj.forum.controller.dto.TopicDetailDto;
import br.com.sprj.forum.controller.dto.TopicDto;
import br.com.sprj.forum.controller.form.TopicForm;
import br.com.sprj.forum.modelo.Topic;
import br.com.sprj.forum.repository.CourseRepository;
import br.com.sprj.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<TopicDto> list(String courseName) {
	if (courseName == null) {
	    List<Topic> topics = topicRepository.findAll();
	    return TopicDto.converter(topics);
	} else {
	    List<Topic> topics = topicRepository.findByCourseName(courseName);
	    return TopicDto.converter(topics);
	}
    }

    @PostMapping
    public ResponseEntity<TopicDto> add(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriBuilder) {
	Topic topic = topicForm.converter(courseRepository);
	topicRepository.save(topic);
	URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
	return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @GetMapping("/{id}")
    public TopicDetailDto detail(@PathVariable("id") Long id) {
	Topic topic = topicRepository.getById(id);
	return new TopicDetailDto(topic);
    }

}