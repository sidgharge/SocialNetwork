package com.network.social.user.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.network.social.user.dtos.Email;
import com.network.social.user.dtos.RegistrationDto;
import com.network.social.user.exceptions.EmailAlreadyRegisteredException;
import com.network.social.user.exceptions.InvalidLinkException;
import com.network.social.user.models.User;
import com.network.social.user.repositories.UserRedisRepository;
import com.network.social.user.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	@Value("${activation.template.path}")
	private String activationTemplatePath;

	@Autowired
	private UserRedisRepository userRedisRepository;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void register(RegistrationDto dto, String url)
			throws EmailAlreadyRegisteredException, IOException, MessagingException {
		User user = modelMapper.map(dto, User.class);

		Optional<User> optionalOldUser = userRepository.findByEmail(dto.getEmail());

		if (optionalOldUser.isPresent()) {
			throw new EmailAlreadyRegisteredException();
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);

		String mailText = readFile(activationTemplatePath);

		String token = UUID.randomUUID().toString();
		userRedisRepository.save(token, String.valueOf(user.getId()));

		String link = url + "/user/activate?token=" + token;
		mailText = mailText.replace("{{link}}", link);

		Email email = new Email(dto.getEmail(), "Activation Link", mailText);

		emailService.sendEmail(email);
	}

	@Override
	public void activateUser(String token) throws InvalidLinkException {
		String userIdString = userRedisRepository.get(token);

		if (userIdString == null) {
			throw new InvalidLinkException();
		}

		User user = userRepository.findById(Long.parseLong(userIdString)).get();
		user.setActivated(true);
		userRepository.save(user);
		
		userRedisRepository.delete(token);
	}
	
	private String readFile(String path) throws IOException {
		File mailFile = ResourceUtils.getFile(activationTemplatePath);
		return new String(Files.readAllBytes(mailFile.toPath()));
	}
}
