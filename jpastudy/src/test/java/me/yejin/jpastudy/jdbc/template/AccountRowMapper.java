package me.yejin.jpastudy.jdbc.template;

import me.yejin.jpastudy.jdbc.vo.AccountVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<AccountVO> {

    @Override
    public AccountVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        var account = new AccountVO();
        account.setId(rs.getInt("ID"));
        account.setUsername(rs.getString("USERNAME"));
        account.setPassword(rs.getString("PASSWORD"));
        return account;
    }
}
