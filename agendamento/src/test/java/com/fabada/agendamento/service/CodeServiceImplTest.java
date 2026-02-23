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
    final int code = 597120;

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
                                .code(code)
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
        @DisplayName("should create code manager")
        void shouldCreateCodeManager(){
            final String email = "fabiano@gmail.com";
            final String code = "597120";

            when(userService.findByEmail(email))
                    .thenReturn(user);

            when(codeRepository.findByUserId(user))
                    .thenReturn(optionalCodeManager);

            when(randomCode.createCode(6, 9))
                    .thenReturn(code);

            when(codeRepository.save(any(CodeManager.class)))
                    .thenReturn(optionalCodeManager.get());

            codeService.generateCode(email);

            ArgumentCaptor<CodeManager> argument = ArgumentCaptor.forClass(CodeManager.class);

            verify(userService, times(1)).findByEmail(email);
            verify(randomCode, times(1)).createCode(6, 9);
            verify(codeRepository, times(1)).save(argument.capture());

            String captureCode = String.valueOf(argument.getValue().getCode());

            assertEquals(Integer.parseInt(code), argument.getValue().getCode());
            assertTrue(argument.getValue().getTimeValid().isAfter(argument.getValue().getRegister()));
            assertTrue(!captureCode.isEmpty() && captureCode.chars().allMatch(Character::isDigit));
        }

    }

    @Nested
    @DisplayName("Code manager find")
    class CodeManagerFind{

        @Test
        @DisplayName("should return code manager by code")
        void shouldReturnCodeManageByCode(){
            when(codeRepository.findByCode(code))
                    .thenReturn(optionalCodeManager);

            Optional<CodeManager> result = codeService.findByCode(code);

            verify(codeRepository, times(1))
                    .findByCode(code);

            assertEquals(code, result.get().getCode());
        }

        @Test
        @DisplayName("should return code manager by user")
        void shouldReturnCodeManageByUser(){
            when(codeRepository.findByUserId(user))
                    .thenReturn(optionalCodeManager);

            Optional<CodeManager> result = codeService.findByUserId(user);

            verify(codeRepository, times(1))
                    .findByUserId(user);

            assertEquals(user, result.get().getUserId());
        }


        @Test
        @DisplayName("should return null code manager by code")
        void shouldReturnNullCodeManageByCode(){
            when(codeRepository.findByCode(code))
                    .thenReturn(Optional.empty());

            Optional<CodeManager> result = codeService.findByCode(code);

            verify(codeRepository, times(1))
                    .findByCode(code);

            assertEquals(true, result.isEmpty());
        }

        @Test
        @DisplayName("should return null code manager by user")
        void shouldReturnNullCodeManageByUser(){
            when(codeRepository.findByUserId(user))
                    .thenReturn(Optional.empty());

            Optional<CodeManager> result = codeService.findByUserId(user);

            verify(codeRepository, times(1))
                    .findByUserId(user);

            assertEquals(true, result.isEmpty());
        }
    }
}