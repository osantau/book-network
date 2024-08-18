package oct.soft.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
	private Long id;
	private String title;
	private String authorName; 
	private String isbn;
	private String synopsis;
	private String owner;
	private String bookCover;	
	private byte[] cover;
	private double rate;
	private boolean archived;
	private boolean shareable;
		
}
