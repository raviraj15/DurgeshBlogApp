package com.lcwd.blog.payload;




import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDto {

	private int id;
	
	@javax.validation.constraints.NotEmpty
	@javax.validation.constraints.Size(min=4,message="name should be larger than 4 characters")
	private String name;
	
	@javax.validation.constraints.Email(message="email must be valid email")
	private String email;
	
	@javax.validation.constraints.NotEmpty
	@javax.validation.constraints.Size(min=4,max=10,message="password must be within 4 to 10 characters")
	private String password;
	
	@javax.validation.constraints.NotEmpty
	private String about;

}
