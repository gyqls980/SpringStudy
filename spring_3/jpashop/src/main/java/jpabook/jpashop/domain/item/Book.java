package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("B") //한 테이블에 때려박고 구분 값
public class Book extends Item{

    private String author;
    private String isbn;
}
