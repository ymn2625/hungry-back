package org.example.hungryback.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hungryback.repository.resultSet.GetPartyMemberListResultSet;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartyMemberListItem {
    private String userEmail;
    private String userNickname;
    private String userProfileImg;
    private Integer memberRole;

    public PartyMemberListItem (GetPartyMemberListResultSet resultSet) {
        this.userEmail = resultSet.getUserEmail();
        this.userNickname = resultSet.getUserNickname();
        this.userProfileImg = resultSet.getUserProfileImg();
        this.memberRole = resultSet.getMemberRole();
    }

    public static List<PartyMemberListItem> copyList(List<GetPartyMemberListResultSet> resultSets) {
        List<PartyMemberListItem> list = new ArrayList<>();
        for(GetPartyMemberListResultSet resultSet : resultSets) {
            PartyMemberListItem partyMemberListItem = new PartyMemberListItem(resultSet);
            list.add(partyMemberListItem);
        }
        return list;
    }
}
