package com.example.demo.services;

import com.example.demo.model.Role;
import com.example.demo.model.RoleInfo;
import com.example.demo.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {

	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	public Set<RoleInfo> setActions() {
		HashSet<RoleInfo> actions = new HashSet<>();

		Role admin = roleRepository.findByRole("ROLE_ADMIN");
		RoleInfo adminInfo = new RoleInfo();
		adminInfo.setRole(admin);
		adminInfo.getActions().add("signup");
		adminInfo.getActions().add("edit");
		adminInfo.getActions().add("addevent");
		adminInfo.getActions().add("addperf");
		adminInfo.getActions().add("admin");
		actions.add(adminInfo);

		Role user = roleRepository.findByRole("ROLE_USER");
		RoleInfo userInfo = new RoleInfo();
		adminInfo.setRole(user);
		adminInfo.getActions().add("signup");
		actions.add(userInfo);

		return actions;
	}

}
