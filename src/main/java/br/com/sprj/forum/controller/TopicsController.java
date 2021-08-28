package br.com.sprj.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sprj.forum.controller.dto.TopicDto;
import br.com.sprj.forum.modelo.Course;
import br.com.sprj.forum.modelo.Topic;

@RestController
public class TopicsController {

    @RequestMapping("/topics")
    public List<TopicDto> list() {

	Topic topic = new Topic("Message Title", "Message", new Course("Course", "Category"));

	return TopicDto.converter(Arrays.asList(topic, topic, topic));

    }

}