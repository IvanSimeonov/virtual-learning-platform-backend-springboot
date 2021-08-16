package com.tusofia.virtuallearningplatform.course;

import com.sun.istack.NotNull;
import com.tusofia.virtuallearningplatform.lecture.Lecture;
import lombok.*;

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
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String shortDescription;

    @NotNull
    private String fullDescription;

    @NotNull
    private String requirements;

    @NotNull
    private String language;

    @OneToMany(mappedBy = "course")
    private List<Lecture> lectures;

}
