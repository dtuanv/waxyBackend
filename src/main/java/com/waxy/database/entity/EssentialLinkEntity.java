package com.waxy.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "essential_link")
public class EssentialLinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String link;
    private String description;
    private String caption;
    private String icon;
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private EssentialLinkEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<EssentialLinkEntity> children = new ArrayList<>();


}
