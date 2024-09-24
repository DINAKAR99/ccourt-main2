package com.example.demo.security;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserLoginService {

	public static final int MAX_FAILED_ATTEMPTS = 3;

	private static final long LOCK_TIME_DURATION = 30 * 60 * 1000; // 30Min

	@Autowired
	private UserRepository repo;

	public void increaseFailedAttempts(User user) {
		int newFailAttempts = user.getFailedAttempt() + 1;
		repo.updateFailedAttempts(newFailAttempts, user.getUserCode());
	}

	public void resetFailedAttempts(Long userCode) {
		repo.updateFailedAttempts(0, userCode);
	}

	public void lock(User user) {
		user.setAccountNonLocked(false);
		user.setLockTime(new Date());

		repo.save(user);
	}

	public boolean unlockWhenTimeExpired(User user) {
		long lockTimeInMillis = user.getLockTime().getTime();
		long currentTimeInMillis = System.currentTimeMillis();

		if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
			user.setAccountNonLocked(true);
			user.setLockTime(null);
			user.setFailedAttempt(0);

			repo.save(user);

			return true;
		}

		return false;
	}

	public static long getLockTimeDuration() {
		return LOCK_TIME_DURATION;
	}

}
