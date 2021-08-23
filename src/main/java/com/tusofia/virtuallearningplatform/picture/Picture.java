package com.tusofia.virtuallearningplatform.picture;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "picture_id")
    private Long id;

    private String name;

    private String type;

    @Lob
    private byte[] data;
}
