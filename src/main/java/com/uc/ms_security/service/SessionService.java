package com.uc.ms_security.service;

import com.uc.ms_security.dto.session.*;
import com.uc.ms_security.entity.Session;
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

    public SessionResponseDTO create(CreateSessionDTO dto) {
        if (repository.existsByToken(dto.getToken())) throw new ApplicationException(ErrorCase.ALREADY_EXISTS, "Ya existe una sesión con este token");
        Session session = mapper.toEntity(dto);
        session.setUser(userRepository.findById(dto.getUserId()).orElseThrow(() -> new ApplicationException(
            ErrorCase.NOT_FOUND, "Usuario no encontrado con id: " + dto.getUserId())));
        return mapper.toResponseDTO(repository.save(session));
    }
    public List<SessionResponseDTO> findAll() { return mapper.toResponseDTOList(repository.findAll()); }
    public SessionResponseDTO findById(Long id) { return mapper.toResponseDTO(findEntity(id)); }
    public SessionResponseDTO update(Long id, UpdateSessionDTO dto) {
        Session session = findEntity(id);
        if (repository.existsByTokenAndIdNot(dto.getToken(), id)) throw new ApplicationException(ErrorCase.ALREADY_EXISTS, "El token pertenece a otra sesión");
        mapper.updateEntity(dto, session);
        return mapper.toResponseDTO(repository.save(session));
    }
    public void delete(Long id) { repository.delete(findEntity(id)); }
    private Session findEntity(Long id) { return repository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCase.NOT_FOUND, "Sesión no encontrada con id: " + id)); }
}