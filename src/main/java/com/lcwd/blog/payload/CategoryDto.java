package com.lcwd.blog.payload;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CategoryDto {

	private Integer categoryId;
	@NotEmpty
	@Size(min=4,message="categoryTitle should be larger than 4 characters")
	private String categoryTitle;
	@NotEmpty
	@Size(min=5,max=100,message="categoryDescription must be within 5 to 100 characters")
	private String categoryDescription;

}
