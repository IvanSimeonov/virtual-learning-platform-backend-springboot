package com.tusofia.virtuallearningplatform.lecture;

import org.springframework.lang.NonNull;
import com.tusofia.virtuallearningplatform.course.Course;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lecture_id")
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

}
