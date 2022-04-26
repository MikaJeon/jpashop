package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //로딩 시점에 딱 하나만 만들어줘야 한다.
        EntityManager em = emf.createEntityManager();
        //db커넥션 얻어서 쿼리 날리고 종료 하는 단위마다 엔티티매니저 만들어줘야 함

        EntityTransaction tx = em.getTransaction();//데이터베이스 트랜잭션 생성
        tx.begin();//tx시작.
        //트랜잭션이란?  데이터베이스의 상태를 변화시키기 해서 수행하는 작업의 단위

        try{
            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");

            em.persist(book);

            tx.commit();//트랜젝션의 처리 과정을 데이터베이스에 반영하기 위해서, 변경된 내용을 모두 영구 저장. + 트랜잭션 종료
        }
        catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
