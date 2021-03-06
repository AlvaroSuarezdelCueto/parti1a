package asw.citizensLoader.parser.cartas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import asw.dbManagement.model.Participant;

public class TxtLetter extends Letter {
	private Writer writer;

	public void createLetter(Participant user) throws IOException {
		File folder = new File("src/main/resources/cartas/txt");
		if (!folder.exists())
			folder.mkdirs();
		File letter = new File("src/main/resources/cartas/txt/" + user.getDNI() + ".txt");
		writer = new FileWriter(letter);
		writer.write("Usuario: " + user.getEmail() + "\n Password: " + user.getPassword());
		writer.close();
	}
}
