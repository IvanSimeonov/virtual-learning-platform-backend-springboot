package com.tusofia.virtuallearningplatform.category;

import org.springframework.lang.Nullable;
import org.springframework.lang.NonNull;
import com.tusofia.virtuallearningplatform.course.Course;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    @NonNull
    private String title;

    @Nullable
    @ManyToOne
    private Category parent;

    @Nullable
    @OneToMany(mappedBy = "parent")
    private List<Category> children;

    @Nullable
    @OneToMany(mappedBy = "category")
    private List<Course> courses;
}
