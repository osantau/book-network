package oct.soft.book;

import org.springframework.stereotype.Service;

@Service
public class BookMapper {

	public Book toBook(BookRequest request) {
		// TODO Auto-generated method stub
		return Book.builder().id(request.id()).title(request.title()).authorName(request.authorName())
				.synopsis(request.synopsis()).archived(false).shareable(request.shareable()).build();
	}

	public BookResponse toBookResponse(Book book)
	{
		return BookResponse.builder()
				.id(book.getId())
				.title(book.getTitle())
				.authorName(book.getAuthorName())
				.isbn(book.getIsbn())
				.synopsis(book.getSynopsis())
				.rate(book.getRate())
				.archived(book.isArchived())
				.shareable(book.isShareable())
				.owner(book.getOwner().fullName())
				//TODO implement this later
//				.cover()
				.build();
	}
}
