import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArchivingStringsTask1 {
	public static void archive(File fileIn) {
		String out = "";
		String line;
		int value = 0;
		Character lastChar = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(fileIn))) {
			while ((line = br.readLine()) != null)
				for (Character ch : line.toCharArray())
					if (ch != ' ') {
						if (lastChar == 0)
							lastChar = ch;
						if (lastChar == ch)
							value = value + 1;
						 else {
							out = out.concat(lastChar.toString() + value);
							lastChar = ch;
							value = 1;
						}
					}
			out = out.concat(lastChar.toString() + value);

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

		File fileOut = new File(fileIn.getParent(), "FileOut.txt");
		try (BufferedWriter wr = new BufferedWriter(new FileWriter(fileOut))) {
			wr.write(out);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void main(String[] argv) {
		ArchivingStringsTask1.archive(new File(argv[0]));
	}
}
