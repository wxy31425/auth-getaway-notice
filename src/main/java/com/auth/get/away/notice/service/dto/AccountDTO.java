package com.auth.get.away.notice.service.dto;

import com.auth.get.away.notice.core.TreeNode;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AccountDTO {
    private String accountId;
    private String roleName;
    private List<TreeNode> authorities;
    private String email;
    private String langKey;

}
