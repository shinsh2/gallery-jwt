package kr.co.wikibook.gallery.member.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.wikibook.gallery.common.util.HashUtils;
import kr.co.wikibook.gallery.member.entity.Member;
import kr.co.wikibook.gallery.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaseMemberService implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 데이터 저장
    @Override
    public void save(String name, String loginId, String loginPw) {
        // 솔트 생성
        String loginPwSalt = HashUtils.generateSalt(16);

        // 입력 패스워드에 솔트를 적용
//        String loginPwSalted = HashUtils.generateHash(loginPw, loginPwSalt);
        String loginPwSalted = passwordEncoder.encode(loginPw);

        // 회원 데이터 저장
        memberRepository.save(new Member(name, loginId, loginPwSalted, loginPwSalt));
    }

    // 회원 데이터 조회
    @Override
    public Member find(String loginId, String loginPw) {
        // 로그인 아이디로 회원 조회
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);
        log.debug("find loginId : {}, memberOptional : {}", loginId, memberOptional);
        // 회원 데이터가 있으면
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            // 솔트 조회
//            String loginPwSalt = memberOptional.get().getLoginPwSalt();

            // 입력 패스워드에 솔트를 적용
//            String loginPwSalted = HashUtils.generateHash(loginPw, loginPwSalt);

            // 저장된 회원 로그인 패스워드와 일치한다면
            log.debug("member.getLoginPw() : {}, passwordEncoder.encode(loginPw) : {}", member.getLoginPw(), passwordEncoder.encode(loginPw));
			if (passwordEncoder.matches(loginPw, member.getLoginPw())) {
				return member;
			}
//            if (member.getLoginPw().equals(passwordEncoder.encode(loginPw))) {
//                return member;
//            }
        }

        return null;
    }

    @Override
    public Member find(String loginId) {
        // 회원 데이터가 있으면 해당 값 리턴
        return memberRepository.findByLoginId(loginId).orElse(null);
    }

	@Override
	public Member find(Integer id) {
        return memberRepository.findById(id).orElse(null);
	}
}