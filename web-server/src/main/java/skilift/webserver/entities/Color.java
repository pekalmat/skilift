package skilift.webserver.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
	uniqueConstraints={
		@UniqueConstraint(columnNames={"color"}),
		@UniqueConstraint(columnNames={"colorCode"}),
		@UniqueConstraint(columnNames={"difficultyLevel"})
	}
)
public class Color implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private String color;
	
	@NotNull
	private String colorCode;
	
	@NotNull
	private String difficultyLevel;
	
	public Color() {
	}
	
	public Color(String color, String colorCode, String difficultyLevel) {
		this.color = color;
		this.colorCode = colorCode;
		this.difficultyLevel = difficultyLevel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColorCOde() {
		return colorCode;
	}
	
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	
}