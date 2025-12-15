package com.acs.authenticator.infrastructure.security.token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.acs.authenticator.application.dto.TokenDto;
import com.acs.authenticator.application.dto.UserDto;
import com.acs.authenticator.application.service.TokenService;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService implements TokenService {

  @Value("${security.jwt.secret-key}")
  private String secretKey;

  @Value("${security.jwt.access-expiration-time}")
  private long jwtAccessExpiration;

  @Value("${security.jwt.refresh-expiration-time}")
  private long jwtRefreshExpiration;

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parser()
        .verifyWith(getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  @Override
  public boolean isValid(TokenDto token, UserDto user) {
    final String username = extractUsername(token);
    return (username.equals(user.username()) && !isExpired(token));
  }

  private String extractUsername(TokenDto token) {
    return extractClaim(token.accessToken(), Claims::getSubject);
  }

  @Override
  public boolean isExpired(TokenDto token) {
    return extractExpiration(token.accessToken()).before(new Date(System.currentTimeMillis()));
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  @Override
  public TokenDto generateToken(UserDto user) {
    return generateToken(user, Map.of());
  }

  @Override
  public TokenDto generateToken(UserDto user, Map<String, Object> args) {
    long currentSystemTime = System.currentTimeMillis();
    Date issueDate = new Date(currentSystemTime);
    Date accessExpirationDate = new Date(currentSystemTime + jwtAccessExpiration);
    String accessToken = buildToken(user, args, issueDate, accessExpirationDate);
    Date refreshExpirationDate = new Date(currentSystemTime + jwtRefreshExpiration);
    String refreshToken = buildToken(user, args, issueDate, refreshExpirationDate);
    return new TokenDto(
        accessToken, convertToLocalDateTime(accessExpirationDate),
        refreshToken, convertToLocalDateTime(refreshExpirationDate),
        convertToLocalDateTime(issueDate)
    );
  }

  private LocalDateTime convertToLocalDateTime(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  private String buildToken(
      UserDto user,
      Map<String, Object> extraClaims,
      Date issueDate,
      Date expirationDate
  ) {
    return Jwts
        .builder()
        .claims(extraClaims)
        .subject(user.username())
        .issuedAt(issueDate)
        .expiration(expirationDate)
        .signWith(getSignInKey())
        .compact();
  }

  private SecretKey getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
