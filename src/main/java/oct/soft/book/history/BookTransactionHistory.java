package oct.soft.book.history;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import oct.soft.book.Book;
import oct.soft.book.common.BaseEntity;
import oct.soft.book.user.User;
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookTransactionHistory extends BaseEntity {
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	private boolean returned;
	private boolean returnApproved;
	
}
