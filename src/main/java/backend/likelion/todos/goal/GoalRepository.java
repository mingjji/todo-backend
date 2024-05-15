package backend.likelion.todos.goal;

import java.util.List;

import backend.likelion.todos.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Member, Long> {
    // TODO [10단계] JpaRepository를 상속받습니다.

    // TODO [10단계] 아래는 기존 코드입니다. 컴파일 오류가 발생하고 있는데, 이를 해결하세요.
    List<Goal> findAllByMemberId(Long memberId);
}
