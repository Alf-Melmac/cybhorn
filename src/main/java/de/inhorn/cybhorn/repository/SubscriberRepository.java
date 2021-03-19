package de.inhorn.cybhorn.repository;

import de.inhorn.cybhorn.model.Subscriber;
import de.inhorn.cybhorn.model.Subscription;
import de.inhorn.cybhorn.model.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alf
 * @since 17.03.2021
 */
@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    boolean existsByTerminal(Terminal t);
    boolean existsBySubscription(Subscription s);

    List<Subscriber> findAllBySubscription(Subscription subscription);
}
