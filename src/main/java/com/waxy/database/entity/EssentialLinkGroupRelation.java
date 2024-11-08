package com.waxy.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EssentialLinkGroupRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "essential_link_id")
    private EssentialLinkEntity essentialLink;

    @ManyToOne
    @JoinColumn(name = "essential_link_group_id")
    private EssentialLinkGroup essentialLinkGroup;

}
