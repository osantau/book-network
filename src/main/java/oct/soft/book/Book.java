package oct.soft.book;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import oct.soft.book.common.BaseEntity;
import oct.soft.book.feedback.Feedback;
import oct.soft.book.history.BookTransactionHistory;
import oct.soft.book.user.User;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends BaseEntity {
	private String title;
	private String authorName;
	private String isbn;
	private String synopsis;
	private String bookCover;
	private boolean archived;
	private boolean shareable;
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;
	@OneToMany(mappedBy = "book")
	private List<Feedback> feedbacks;
	@OneToMany(mappedBy = "book")
	private List<BookTransactionHistory> histories;

	@Transient
	public double getRate() {
		if (feedbacks == null || feedbacks.isEmpty()) {
			return 0.0;
		}

		var rate = this.feedbacks.stream().mapToDouble(Feedback::getNote).average().orElse(0.0);
		double roundedRate = Math.round(rate * 10.0) / 10.0;
		return roundedRate;
	}

}
