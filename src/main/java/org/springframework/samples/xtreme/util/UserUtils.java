package org.springframework.samples.xtreme.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtils {

    public UserDetails getUserDetails() {
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        return userDetails;
    }

    public Boolean isAdmin(UserDetails userDetails) {
        return userDetails.getAuthorities().stream().anyMatch(x-> x.getAuthority().equals("admin"));
    }
}
