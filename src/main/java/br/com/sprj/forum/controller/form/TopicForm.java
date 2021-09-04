package br.com.sprj.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.sprj.forum.modelo.Course;
import br.com.sprj.forum.modelo.Topic;
import br.com.sprj.forum.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicForm {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String message;

    @NotNull
    @NotEmpty
    private String courseName;

    public Topic converter(CourseRepository courseRepository) {
	Course course = courseRepository.findByName(courseName);
	return new Topic(title, message, course);
    }

}
