package com.fabada.agendamento.service;

import com.fabada.agendamento.enums.UserRole;
import com.fabada.agendamento.execption.EmailNotFoundException;
import com.fabada.agendamento.model.CodeManager;
import com.fabada.agendamento.model.User;
import com.fabada.agendamento.repository.CodeRepository;
import com.fabada.agendamento.utils.RandomCode;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CodeServiceImpl Unit Tests")
class CodeServiceImplTest {
    @Mock
    private CodeRepository codeRepository;
    @Mock
    private UserService userService;
    @Mock
    private RandomCode randomCode;

    @InjectMocks
    private CodeServiceImpl codeService;

    private User user;
    private Optional<CodeManager> optionalCodeManager;

    @BeforeEach
    void init(){
        this.user = User
                .builder()
                .id(10L)
                .username("fabiano")
                .email("fabiano@gmail.com")
                .password(new BCryptPasswordEncoder().encode("1234"))
                .role(UserRole.CLIENT)
                .lastUpdate(LocalDateTime.now().plusHours(5))
                .register(LocalDateTime.now()).build();

        this.optionalCodeManager =
                Optional.of(
                        CodeManager
                        .builder()
                                .id(1L)
                                .isUsed(false)
                                .userId(user)
                                .register(LocalDateTime.now())
                                .timeValid(LocalDateTime.now().plusMinutes(15))
                                .code(597120)
                        .build()
                );
    }


    @Nested
    @DisplayName("Generate code test")
    class CreateCodeTest{

        @Test
        @DisplayName("should throw exception if email not found")
        void shouldThrowExceptionIfEmailNotFound(){
            final String email = "teste@gmail.com";

            when(userService.findByEmail(email))
                    .thenThrow(new EmailNotFoundException("email not found"));

            EmailNotFoundException exception = assertThrows(
                    EmailNotFoundException.class, () -> codeService.generateCode(email));

            assertEquals("email not found", exception.getMessage());
            verify(userService, times(1)).findByEmail(email);
        }

        @Test
        @DisplayName("should update code manager")
        void shouldUpdateCodeManager(){
            final String email = "fabiano@gmail.com";
            final String code = "597120";

            when(userService.findByEmail(email))
                    .thenReturn(user);

            when(codeRepository.findByUserId(user))
                    .thenReturn(optionalCodeManager);

            when(randomCode.createCode(6, 9))
                    .thenReturn(code);

            lenient().when(codeRepository.save(optionalCodeManager.get()))
                    .thenReturn(optionalCodeManager.get());

            codeService.generateCode(email);

            verify(userService, times(1)).findByEmail(email);
            verify(randomCode, times(1)).createCode(6, 9);
            verify(codeRepository, times(1)).save(any(CodeManager.class));
        }

    }
}