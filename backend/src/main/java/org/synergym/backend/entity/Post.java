package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Post 엔티티: 게시물 정보를 나타내는 엔티티
 * BaseEntity를 상속받아 생성일, 수정일, 삭제여부 등 공통 필드를 가짐
 */
@Entity
// JPA 엔티티 클래스임을 명시하는 어노테이션
@Getter
// Lombok: 모든 필드의 getter 메소드를 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// Lombok: 파라미터 없는 기본 생성자를 protected 접근 제한으로 생성
// JPA가 프록시 객체를 만들기 위해 필요하며, protected로 설정하여 직접 생성을 제한
@AllArgsConstructor
@Builder
@Table(name = "posts")

public class Post extends BaseEntity {
    @Id
    // 기본 키(Primary Key) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본 키 생성을 데이터베이스에 위임 (MySQL의 AUTO_INCREMENT)
    private Integer id;

    @Column(nullable = false)
    // 컬럼이 NULL을 허용하지 않음
    private String title;

    @Column(nullable = false)
    // 컬럼이 NULL을 허용하지 않고, 최대 길이를 5000자로 제한
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    // 다대일(N:1) 관계 매핑, 지연 로딩 전략 사용 (실제 사용 시점에 로딩)
    @JoinColumn(name = "user_id")
    // 외래 키 컬럼명을 'user_id'로 지정
    private User user;

    private int viewCount;
    // 조회수를 저장하는 필드

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // 일대다(1:N) 관계 매핑, 양방향 관계에서 매핑 주인을 'post'로 지정
    // cascade = CascadeType.ALL: Post 엔티티의 모든 변경(저장, 삭제 등)이 연관된 Comment에도 적용됨
    // orphanRemoval = true: 부모 엔티티와 연관관계가 끊어진 자식 엔티티 자동 삭제 (연관관계 제거 시 댓글도 삭제)
    // fetch = FetchType.LAZY: 지연 로딩 전략 (댓글 목록을 실제 사용 시점에 로딩)
    private List<Comment> comments = new ArrayList<>();
    // Comment 엔티티의 컬렉션, 빈 ArrayList로 초기화하여 NPE 방지


    // 생성/수정 시에는 title과 content만 필요한 경우가 많으므로 간소화된 생성자 추가
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 특정 필드만 업데이트하기 위한 메서드
    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    // 게시물 조회수를 1 증가시키는 메소드
    public void increaseViewCount() {
        this.viewCount++;
    }

    //Post와 Comment는 '양방향 연관관계', 데이터 일관성을 유지, 코드 중복 방지, 객체지향적 설계를 위해
    public void addComment(Comment comment) {
        this.comments.add(comment);
        ((Comment) comment).changePost(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        ((Comment) comment).changePost(null);
    }


}