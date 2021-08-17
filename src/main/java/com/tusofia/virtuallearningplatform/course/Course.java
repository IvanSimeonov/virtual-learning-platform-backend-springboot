package com.tusofia.virtuallearningplatform.course;

import com.tusofia.virtuallearningplatform.category.Category;
import com.tusofia.virtuallearningplatform.lecture.Lecture;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="course_id")
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String shortDescription;

    @NonNull
    private String fullDescription;

    @NonNull
    private String requirements;

    @NonNull
    private String language;

    @OneToMany(mappedBy = "course")
    private List<Lecture> lectures;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
