package com.jay.user_service.logic.impl;

import com.jay.user_service.dto.LoginDto;
import com.jay.user_service.dto.UserDto;
import com.jay.user_service.logic.UserService;
import com.jay.user_service.model.User;
import com.jay.user_service.repository.UserRepository;
import com.jay.user_service.utils.JwtUtil;
import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Connection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Value("${vonage.api-key}")
    private String apiKey;

    @Value("${vonage.api-secret}")
    private String apiSecret;

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private String port;

    @Value("${redis.password}")
    private String password;

    private static final String OTP_CHARACTERS = "0123456789";
    private static final int OTP_LENGTH = 6;

    @Autowired
    private UserRepository userRepository;

    public Jedis connection() throws Exception {
        Jedis jedis = new Jedis("redis://default:" + password + "@" + host + ":" + port);
        if (jedis.getConnection().isConnected()) {
            return jedis;
        } else {
            throw new Exception("Unable to connect to Redis");
        }
    }

    @Override
    public ResponseEntity<?> createUser(UserDto userInput) {
        User user = new User();
        user.setName(userInput.getName());
        user.setEmail(userInput.getEmail());
        user.setPhone(userInput.getPhone());
        user.setGender(userInput.getGender());
        user.setState(userInput.getState());
        user.setDob(userInput.getDob());

        try {
            boolean isValid = isOtpValid(userInput.getPhone(), userInput.getOtp());
            if (isValid) {
                userRepository.save(user);
                Map<String, String> response = new HashMap<>();
                response.put("message", "User created successfully!");
                response.put("token", JwtUtil.generateToken(userInput.getPhone()));
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create User " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String generateOtp() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(OTP_LENGTH);

        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(OTP_CHARACTERS.charAt(random.nextInt(OTP_CHARACTERS.length())));
        }

        return otp.toString();
    }

    @Override
    public ResponseEntity<?> sendOtpToPhone(String number) throws Exception {
        VonageClient client = VonageClient.builder().apiKey(apiKey).apiSecret(apiSecret).build();

        String otp = generateOtp();

        TextMessage message = new TextMessage("Vonage APIs",
                number,
                otp + " is your OTP to register in Bus Booking App."
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            storeOtp(number, otp, 5);
            Map<String, String> responseEntity = new HashMap<>();
            responseEntity.put("message", "OTP sent successfully!");
            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
        } else {
            Map<String, String> responseEntity = new HashMap<>();
            responseEntity.put("error", "Message failed with error: " + response.getMessages().get(0).getErrorText());
            return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> loginUser(LoginDto loginInput) {
        try {
            boolean isValid = isOtpValid(loginInput.getPhone(), loginInput.getOtp());
            if (isValid) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "User logged in successfully!");
                response.put("token", JwtUtil.generateToken(loginInput.getPhone()));
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to login User " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> currentUser(String token) {
        String phoneNumber = JwtUtil.extractSubject(token);
        return new ResponseEntity<>(userRepository.findByPhone(phoneNumber), HttpStatus.FOUND);
    }

    public void storeOtp(String key, String otp, long ttlInMinutes) throws Exception {
//        redisTemplate.opsForValue().set(key, otp, ttlInMinutes, TimeUnit.MINUTES);
        Jedis jedis = connection();
        jedis.set(key, otp);
    }

    public boolean isOtpValid(String key, String otp) throws Exception {
        Jedis jedis = connection();
        String storedOtp = jedis.get(key);
        System.out.println(storedOtp);
        if (storedOtp != null && storedOtp.equals(otp)) {
            jedis.del(key); // Optionally delete OTP after successful validation
            return true;
        }
        return false;
    }

    @Override
    public String store(String phone) throws Exception {
        String otp = generateOtp();
        storeOtp(phone, otp, 5);
        return otp + " OTP stored successfully";
    }

}
