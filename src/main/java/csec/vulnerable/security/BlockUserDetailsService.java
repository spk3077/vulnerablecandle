package csec.vulnerable.security;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import csec.vulnerable.beans.User;
import csec.vulnerable.dao.UserDao;

@Service
public class BlockUserDetailsService implements UserDetailsService {

    /* private static final Logger LOGGER = LoggerFactory.getLogger(BlockUserDetailsService.class);
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final int BLOCK_DURATION_MINUTES = 30; */

    private final Map<String, LocalDateTime> blockedUsers = new HashMap<>();
    private final Map<String, Integer> failedAttempts = new HashMap<>();
    @Autowired
	UserDao userDao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (isBlocked(username)) {
            throw new UsernameNotFoundException("User is blocked for 30 minutes due to multiple failed login attempts.");
        }

        // Load user from the data source
        User user = userDao.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User: " + username + " was not found in the database");
		}
		return user;
	}

    public void increaseFailedAttempts(String username) {
        /* int attempts = failedAttempts.getOrDefault(username, 0);
        failedAttempts.put(username, attempts + 1);

        LOGGER.info("Failed login attempts for user {}: {}", username, attempts + 1);

        if (attempts + 1 >= MAX_FAILED_ATTEMPTS) {
            blockUser(username);
        } */
    }

    public boolean isBlocked(String username) {
        LocalDateTime blockedUntil = blockedUsers.get(username);

        if (blockedUntil == null) {
            return false;
        }

        boolean blocked = LocalDateTime.now().isBefore(blockedUntil);

        //LOGGER.info("User {} is{}blocked", username, blocked ? " " : " not ");

        if (!blocked) {
            blockedUsers.remove(username);
            resetFailedAttempts(username);
        }

        //return blocked;
        return false;
    }

    public void resetFailedAttempts(String username) {
        failedAttempts.remove(username);
    }

    /* private void blockUser(String username) {
        blockedUsers.put(username, LocalDateTime.now().plusMinutes(BLOCK_DURATION_MINUTES));
    } */
}