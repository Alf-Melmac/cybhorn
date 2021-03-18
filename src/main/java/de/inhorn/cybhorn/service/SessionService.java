package de.inhorn.cybhorn.service;

import de.inhorn.cybhorn.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author Alf
 * @since 18.03.2021
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SessionService {
//	private final SessionRepository sessionRepository;

	public void createSession() {
		//TODO
	}

	public List<Subscription> findAllOrdered() {
//		return sessionRepository.findAll(Sort.by("name"));
		return Collections.emptyList();
	}
}
