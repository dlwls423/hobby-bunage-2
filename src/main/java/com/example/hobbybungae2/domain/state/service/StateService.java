package com.example.hobbybungae2.domain.state.service;

import com.example.hobbybungae2.domain.state.entity.State;
import com.example.hobbybungae2.domain.state.exception.NotFoundStateException;
import com.example.hobbybungae2.domain.state.repository.StateRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateService {

	private final StateRepository stateRepository;

	public State validateStateExistence(State state) {
		return stateRepository.findByStateDoAndStateSiAndStateGu(state.getStateDo(), state.getStateSi(), state.getStateGu())
			.orElseThrow(() -> new NotFoundStateException("state", state.toString()));
	}
}
