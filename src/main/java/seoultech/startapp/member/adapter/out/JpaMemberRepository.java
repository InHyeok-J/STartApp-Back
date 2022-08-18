package seoultech.startapp.member.adapter.out;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import seoultech.startapp.member.domain.MemberRole;
import seoultech.startapp.member.domain.MemberStatus;

interface JpaMemberRepository extends JpaRepository<JpaMember, Long> {

  Optional<JpaMember> findByStudentNo(String studentNo);
  boolean existsByStudentNoAndMemberStatusNot(String studentNo, MemberStatus memberStatus);
  Page<JpaMember> findAllByMemberRoleOrderByIdDesc(MemberRole role, PageRequest pageRequest);
}
