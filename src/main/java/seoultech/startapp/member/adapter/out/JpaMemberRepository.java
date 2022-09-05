package seoultech.startapp.member.adapter.out;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import seoultech.startapp.member.domain.MemberRole;
import seoultech.startapp.member.domain.MemberStatus;

public interface JpaMemberRepository extends JpaRepository<JpaMember, Long> {

  Optional<JpaMember> findByStudentNo(String studentNo);

  boolean existsByStudentNo(String studentNo);

  Page<JpaMember> findAllByMemberRoleAndMemberStatusNotOrderByIdAsc(MemberRole role,
       MemberStatus memberStatus, PageRequest pageRequest);

  Page<JpaMember> findAllByMemberRoleAndMemberStatusOrderByIdAsc(MemberRole role,
       MemberStatus memberStatus,PageRequest pageRequest);

  Boolean existsByPhoneNo(String phoneNo);
}
