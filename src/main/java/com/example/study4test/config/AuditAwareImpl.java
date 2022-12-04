//package com.example.study4test.config;
//
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//
//import java.security.Principal;
//import java.util.Optional;
//
//public class AuditAwareImpl implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        return Optional.of("admin");
//                //Optional.of(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
//    }
//}
