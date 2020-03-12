package com.justindodson.familybucks.app.service.user;

import com.justindodson.familybucks.app.auth.User;
import com.justindodson.familybucks.app.model.entity.user.Family;
import com.justindodson.familybucks.app.model.entity.user.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyMemberServiceImpl implements FamilyMemberService {
    private final FamilyService familyService;
    private final ParentService parentService;

    @Autowired
    public FamilyMemberServiceImpl(FamilyService familyService, ParentService parentService) {
        this.familyService = familyService;
        this.parentService = parentService;
    }

    @Override
    public void addUserToFamily(User user, Family family) {
        family.familyMembers.add(user);
        user.setFamily(family);
        parentService.createOrUpdateParent((Parent) user);
        familyService.createNewFamily(family);
    }
}
