package kr.co.wikibook.gallery_jwt_jpa.account;

import kr.co.wikibook.gallery_jwt_jpa.account.model.AccountJoinReq;
import kr.co.wikibook.gallery_jwt_jpa.account.model.AccountLoginReq;
import kr.co.wikibook.gallery_jwt_jpa.account.model.AccountLoginRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    int save(AccountJoinReq p);
    AccountLoginRes findByLoginId(AccountLoginReq p);
    List<String> findAllRolesByMemberId(int memberId);
}
