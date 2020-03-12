package com.justindodson.familybucks.app.service.user;

import com.justindodson.familybucks.app.auth.User;
import com.justindodson.familybucks.app.model.entity.user.Family;
import com.justindodson.familybucks.app.model.entity.user.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyMemberServiceImpl implements FamilyMemberService {
    private final FamilyServiceImpl familyService;
    private final ParentServiceImpl parentServiceImpl;

    @Autowired
    public FamilyMemberServiceImpl(FamilyServiceImpl familyService, ParentServiceImpl parentServiceImpl) {
        this.familyService = familyService;
        this.parentServiceImpl = parentServiceImpl;
    }

    @Override
    public void addUserToFamily(User user, Family family) {
        family.familyMembers.add(user);
        user.setFamily(family);
        parentServiceImpl.createOrUpdateParent((Parent) user);
        familyService.createNewFamily(family);
    }
}
