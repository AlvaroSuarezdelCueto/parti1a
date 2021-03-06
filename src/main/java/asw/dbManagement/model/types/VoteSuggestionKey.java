package asw.dbManagement.model.types;

import java.io.Serializable;

public class VoteSuggestionKey implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Long participant;
	protected Long suggestion;

	VoteSuggestionKey() {
	}

	public VoteSuggestionKey(Long participant, Long suggestion) {
		this.participant = participant;
		this.suggestion = suggestion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((participant == null) ? 0 : participant.hashCode());
		result = prime * result + ((suggestion == null) ? 0 : suggestion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoteSuggestionKey other = (VoteSuggestionKey) obj;
		if (participant == null) {
			if (other.participant != null)
				return false;
		} else if (!participant.equals(other.participant))
			return false;
		if (suggestion == null) {
			if (other.suggestion != null)
				return false;
		} else if (!suggestion.equals(other.suggestion))
			return false;
		return true;
	}

}
