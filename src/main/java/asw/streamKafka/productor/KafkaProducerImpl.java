package asw.streamKafka.productor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import asw.streamKafka.productor.util.Topics;

import javax.annotation.ManagedBean;

@ManagedBean
public class KafkaProducerImpl implements KafkaProducer{

	private static final Logger logger = Logger.getLogger(KafkaProducerImpl.class);

	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public KafkaProducerImpl(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	// Sugerencias
	public void sendNewSuggestion(String suggestionId, String title) {
		send(Topics.NEW_SUGGESTION,
				"{ \"suggestion\":\"" + suggestionId + "\", \"title\":\"" + title + "\"}");
	}

	public void sendDeleteSuggestion(String suggestionId) {
		send(Topics.DELETE_SUGGESTION, "{ \"suggestion\":\"" + suggestionId + "\" }");
	}

	public void sendPositiveSuggestion(String suggestionId) {
		send(Topics.POSITIVE_SUGGESTION, "{ \"suggestion\":\"" + suggestionId + "\"}");
	}
	
	public void sendAlertSuggestion(String suggestionId) {
		send(Topics.ALERT_SUGGESTION, "{ \"suggestion\":\"" + suggestionId + "\"}");
	}

	// Comentarios
	public void sendNewComment(String commentId, String suggestionId) {
		send(Topics.NEW_COMMENT,
				"{ \"comment\":\"" + commentId + "\", \"suggestion\":\"" + suggestionId + "\"}");
	}

	public void sendPositiveComment(String commentId, String suggestionId) {
		send(Topics.POSITIVE_COMMENT,
				"{ \"comment\":\"" + commentId + "\", \"suggestion\":\"" + suggestionId + "\"}");
	}

	public void sendNegativeComment(String commentId, String suggestionId) {
		send(Topics.NEGATIVE_COMMENT,
				"{ \"comment\":\"" + commentId + "\", \"suggestion\":\"" + suggestionId + "\"}");
	}

	public void send(String topic, String data) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("Success on sending message \"" + data + "\" to topic " + topic);
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error(
						"Error on sending message \"" + data + "\", stacktrace " + ex.getMessage());
			}
		});
	}

}
