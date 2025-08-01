package kr.co.wikibook.gallery_jwt_jpa.account;

import kr.co.wikibook.gallery_jwt_jpa.account.model.AccountJoinReq;
import kr.co.wikibook.gallery_jwt_jpa.account.model.AccountLoginReq;
import kr.co.wikibook.gallery_jwt_jpa.account.model.AccountLoginRes;
import kr.co.wikibook.gallery_jwt_jpa.config.model.JwtUser;
import kr.co.wikibook.gallery_jwt_jpa.config.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j //log
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;

    public int join(AccountJoinReq req) {
        String hashedPw = BCrypt.hashpw(req.getLoginPw(), BCrypt.gensalt());
        // 암호화가 된 비민번호를 갖는 AccountJoinReq 객체를 만들어주세요.(아이디, 이름 갖고 있고)
            AccountJoinReq changedReq = new AccountJoinReq(
                                        req.getName(), req.getLoginId(), hashedPw
                                        );
            return accountMapper.save(changedReq); // 암호화된
    }

    public AccountLoginRes login(AccountLoginReq req) {
        AccountLoginRes res = accountMapper.findByLoginId(req);

        // 아이디가 없거나 비밀번호가 다르면 비밀번호 체크
        if(res == null || !BCrypt.checkpw(req.getLoginPw(), res.getLoginPw())) {
            return null; // return null; 처리
        }

        // 로그인 성공 !! 로그인 한 사용자의 role가져오기~~
        // 호출
        List<String> roles = accountMapper.findAllRolesByMemberId(res.getId());
        JwtUser jwtUser = new JwtUser(res.getId(),roles);
        res.setJwtUser(jwtUser);
        return res;
    }

}










