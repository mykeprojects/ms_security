package com.uc.ms_security.service;

import com.uc.ms_security.dto.session.SessionRequestDTO;
import com.uc.ms_security.dto.session.SessionResponseDTO;
import com.uc.ms_security.entity.Session;
import com.uc.ms_security.entity.User;
import com.uc.ms_security.exception.*;
import com.uc.ms_security.mapper.SessionMapper;
import com.uc.ms_security.repository.SessionRepository;
import com.uc.ms_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository repository;
    private final UserRepository userRepository;
    private final SessionMapper mapper;

    public SessionResponseDTO create(Long userId, SessionRequestDTO dto) {
        if (repository.existsByToken(dto.getToken())) throw new ApplicationException(ErrorCase.ALREADY_EXISTS, "Ya existe una sesión con este token");
        Session session = mapper.toEntity(dto);
        session.setUser(findUser(userId));
        return mapper.toResponseDTO(repository.save(session));
    }
    public List<SessionResponseDTO> findAllByUserId(Long userId) {
        findUser(userId);
        return mapper.toResponseDTOList(repository.findAllByUserId(userId));
    }
    public SessionResponseDTO findById(Long userId, Long sessionId) {
        return mapper.toResponseDTO(findSession(userId, sessionId));
    }
    public SessionResponseDTO update(Long userId, Long sessionId, SessionRequestDTO dto) {
        Session session = findSession(userId, sessionId);
        if (repository.existsByTokenAndIdNot(dto.getToken(), sessionId)) throw new ApplicationException(ErrorCase.ALREADY_EXISTS, "El token pertenece a otra sesión");
        mapper.updateEntity(dto, session);
        return mapper.toResponseDTO(repository.save(session));
    }
    public void delete(Long userId, Long sessionId) { repository.delete(findSession(userId, sessionId)); }
    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ApplicationException(
                ErrorCase.NOT_FOUND, "Usuario no encontrado con id: " + userId));
    }
    private Session findSession(Long userId, Long sessionId) {
        return repository.findByIdAndUserId(sessionId, userId).orElseThrow(() -> new ApplicationException(
                ErrorCase.NOT_FOUND, "Sesión no encontrada para este usuario"));
    }
}