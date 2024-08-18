package oct.soft.book;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import oct.soft.book.user.User;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookMapper bookMapper;
	private final BookRepository bookRepository;

	public Long save(BookRequest request, Authentication connectedUser) {
		User user = (User) connectedUser.getPrincipal();
		Book book = bookMapper.toBook(request);
		book.setOwner(user);

		return bookRepository.save(book).getId();
	}

	public BookResponse findById(Long bookId) {		
		return bookRepository.findById(bookId).map(bookMapper::toBookResponse).orElseThrow(()->new EntityNotFoundException("No book found with id::  "+bookId));
	}
	
 

}
