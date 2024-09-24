package com.example.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Configuration
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserLoginService userLoginService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String userName = request.getParameter("username");

		System.out.println("onAuthenticationFailure ::::::::::::username::::::::::::: " + userName);
		User user = null;

		user = userRepository.findByUserId(userName);

		if (user != null) {
			if (user.isAccountNonLocked()) {
				if (user.getFailedAttempt() < UserLoginService.MAX_FAILED_ATTEMPTS - 1) {
					System.out.println(" isAccountNonLocked >>>>>>>>>>>>  " + user.getFailedAttempt());

					int leftLoginAttempts = UserLoginService.MAX_FAILED_ATTEMPTS - 1 - user.getFailedAttempt();
					exception = new LockedException(
							"Username or Password is incorrect !!.  You have " + leftLoginAttempts + " chances left.");

					userLoginService.increaseFailedAttempts(user);
				} else {
					userLoginService.lock(user);
					exception = new LockedException("Your account has been locked due to 3 failed attempts."
							+ " It will be unlocked after 30 Minutes.");
				}
			} else if (!user.isAccountNonLocked()) {

				if (userLoginService.unlockWhenTimeExpired(user)) {

					System.out.println("ggggggggggggggggggggggggg");

					user = userRepository.findByUserId(userName);

					int leftLoginAttempts = UserLoginService.MAX_FAILED_ATTEMPTS - 1 - user.getFailedAttempt();
					exception = new LockedException(
							"Username or Password is incorrect !!.  You have " + leftLoginAttempts + " chances left.");

					userLoginService.increaseFailedAttempts(user);

				} else {

					long lockTimeInMillis = user.getLockTime().getTime();

					long currentTimeInMillis = System.currentTimeMillis();

					long lockTime = UserLoginService.getLockTimeDuration();

					if (lockTimeInMillis + lockTime > currentTimeInMillis) {

						long leftLockTimeInMin = ((lockTimeInMillis + lockTime) - currentTimeInMillis) / (1000 * 60);
						exception = new LockedException(
								"Too many wrong attempts. You are locked out!. It will be unlocked after "
										+ leftLockTimeInMin + " Minutes.");

					}

				}
			}

		}

		super.setDefaultFailureUrl("/login?error");
		super.onAuthenticationFailure(request, response, exception);
	}

}
