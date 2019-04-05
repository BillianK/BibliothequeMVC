package model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class Theme {

	private String codeTheme;
	private String libelle;
	private Theme themeParent;
	
}
