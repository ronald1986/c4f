package com.biztrace.web.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biztrace.dao.GenericDAO;
import com.biztrace.dao.model.Criteria;
import com.biztrace.model.GenericEntity;
import com.biztrace.util.MD5Encoder;

@Service
public class PasswordManager {
	@Autowired
	private GenericDAO genericDAO;
	@Autowired
	private UserManager userManager;

	public boolean isPasswordValid(final String orgCode, final String loginId,
			final String password) {
	    GenericEntity user = genericDAO.getUniqueEntity(
	        "usr", false,
			new Criteria("orgCode", orgCode),
			new Criteria("loginId", loginId));
		if (user == null) {
			return false;
		}

		return StringUtils.equals((String) user.get("password"),
		    MD5Encoder.getInstance().encode(password, userManager.getUserSystemName(user)));
	}

	public void setGenericDAO(GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
