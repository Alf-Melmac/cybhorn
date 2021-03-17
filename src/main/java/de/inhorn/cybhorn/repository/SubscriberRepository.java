package de.inhorn.cybhorn.repository;

import de.inhorn.cybhorn.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
}
