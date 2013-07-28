package example.core.domain;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class Person {
	@Id
	private long id;
	
	@Column
	@NotBlank
	private String givenName;
	
	@Column
	@NotBlank
	private String familyName;
	
	@Column
	@Email
	private String email;
}
