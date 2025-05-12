package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comments")
public class Comment extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        // 게시글 연관 관계
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_id", nullable = false)
        private Post post;

        // 작성자 연관 관계
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @Column
        private String content;

        // soft delete 처리
        public void markAsDeleted() {
            this.softDelete();  // BaseEntity에 있는 삭제 처리 메서드
        }

        // 내용 수정 메서드
        public void changeContent(String content) {
            this.content = content;
        }

        void changePost(Post post) {
                this.post = post;
        }



}
