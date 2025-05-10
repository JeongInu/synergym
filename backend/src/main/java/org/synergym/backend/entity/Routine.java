package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.synergym.backend.dto.RoutineDTO;

@Entity
@Table(name = "routines")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String routineGoal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 90fa1dc (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 9f3ef93 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
    private Character useYN;

    public void changeName(String name) {
        this.name = name;
    }

    public void changeRoutineGoal(String routineGoal) {
        this.routineGoal = routineGoal;
=======
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 90fa1dc (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> 9f3ef93 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
    private Boolean useYn;
    private Boolean deleteYn;

    public void updateFromDTO(RoutineDTO dto, User user) {
        this.name = dto.getName();
        this.routineGoal = dto.getRoutineGoal();
        this.user = user;
        this.useYn = dto.getUseYn();
        this.deleteYn = dto.getDeleteYn();
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
    }

    public void changeUseYN(Character useYN) {
        this.useYN = useYN;
    }
}
