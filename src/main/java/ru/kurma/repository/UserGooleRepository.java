package ru.kurma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kurma.model.UserGoggle;

public interface UserGooleRepository extends JpaRepository<UserGoggle, String> {

}
