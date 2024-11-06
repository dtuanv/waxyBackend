package com.waxy.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EssentialLink {
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
    private EssentialLink parent;

    @OneToMany(mappedBy = "parent")
    private List<EssentialLink> children;

    @OneToMany(mappedBy = "essentialLink", cascade = CascadeType.ALL)
    private List<EssentialLinkGroupRelation> essentialLinkGroupRelations;




}
