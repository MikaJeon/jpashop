package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity{

    @Id
    @GeneratedValue
    private  Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private  Category parent;//카테고리 내에서 나뉘는 값들을 셀프로 만들기

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();//카테고리 내에서 나뉘는 값들을 셀프로 만들기

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),//내가 조인하는 것
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")//반대쪽이 조인하는 것
    )
    private List<Item> items = new ArrayList<>();

}
