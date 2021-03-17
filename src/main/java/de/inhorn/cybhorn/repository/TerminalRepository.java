package de.inhorn.cybhorn.repository;

import de.inhorn.cybhorn.model.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Long> {
}
