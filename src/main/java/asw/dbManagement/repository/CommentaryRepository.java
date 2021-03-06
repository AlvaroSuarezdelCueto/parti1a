package asw.dbManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asw.dbManagement.model.Comment;
import asw.dbManagement.model.Participant;
import asw.dbManagement.model.Suggestion;


@Repository
public interface CommentaryRepository extends JpaRepository<Comment, Long>{

	public Comment findByIdentificador(String identificador);
	List<Comment> findByParticipant(Participant participant);
	List<Comment> findBySuggestion(Suggestion Suggestion);
}
