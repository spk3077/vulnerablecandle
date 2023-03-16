package csec.vulnerable.security;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BlockUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockUserDetailsService.class);
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final int BLOCK_DURATION_MINUTES = 30;

    private final Map<String, LocalDateTime> blockedUsers = new HashMap<>();
    private final Map<String, Integer> failedAttempts = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (isBlocked(username)) {
            throw new RuntimeException("User is blocked for 30 minutes due to multiple failed login attempts.");
        }

        // Load user from the data source
        UserDetails userDetails = loadUserFromDataSource(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return userDetails;
    }

    private UserDetails loadUserFromDataSource(String username) {
        // Implement your logic to load user details from the data source (e.g., database) here.
        // For now, this method returns null as a placeholder.
        return null;
    }

    public void increaseFailedAttempts(String username) {
        int attempts = failedAttempts.getOrDefault(username, 0);
        failedAttempts.put(username, attempts + 1);
    
        LOGGER.info("Failed login attempts for user {}: {}", username, attempts + 1);
    
        if (attempts + 1 >= MAX_FAILED_ATTEMPTS) {
            blockUser(username);
        }
    }
    
    public boolean isBlocked(String username) {
        LocalDateTime blockedUntil = blockedUsers.get(username);
    
        if (blockedUntil == null) {
            return false;
        }
    
        boolean blocked = LocalDateTime.now().isBefore(blockedUntil);
    
        LOGGER.info("User {} is{}blocked", username, blocked ? " " : " not ");
    
        if (!blocked) {
            blockedUsers.remove(username);
            resetFailedAttempts(username);
        }
    
        return blocked;
    }

    private void resetFailedAttempts(String username) {
        failedAttempts.remove(username);
    }

    private void blockUser(String username) {
        blockedUsers.put(username, LocalDateTime.now().plusMinutes(BLOCK_DURATION_MINUTES));
    }
}
