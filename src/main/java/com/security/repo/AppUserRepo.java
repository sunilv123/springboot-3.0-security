/**
 * Created By Sunil Verma
 * Date: 07/01/23
 * Time: 4:09 PM
 * Project Name: security
 */

package com.security.repo;

import com.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, String> {
    AppUser findByEmail(String email);
}
