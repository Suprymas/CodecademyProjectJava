package com.mano.codecademy.dinerApi.repositories;

import com.mano.codecademy.dinerApi.User.DinerUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<DinerUser, Long> {
    Optional<DinerUser> findByName(String name);
}
