package oct.soft.book;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {
	private final BookService service;

	@PostMapping
	public ResponseEntity<Long> saveBook(@Valid @RequestBody BookRequest request, Authentication connectedUser) {

		return ResponseEntity.ok(service.save(request, connectedUser));
	}

	@GetMapping("{book-id}")
	public ResponseEntity<BookResponse> findBokkById(@PathVariable("book-id") Long bookId)
	{
		return ResponseEntity.ok(service.findById(bookId));
	}
	
	@GetMapping
	public ResponseEntity<PageResponse<BookResponse>> findAllBooks(){
	return null;
	}
}
